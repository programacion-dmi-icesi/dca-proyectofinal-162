package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

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
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.HijoBlanco;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieGomiHerbivoro extends GomiCabra implements IApareable, IHerbivoro {

	private boolean procrear = false;

	public EspecieGomiHerbivoro(EcosistemaAbstracto ecosistema, int vista) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		this.vista = vista;

		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroAtras/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroDerecha/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/herbivoro/herbivoroIzquierda/4.png");
		maxVida = vida;
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {

		return null;
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {

				// AQUI TIENE EL BEBE

				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this && especie instanceof IApareable) {

							float d = PApplet.dist(especie.getX(), especie.getY(), this.x, this.y);

							if (d < 100) {
								procrear = true;
							} else {
								procrear = false;
							}

						}
					}
				}

				/*
				 * if (procrear == true) { HijoGomiCabra hijo = new
				 * HijoGomiCabra(ecosistema); hijo.setX(this.x);
				 * hijo.setY(this.y); ecosistema.getEspecies().add(hijo);
				 * 
				 * }
				 */

				Thread.sleep(33);
				vista++;

				if (vista == 3) {
					vista = 0;
				}
				// condición para que coma una planta cada cierto tiempo t
				if (!puedeComer) {
					t++;
					if (t > 500) {
						puedeComer = true;
						t = 0;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo
		return false;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {

		if (puedeComer && victima instanceof PlantaGomiCabra) {
			PlantaGomiCabra p = (PlantaGomiCabra) victima;

			switch (p.getId()) {
			case 0:
				vida += 15;
				if (vida > maxVida)
					vida = maxVida;
				break;
			case 1:
				vida -= 15;
				if (vida < 0) {

					// condición de morir
				}
				break;
			}
			p.mordisco();
			puedeComer = false;

		}

	}

	public boolean isProcrear() {
		return procrear;
	}

	public void setProcrear(boolean procrear) {
		this.procrear = procrear;
	}

}