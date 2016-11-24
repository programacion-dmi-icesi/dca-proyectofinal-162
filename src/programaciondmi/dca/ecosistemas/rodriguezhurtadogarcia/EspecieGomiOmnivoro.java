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
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

/*VISTAS
1=delante
2=atras
3=izquierda
4=derecha
*/

public class EspecieGomiOmnivoro extends GomiCabra implements IOmnivoro {

	// Constantes
	private final int LIMITE_APAREO = 100;

	public EspecieGomiOmnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;

		app = Mundo.ObtenerInstancia().getApp();

		izquierda[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/4.png");

		// OMNIVORO IZQUIERDO
		derecha[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/4.png");

		// OMNIVORO FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/4.png");

		// OMNIVORO ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/4.png");
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
				// para comerse a los demás
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this) {

							float d = app.dist(especie.getX(), especie.getY(), this.x, this.y);

							if (!esperar) {
								if (d < 100) {
									comer(especie);
									((GomiCabra) especie).setVivo(vivo);
									ecosistema.getEspecies().remove(especie);

									break;
								}
							}
						}
					}
				}

				/*
				 * Aqui se recorren las plantas y si, esta esta cerca de esta
				 * clase, se llamará el metodo comerPlanta.
				 */
				synchronized (ecosistema.getPlantas()) {
					// List<PlantaAbstracta> plantas = ecosistema.getPlantas();
					for (PlantaAbstracta planta : ecosistema.getPlantas()) {
						float d = app.dist(planta.getX(), planta.getY(), this.x, this.y);

						if (d < 100) {
							if (!esperar) {
								comerPlanta(planta);

							}
						}
					}
				}
				Thread.sleep(33);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		return false;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub

	}

}