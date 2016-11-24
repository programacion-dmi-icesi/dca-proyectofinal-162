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

public class AmidHijo extends EspecieAbstracta {

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
	private PImage[] extasis = new PImage[30];
	private PImage[] envenenado = new PImage[12];

	private float energia;
	private int contador, contadorExta;

	private PVector dir;
	private PVector pos;
	private PVector objetivo;
	private int animacion;
	private int ciclo;
	private boolean enExtasis, enVeneno;

	public AmidHijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.x = (int) app.random(-500, 500);
		this.y = (int) app.random(-500, 500);
		this.vida = 50;
		this.energia = 250;
		objetivo = new PVector(300, 100);

		dir = new PVector(0, 0);
		pos = new PVector(x, y);

		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Frente/P5 F Enfermo" + i + ".png"); // FRENTE
			// ENFERMO

			frenteSano[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Frente/P5 F Sano" + i + ".png"); // FRENTE
																											// SANO

			espaldaEnfermo[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Espalda/P5 E Enfermo" + i + ".png"); // ESPALDA
																													// ENFERMO

			espaldaSano[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Espalda/P5 E Sano" + i + ".png"); // ESPALDA
			// SANO

			ladoEnfermo[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Lado/P5 L Enfermo" + i + ".png"); // LADO
			// ENFERMO

			ladoSano[i] = app.loadImage("../dataAmids/Personajes/P5/P5 Lado/P5 L Sano" + i + ".png"); // LADO
																										// SANO

			transicionFrente[i] = app
					.loadImage("../dataAmids/Personajes/P5/Transiciones/Frente/Transicion P5 F" + i + ".png"); // TRANSICI�N
																												// //
																												// FRENTE

			transicionEspalda[i] = app
					.loadImage("../dataAmids/Personajes/P5/Transiciones/Espalda/Transicion P5 E" + i + ".png"); // TRANSICI�N
			// //
			// ESPALDA

			transicionLado[i] = app
					.loadImage("../dataAmids/Personajes/P5/Transiciones/Lado/Transicion P5 L" + i + ".png"); // TRANSICI�N
			// //
			envenenado[i] = app.loadImage("../dataAmids/Estados/Envenenado/Herido" + i + ".png");
		}

		for (int i = 0; i < 30; i++) {
			extasis[i] = app.loadImage("../dataAmids/Estados/Extasis/Extasis" + i + ".png");
		}

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.ellipse(objetivo.x, objetivo.y, 10, 10);
		animacionMovimientos();
		perseguir();
	}

	@Override
	public void mover() {
		pos.add(dir);
		x = (int) pos.x;
		y = (int) pos.y;
	}

	public void animacionMovimientos() {
		app.imageMode(PApplet.CENTER);
		switch (animacion) {
		case 0:

			if (contador == 12)
				contador = 0;
			if (estado == ENFERMO) {
				app.image(frenteEnfermo[contador], x, y, 125, 125);
			} else {
				app.image(frenteSano[contador], x, y, 125, 125);
			}
			if (contadorExta >= 30) {
				contadorExta = 0;
			}
			if (enExtasis) {
				app.image(extasis[contadorExta], x, y, 125, 125);
			}
			contadorExta++;

			if (enVeneno) {
				app.image(envenenado[contador], x, y, 125, 125);
			}

			contador++;
			break;

		case 1:
			if (contador == 12)
				contador = 0;

			if (estado == ENFERMO) {
				app.image(espaldaEnfermo[contador], x, y, 125, 125);
			} else {
				app.image(espaldaSano[contador], x, y, 125, 125);
			}

			if (contadorExta >= 30) {
				contadorExta = 0;
			}
			if (enExtasis) {
				app.image(extasis[contadorExta], x, y, 125, 125);
			}
			contadorExta++;

			if (enVeneno) {
				app.image(envenenado[contador], x, y, 125, 125);
			}

			contador++;
			break;

		case 2:
			if (contador == 12)
				contador = 0;
			if (estado == ENFERMO) {
				app.image(ladoEnfermo[contador], x, y, 125, 125);
			} else {
				app.image(ladoSano[contador], x, y, 125, 125);
			}

			if (contadorExta >= 30) {
				contadorExta = 0;
			}
			if (enExtasis) {
				app.image(extasis[contadorExta], x, y, 125, 125);
			}
			contadorExta++;

			if (enVeneno) {
				app.image(envenenado[contador], x, y, 125, 125);
			}

			contador++;
			break;

		case 3:
			if (contador == 12)
				contador = 0;
			if (estado == ENFERMO) {
				app.pushMatrix();
				app.scale(-1.0f, 1.0f);
				app.image(ladoEnfermo[contador], -ladoEnfermo[contador].width - x + 125, y, 125, 125);
				app.popMatrix();
			} else {
				app.pushMatrix();
				app.scale(-1.0f, 1.0f);
				app.image(ladoSano[contador], -ladoSano[contador].width - x + 125, y, 125, 125);
				app.popMatrix();
			}
			if (contadorExta >= 30) {
				contadorExta = 0;
			}
			if (enExtasis) {
				app.image(extasis[contadorExta], x, y, 125, 125);
			}
			contadorExta++;

			if (enVeneno) {
				app.image(envenenado[contador], x, y, 125, 125);
			}

			contador++;
			break;
		default:
			break;
		}
		app.imageMode(PApplet.CORNER);

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
		// TODO Auto-generated method stub

		while (true) {
			if (vida > 0) {
				mover();
			} else {
				Mundo.ObtenerInstancia().getEspecies().remove(this);
				this.ecosistema.getEspecies().remove(this);
			}
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) < 50) {
			vida -= 5;
			try {
				lastimador.setEstado(ENFERMO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}