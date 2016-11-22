package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.Random;

import processing.core.PImage;
import processing.core.PVector;
import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidCanibal extends EspecieAbstracta implements ICanibal {

	private PImage[] frenteEnfermo = new PImage[12];
	private PImage[] frenteSano = new PImage[12];
	private PImage[] espaldaEnfermo = new PImage[12];
	private PImage[] espaldaSano = new PImage[12];
	private PImage[] ladoEnfermo = new PImage[12];
	private PImage[] ladoSano = new PImage[12];
	private PImage[] transicionFrente = new PImage[12];
	private PImage[] transicionEspalda = new PImage[12];
	private PImage[] transicionLado = new PImage[12];

	private PApplet app;

	private int contador;
	private int vida;
	private float fuerza;
	private int velocidad;
	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private PVector pos;
	private PVector objetivo;
	private int animacion;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public AmidCanibal(EcosistemaAbstracto ecosistema) {

		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		objetivo = new PVector(-300, 300);

		dir = new PVector(0, 0);
		pos = new PVector(0, 0);

		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Enfermo" + i + ".png"); // FRENTE
																											// ENFERMO

			frenteSano[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Sano" + i + ".png"); // FRENTE
																										// SANO

			espaldaEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Enfermo" + i + ".png"); // ESPALDA
																												// ENFERMO

			espaldaSano[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Sano" + i + ".png"); // ESPALDA
																										// SANO

			ladoEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Enfermo" + i + ".png"); // LADO
																										// ENFERMO

			ladoSano[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Sano" + i + ".png"); // LADO
																									// SANO

			transicionFrente[i] = app
					.loadImage("../data/Personajes/P1/Transiciones/Frente/Transicion P1 F" + i + ".png"); // TRANSICIÓN
																											// FRENTE

			transicionEspalda[i] = app
					.loadImage("../data/Personajes/P1/Transiciones/Espalda/Transicion P1 E" + i + ".png"); // TRANSICIÓN
																											// ESPALDA

			transicionLado[i] = app.loadImage("../data/Personajes/P1/Transiciones/Lado/Transicion P1 L" + i + ".png"); // TRANSICIÓN
																														// LADO
		}

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.ellipse(objetivo.x, objetivo.y, 50, 50);
		animacion();
		perseguir();
	}

	@Override
	public void mover() {
		pos.add(dir);
	}

	public void animacion() {
		switch (animacion) {
		case 0:

			if (contador == 12)
				contador = 0;
			app.image(frenteSano[contador], pos.x - 50, pos.y - 50);
			contador++;
			break;

		case 1:
			if (contador == 12)
				contador = 0;
			app.image(espaldaSano[contador], pos.x - 50, pos.y - 50);
			contador++;
			break;

		case 2:
			if (contador == 12)
				contador = 0;
			app.image(ladoSano[contador], pos.x - 50, pos.y - 50);
			contador++;
			break;

		case 3:
			if (contador == 12)
				contador = 0;
			app.pushMatrix();
			app.scale(-1.0f, 1.0f);
			app.image(ladoSano[contador], -ladoSano[contador].width - pos.x + 50, pos.y - 50);
			app.popMatrix();
			contador++;

			break;
		default:
			break;
		}
	}

	public void perseguir() {
		PVector distanX = PVector.sub(objetivo, pos);
		distanX.y = 0;
		PVector distanY = PVector.sub(objetivo, pos);
		distanY.x = 0;
		float dX = distanX.mag();
		System.out.println(distanX.x + "  " + distanY.y);

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
		// TODO Auto-generated method stub

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
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}