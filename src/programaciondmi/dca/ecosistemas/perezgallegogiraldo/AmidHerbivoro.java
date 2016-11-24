package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidHerbivoro extends EspecieAbstracta implements IHerbivoro {
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

	private PlantaAbstracta plantaCercana;

	private float energia;
	private int contador;

	private PVector dir;
	private PVector pos;
	private int animacion;
	private int ciclo;

	private boolean puedeComer = true, encontro;

	public AmidHerbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.x = (int) app.random(-500, 500);
		this.y = (int) app.random(-500, 500);
		this.vida = 50;
		this.energia = 250;

		dir = new PVector(0, 0);
		pos = new PVector(x, y);

		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Frente/P4 F Enfermo" + i + ".png"); // FRENTE
																											// ENFERMO

			frenteSano[i] = app.loadImage("../data/Personajes/P4/P4 Frente/P4 F Sano" + i + ".png"); // FRENTE
																										// SANO

			espaldaEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Espalda/P4 E Enfermo" + i + ".png"); // ESPALDA
																												// ENFERMO

			espaldaSano[i] = app.loadImage("../data/Personajes/P4/P4 Espalda/P4 E Sano" + i + ".png"); // ESPALDA
																										// SANO

			ladoEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Lado/P4 L Enfermo" + i + ".png"); // LADO
																										// ENFERMO

			ladoSano[i] = app.loadImage("../data/Personajes/P4/P4 Lado/P4 L Sano" + i + ".png"); // LADO
																									// SANO
			/*
			 * transicionFrente[i] = app.loadImage(
			 * "../data/Personajes/P4/Transiciones/Frente/Transicion P4 F" + i +
			 * ".png"); // TRANSICI�N FRENTE
			 * 
			 * transicionEspalda[i] = app.loadImage(
			 * "../data/Personajes/P4/Transiciones/Espalda/Transicion P4 E" + i
			 * + ".png"); // TRANSICI�N ESPALDA
			 * 
			 * transicionLado[i] = app.loadImage(
			 * "../data/Personajes/P4/Transiciones/Lado/Transicion P4 L" + i +
			 * ".png"); // TRANSICI�N LADO
			 */
		}

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		animacionesMovimiento();
	}

	@Override
	public void mover() {
		if (energia > 0 && vida > 0) {
			buscarPlanta();
			if (plantaCercana != null) {
				comerPlanta(plantaCercana);
			}
			if (!encontro) {
				if (ciclo % 90 == 0) {
					int targetX = (int) app.random(-500, 500);
					int targetY = (int) app.random(-500, 500);
					perseguir(new PVector(targetX, targetY));
					calcularImagen(new PVector(targetX, targetY));
					puedeComer = true;
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

	private void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();

		while (!encontro && iterador.hasNext()) {
			PlantaAbstracta p = iterador.next();

			float distancia = PApplet.dist(x, y, p.getX(), p.getY());

			if (distancia < energia * 2 && puedeComer) {
				plantaCercana = p;
				encontro = true;
			}
		}

		if (encontro) {
			perseguir(new PVector(plantaCercana.getX(), plantaCercana.getY()));
			calcularImagen(new PVector(plantaCercana.getX(), plantaCercana.getY()));
		} else {
			plantaCercana = null;
		}

	}

	@Override
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
					System.out.println(vida);

				}
				
				Mundo.ObtenerInstancia().getPlantas().remove(victima);
				this.ecosistema.getPlantas().remove(victima);

				puedeComer = false;
			}

		}

	}
}