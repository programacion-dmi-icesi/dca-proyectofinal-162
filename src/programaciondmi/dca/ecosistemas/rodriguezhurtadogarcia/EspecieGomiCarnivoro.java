package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieGomiCarnivoro extends GomiCabra implements ICarnivoro {

	public EspecieGomiCarnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/4.png");
		maxVida = vida;

		// System.out.println(this);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		try {
			// la vida indica el estado de la especie, si este se enferma, se
			// pondrá verde y cambiará su estado
			if (victima.getEstado() == ENFERMO) {
				vida = 49;
			} else {
				vida = 100;
			}

			victima.setEstado(MUERTO);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		esperar = true;

	}

	@Override
	public void run() {
		while (vivo) {
			mover();
			try {

				/*
				 * metodo que inidica que debe comerse a cualquier especie cuya
				 * distancia a él sea menor a 100
				 */
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this) {

							float d = PApplet.dist(especie.getX(), especie.getY(), this.x, this.y);

							if (!esperar) {
								if (d < 100) {
									comer(especie);
									especie.setVivo(false);
									/// ecosistema.getEspecies().remove(especie);
									System.out.println("carnivoro mata!");
									break;
								}
							}

						}

					}
				}

				Thread.sleep(33);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		System.err.println("el carnivoro se ha muerto");

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		return false;
	}

}