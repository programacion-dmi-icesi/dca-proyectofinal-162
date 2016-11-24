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

	private HijoGomiCabra h;

	public EspecieGomiHerbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		this.vista = (int) app.random(4);

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
		h = new HijoGomiCabra(ecosistema, this.x, this.y);
		esperar = true;
		return h;
	}

	@Override
	public void run() {
		while (vivo) {
			mover();

			try {
				/*
				 * por cada acción que se realice, se deberá esperar cierto
				 * tiempo para ejecutar la siguiente acción,porque en caso
				 * contrario, se ejecutará repetitivamente la acción mientras
				 * las condiciones se cumplan.
				 */

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

				/*
				 * aqui se recorren las especies, y si el otro es apareable,
				 * llamará el metodo aparear que retornará un hijo que se guarda
				 * en el arraylist
				 */
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this && especie instanceof IApareable) {
							IApareable apareable = (IApareable) especie;
							float d = app.dist(especie.getX(), especie.getY(), this.x, this.y);

							if (!esperar) {
								if (d < 100) {
									EspecieAbstracta hijo = aparear(apareable);
									ecosistema.getEspecies().add(hijo);

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
		if (victima instanceof PlantaGomiCabra) {
			System.out.println("se come una planta ");
			PlantaGomiCabra p = (PlantaGomiCabra) victima;
			switch (p.getId()) {
			case 0:
				vida += 15;

				if (vida < 50) {
					estado = NORMAL;
				}

				if (vida > maxVida)
					vida = maxVida;
				break;
			case 1:
				vida -= 15;
				if (vida < 50) {
					estado = ENFERMO;
				}

				if (vida < 0) {
					// condición de morir
				}
				break;
			}
			p.mordisco();
			esperar = true;
			System.out.println("esperando siguiente acción");

		}
	}

}