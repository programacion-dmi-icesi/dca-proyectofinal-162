package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidHijo extends EspecieAbstracta implements IApareable, ICarnivoro {

	PApplet app;
	private int vida;

	private PImage[] frenteEnfermo = new PImage[12];
	private PImage[] frenteSano = new PImage[12];
	private PImage[] espaldaEnfermo = new PImage[12];
	private PImage[] espaldaSano = new PImage[12];
	private PImage[] ladoEnfermo = new PImage[12];
	private PImage[] ladoSano = new PImage[12];
	private PImage[] transicionFrente = new PImage[12];
	private PImage[] transicionEspalda = new PImage[12];
	private PImage[] transicionLado = new PImage[12];

	private float energia;
	private int contador;
	private float fuerza;
	private int velocidad;

	private PVector dir;
	private PVector pos;
	private PVector objetivo;
	private int animacion;
	private Random random;

	public AmidHijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		objetivo = new PVector(300, 100);

		dir = new PVector(0, 0);
		pos = new PVector(0, 0);

		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../data/Personajes/P5/P5 Frente/P5 F Enfermo" + i + ".png"); // FRENTE
																											// ENFERMO

			frenteSano[i] = app.loadImage("../data/Personajes/P5/P5 Frente/P5 F Sano" + i + ".png"); // FRENTE
																										// SANO

			espaldaEnfermo[i] = app.loadImage("../data/Personajes/P5/P5 Espalda/P5 E Enfermo" + i + ".png"); // ESPALDA
																												// ENFERMO

			espaldaSano[i] = app.loadImage("../data/Personajes/P5/P5 Espalda/P5 E Sano" + i + ".png"); // ESPALDA
																										// SANO

			ladoEnfermo[i] = app.loadImage("../data/Personajes/P5/P5 Lado/P5 L Enfermo" + i + ".png"); // LADO
																										// ENFERMO

			ladoSano[i] = app.loadImage("../data/Personajes/P5/P5 Lado/P5 L Sano" + i + ".png"); // LADO
																									// SANO

			/*
			 * transicionFrente[i] = app.loadImage(
			 * "../data/Personajes/P5/Transiciones/Frente/Transicion P5 F" + i +
			 * ".png"); // TRANSICI�N // FRENTE
			 * 
			 * transicionEspalda[i] = app.loadImage(
			 * "../data/Personajes/P5/Transiciones/Espalda/Transicion P5 E" + i
			 * + ".png"); // TRANSICI�N // ESPALDA
			 * 
			 * transicionLado[i] = app.loadImage(
			 * "../data/Personajes/P5/Transiciones/Lado/Transicion P5 L" + i +
			 * ".png"); // TRANSICI�N // LADO
			 */
		}

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.ellipse(objetivo.x, objetivo.y, 10, 10);
		animacion();
		perseguir();
	}

	@Override
	public void mover() {
		pos.add(dir);
		x = (int) pos.x;
		y = (int) pos.y;
	}

	public void animacion() {
		app.imageMode(PApplet.CENTER);
		switch (animacion) {
		case 0:

			if (contador == 12)
				contador = 0;
			app.image(frenteSano[contador], x, y, 75, 75);
			contador++;
			break;

		case 1:
			if (contador == 12)
				contador = 0;
			app.image(espaldaSano[contador], x, y, 75, 75);
			contador++;
			break;

		case 2:
			if (contador == 12)
				contador = 0;
			app.image(ladoSano[contador], x, y, 75, 75);
			contador++;
			break;

		case 3:
			if (contador == 12)
				contador = 0;
			app.pushMatrix();
			app.scale(-1.0f, 1.0f);
			app.image(ladoSano[contador], -ladoSano[contador].width - x + 100, y, 75, 75);
			app.popMatrix();
			contador++;

			break;
		default:
			break;
		}
		app.imageMode(PApplet.CORNER);
		app.fill(255, 30, 50);
		app.noStroke();
		app.ellipse(pos.x, pos.y, 20, 20);
		app.noFill();
	}

	public void perseguir() {
		PVector distanX = PVector.sub(objetivo, pos);
		distanX.y = 0;
		PVector distanY = PVector.sub(objetivo, pos);
		distanY.x = 0;
		float dX = distanX.mag();

		distanX.normalize();
		distanY.normalize();

		PVector direccionX = PVector.sub(distanX, dir);
		dir.add(direccionX);
		if (distanX.x < 0) {
			animacion = 2;
		} else if (distanX.x > 0) {
			animacion = 3;
		}

		if (dX <= 0) {

			if (distanY.y < 0) {
				animacion = 1;
			} else if (distanY.y > 0) {
				animacion = 0;
			}

			PVector direccionY = PVector.sub(distanY, dir);
			dir.add(direccionY);
		}

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
			} catch (Exception e) {

			}
		}

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}
}