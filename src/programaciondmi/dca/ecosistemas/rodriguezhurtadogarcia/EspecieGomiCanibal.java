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

public class EspecieGomiCanibal extends GomiCabra implements ICanibal {

	public EspecieGomiCanibal(EcosistemaGomiCabra ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/4.png");
		maxVida = vida;

		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		try {
			victima.setEstado(MUERTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (vivo) {
			mover();
			try {

				// para a cualquier objeto gomicabra que se encuentre cerca.
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this) {
							if (especie instanceof GomiCabra) {
								float d = PApplet.dist(especie.getX(), especie.getY(), this.x, this.y);
								if (!esperar) {
									if (d < 100) {
										comer(especie);
										especie.setEstado(MUERTO);
										((GomiCabra) especie).setVivo(false);

										System.out.println("canibal mata!");
										ecosistema.getEspecies().remove(especie);

										break;
									}
								}

							}

						}
					}
				}

				Thread.sleep(33);
				vista++;
				if (vista == 3) {
					vista = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("se ha muerto!");
			}

		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		return false;
	}

}