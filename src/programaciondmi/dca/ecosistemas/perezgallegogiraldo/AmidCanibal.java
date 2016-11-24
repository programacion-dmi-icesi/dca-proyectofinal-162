package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.List;
import java.util.ListIterator;

import processing.core.PImage;
import processing.core.PVector;
import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
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
	private EspecieAbstracta comestible;
	private PlantaAbstracta plantaCercana;
	private PVector dir;
	private PVector pos;
	private PVector objetivo;
	private int animacion;
	private int ciclo;
	private boolean encontro, encontroPlanta, puedeComer = true;

	// Constantes
	private final int LIMITE_COMER = 150;

	public AmidCanibal(EcosistemaAbstracto ecosistema) {

		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.x = (int) app.random(-500, 500);
		this.y = (int) app.random(-500, 500);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		objetivo = new PVector(-300, 300);

		dir = new PVector(0, 0);
		pos = new PVector(x, y);

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
					.loadImage("../data/Personajes/P1/Transiciones/Frente/Transicion P1 F" + i + ".png"); // TRANSICI�N
																											// FRENTE

			transicionEspalda[i] = app
					.loadImage("../data/Personajes/P1/Transiciones/Espalda/Transicion P1 E" + i + ".png"); // TRANSICI�N
																											// ESPALDA

			transicionLado[i] = app.loadImage("../data/Personajes/P1/Transiciones/Lado/Transicion P1 L" + i + ".png"); // TRANSICI�N
																														// LADO
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
	public void dibujar() {
		// TODO Auto-generated method stub
		app.ellipse(objetivo.x, objetivo.y, 10, 10);
		app.stroke(255, 0, 255);
		app.strokeWeight(3);
		app.arc(x, y, 30, 30, 0, 10);
		app.noStroke();
		animacionesMovimiento();
		barraVida();
	}

	@Override
	public void mover() {
		if (energia > 0) {
			if (energia > LIMITE_COMER) {
				buscarComida();
				if (comestible != null) {
					comer(comestible);
				}

				if (!encontro || energia < LIMITE_COMER) {
					if (ciclo % 90 == 0) {
						int targetX = (int) app.random(-500, 500);
						int targetY = (int) app.random(-500, 500);
						perseguir(new PVector(targetX, targetY));
						calcularImagen(new PVector(targetX, targetY));
					}
				}
			} else {
				buscarPlanta();
				if (plantaCercana != null) {
					comerPlanta(plantaCercana);
				}

				if (ciclo % 90 == 0) {
					int targetX = (int) app.random(-500, 500);
					int targetY = (int) app.random(-500, 500);
					perseguir(new PVector(targetX, targetY));
					calcularImagen(new PVector(targetX, targetY));
				}
			}
			pos.add(dir);
			x = (int) pos.x;
			y = (int) pos.y;
		}

	}

	public void animacionesMovimiento() {
		app.imageMode(PApplet.CENTER);
		switch (animacion) {
		case 0:

			if (contador == 12)
				contador = 0;
			app.image(frenteSano[contador], x, y);
			contador++;
			break;

		case 1:
			if (contador == 12)
				contador = 0;
			app.image(espaldaSano[contador], x, y);
			contador++;
			break;

		case 2:
			if (contador == 12)
				contador = 0;
			app.image(ladoSano[contador], x, y);
			contador++;
			break;

		case 3:
			if (contador == 12)
				contador = 0;
			app.pushMatrix();
			app.scale(-1.0f, 1.0f);
			app.image(ladoSano[contador], -ladoSano[contador].width - x + 100, y);
			app.popMatrix();
			contador++;

			break;
		default:
			break;
		}
		app.imageMode(PApplet.CORNER);
	}

	public void perseguir(PVector objetivo) {
		PVector distanX = PVector.sub(objetivo, pos);
		distanX.y = 0;
		PVector distanY = PVector.sub(objetivo, pos);
		distanY.x = 0;
		float dX = distanX.mag();

		distanX.normalize();
		distanY.normalize();

		PVector direccionX = PVector.sub(distanX, dir);
		dir.add(direccionX);

		if (dX <= 0) {
			PVector direccionY = PVector.sub(distanY, dir);
			dir.add(direccionY);
		}

	}

	public void calcularImagen(PVector objetivo) {
		PVector distanX = PVector.sub(objetivo, pos);
		distanX.y = 0;
		PVector distanY = PVector.sub(objetivo, pos);
		distanY.x = 0;
		float dX = distanX.mag();

		distanX.normalize();
		distanY.normalize();

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

		}
	}

	private void buscarComida() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterator = all.listIterator();

		while (!encontro && iterator.hasNext()) {
			EspecieAbstracta com = iterator.next();

			if (!com.equals(this)) {

				if (com instanceof AmidOmnivoro || com instanceof AmidCanibal || com instanceof AmidHijo
						|| com instanceof AmidHerbivoro) {

					float d = PApplet.dist(x, y, com.getX(), com.getY());
					if (d < energia * 2 && puedeComer) {
						comestible = com;
						encontro = true;
					}
				}
			}
		}

		if (encontro) {

			perseguir(new PVector(comestible.getX(), comestible.getY()));
			calcularImagen(new PVector(comestible.getX(), comestible.getY()));
		}

		if (!encontro) {
			comestible = null;
		}
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				vida += 2;
				energia -= 10;
				victima.recibirDano(this);
				encontro = false;
			}
		}
	}

	public void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();

		while (!encontroPlanta && iterador.hasNext()) {
			PlantaAbstracta p = iterador.next();

			float distancia = PApplet.dist(x, y, p.getX(), p.getY());

			if (distancia < energia * 2 && puedeComer) {
				plantaCercana = p;
				encontroPlanta = true;
			}
		}

		if (encontroPlanta) {
			perseguir(new PVector(plantaCercana.getX(), plantaCercana.getY()));
			calcularImagen(new PVector(plantaCercana.getX(), plantaCercana.getY()));
		} else {
			plantaCercana = null;
		}

	}

	public void comerPlanta(PlantaAbstracta victima) {
		if (victima != null && puedeComer) {
			float d = PApplet.dist(x, y, victima.getX(), victima.getY());
			if (d < 15) {
				if (victima instanceof PlantaVenenosa) {
					estado = ENVENENADO;
					energia += 10;
					vida -= 10;
				} else if (victima instanceof PlantaSaludable) {
					estado = EXTASIS;
					energia += 10;
					vida += 10;
				}
				if (victima.recibirDano(this)) {
					victima.recibirDano(this);
				} else {
					Mundo.ObtenerInstancia().getPlantas().remove(victima);
					this.ecosistema.getPlantas().remove(victima);
				}

				puedeComer = false;
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

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana=" + comestible
				+ ", dir=" + dir + ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	public void barraVida() {
		app.fill(255, 95);
		app.rect(x - 60, y - 120, 120, 70, 8);
		app.fill(255, 50, 90);
		app.rect(x - 50, y - 70, vida, 10, 100);
		app.noFill();
		app.stroke(255, 50, 90);
		app.rect(x - 50, y - 70, 100, 10, 100);
		app.noFill();
		app.stroke(252, 182, 35);
		app.rect(x - 50, y - 90, 100, 10, 100);
		app.fill(252, 182, 35);
		app.rect(x - 50, y - 90, vida, 10, 100);
		app.textAlign(PApplet.CENTER);
		app.fill(252, 182, 35);
		app.text("C A N I B A L", x, y - 105);
		app.textAlign(PApplet.CORNER);
	}
}