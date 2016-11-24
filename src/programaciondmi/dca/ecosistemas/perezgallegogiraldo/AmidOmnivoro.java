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
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidOmnivoro extends EspecieAbstracta implements IOmnivoro, IApareable {

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

	private int contador, contadorExta;

	private float energia;
	private PVector dir;
	private PVector pos;
	private int animacion;
	private int ciclo;
	private int fuerza;
	private boolean encontro;
	private boolean puedeComer = true;
	private EspecieAbstracta comestible;
	private EspecieAbstracta pareja;
	private boolean encontroPlanta, encontroPareja, puedeAparear, enExtasis, enVeneno;
	private PlantaAbstracta plantaCercana;
	private final int LIMITE_COMER = 150;
	private final int LIMITE_APAREA = 150;

	public AmidOmnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.x = (int) app.random(-500, 500);
		this.y = (int) app.random(-500, 500);
		this.vida = 50;
		this.energia = 250;
		this.fuerza = 100;

		dir = new PVector(0, 0);
		pos = new PVector(x, y);

		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Frente/P3 F Enfermo" + i + ".png"); // FRENTE
			// ENFERMO

			frenteSano[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Frente/P3 F Sano" + i + ".png"); // FRENTE
																											// SANO

			espaldaEnfermo[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Espalda/P3 E Enfermo" + i + ".png"); // ESPALDA
																													// ENFERMO

			espaldaSano[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Espalda/P3 E Sano" + i + ".png"); // ESPALDA
			// SANO

			ladoEnfermo[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Lado/P3 L Enfermo" + i + ".png"); // LADO
			// ENFERMO

			ladoSano[i] = app.loadImage("../dataAmids/Personajes/P3/P3 Lado/P3 L Sano" + i + ".png"); // LADO
																										// SANO

			transicionFrente[i] = app
					.loadImage("../dataAmids/Personajes/P3/Transiciones/Frente/Transicion P3 F" + i + ".png"); // TRANSICI�N
			// FRENTE

			transicionEspalda[i] = app
					.loadImage("../dataAmids/Personajes/P3/Transiciones/Espalda/Transicion P3 E" + i + ".png"); // TRANSICI�N
			// ESPALDA

			transicionLado[i] = app
					.loadImage("../dataAmids/Personajes/P3/Transiciones/Lado/Transicion P3 L" + i + ".png"); // TRANSICI�N
			// LADO
			envenenado[i] = app.loadImage("../dataAmids/Estados/Envenenado/Herido" + i + ".png");
		}

		for (int i = 0; i < 30; i++) {
			extasis[i] = app.loadImage("../dataAmids/Estados/Extasis/Extasis" + i + ".png");
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
				Thread.sleep(20);
				ciclo++;
			} catch (Exception e) {

			}
		}
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

			if (puedeAparear) {
				buscarPareja();
				if (pareja != null && pareja instanceof IApareable) {
					aparear((IApareable) pareja);
				}

			}

			pos.add(dir);
			x = (int) pos.x;
			y = (int) pos.y;

		}
	}

	@Override
	public void dibujar() {
		animacionMovimientos();
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

			if (distancia < 20 && puedeComer) {
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
					enVeneno = true;
					enExtasis = false;
					energia += 10;
					vida -= 10;

				} else if (victima instanceof PlantaSaludable) {
					estado = EXTASIS;
					enVeneno = false;
					enExtasis = true;
					energia += 10;
					vida += 10;
					System.out.println(vida);

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

	private void buscarPareja() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterator = all.listIterator();

		while (!encontroPareja && iterator.hasNext()) {
			EspecieAbstracta com = iterator.next();

			if (!com.equals(this)) {

				if (com instanceof IApareable) {

					float d = PApplet.dist(x, y, com.getX(), com.getY());
					if (d < energia * 2 && puedeAparear) {
						pareja = com;
						encontroPareja = true;
					}
				}
			}
		}

		if (encontroPareja) {

			perseguir(new PVector(pareja.getX(), pareja.getY()));
			calcularImagen(new PVector(pareja.getX(), pareja.getY()));
		}

		if (!encontroPareja) {
			pareja = null;
		}
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		AmidHijo hijo = new AmidHijo(ecosistema);
		hijo.setX(this.x);
		hijo.setY(this.y);
		ecosistema.agregarEspecie(hijo);
		return hijo;
	}

}