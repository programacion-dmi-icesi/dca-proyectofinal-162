package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Random;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaGomiCabra extends EcosistemaAbstracto {

	private int id, x, y, vista, velPoder = 1;
	private EspecieAbstracta actual;
	private PlantaAbstracta actualPlanta;
	private PApplet app = Mundo.ObtenerInstancia().getApp();
	private LogoGomiCabra boton;

	// ===============================================================================================
	/**
	 * CONSTRUCTOR DE LA CLASE
	 */

	public EcosistemaGomiCabra() {
		super();
		Mundo logoGomi = Mundo.ObtenerInstancia();
		boton = new LogoGomiCabra("./dataGomiCabra/icono.svg", this);// se
																		// agrego
																		// el
																		// logo
		logoGomi.agregarBoton(boton);
	}

	// ===============================================================================================
	/**
	 * PINTA LAS PLANTAS Y LAS GOMICABRAS
	 */
	@Override
	public void dibujar() {

		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {

				actual = iteradorEspecies.next();
				actual.dibujar();
			}

			synchronized (plantas) {
				// CONDICIONES PARA PONER LAS PLANTAS CON EL CLICK
				if (app.dist(boton.getX(), boton.getY(), app.mouseX, app.mouseY) <= 30) {
					if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
						if (app.mouseButton == app.LEFT) {
							plantas.add(new PlantaGomiCabra(this, 0)); // BUENA
						} else if (app.mouseButton == app.RIGHT) {
							plantas.add(new PlantaGomiCabra(this, 1)); // MALA
						}
					}
				}

				/**
				 * EN ESTA PARTE DEL CODIGO SE RECORRE EL ARREGLO DE PLANTAS Y
				 * LAS PINTA
				 */
				Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
				while (iteradorPlantas.hasNext()) {
					actualPlanta = iteradorPlantas.next();
					actualPlanta.dibujar();
				}
			}

			/**
			 * RECORRE LA LIST DE PLANTAS Y DE ESPECIES Y PRGUNTA QUE SI LA
			 * ESPECIE ES DE TIPO HERBIVORO, PARA PODER COMER LA PLANTA
			 */
			for (EspecieAbstracta es : especies) {
				for (PlantaAbstracta planta : plantas) {

					if (es instanceof IHerbivoro) {
						float d = app.dist(planta.getX(), planta.getY(), es.getX(), es.getY());

						if (d < 100) {
							((IHerbivoro) es).comerPlanta(planta);

							PlantaGomiCabra p = (PlantaGomiCabra) planta;
							GomiCabra gomi = (GomiCabra) es;
							/**
							 * AQUI PREGUNTA QUE SI LA PLANTA YA SE LA COMIERON
							 * COMPLETAMENTE PARA QUE SE MUERA
							 */
							if (p.isMuerto())
								plantas.remove(planta);
							GomiCabra esp = (GomiCabra) es;
							/**
							 * AQUI SE LE DA EL PODER CUANDO SE COMA LA PLANTA
							 * BUENA
							 */
							if (p.getId() == 0) {
								if (velPoder <= 5) {
									velPoder++;
								}
								gomi.setVelPoder(velPoder);
							}
							/**
							 * AQUI PREGUNTA QUE SI EL ESTADO DEL HERBIVORO ES
							 * MUERTO PARA REMOVERLO
							 */
							if (esp.isMuerto())
								especies.remove(esp);
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		especies.add(new EspecieGomiHerbivoro(this, vista));
		especies.add(new EspecieGomiHerbivoro(this, vista));

		especies.add(new EspecieGomiCarnivoro(this));
		especies.add(new EspecieGomiCarnivoro(this));

		especies.add(new EspecieGomiCanibal(this, 1));
		especies.add(new EspecieGomiCanibal(this, 1));

		especies.add(new EspecieGomiOmnivoro(this));
		especies.add(new EspecieGomiOmnivoro(this));

		return especies;
	}
	// ===============================================================================================

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		return plantas;
	}
	// ===============================================================================================

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		especies.add(new EspecieGomiHerbivoro(this, vista));
		especies.add(new EspecieGomiHerbivoro(this, vista));
		especies.add(new EspecieGomiCarnivoro(this));
		especies.add(new EspecieGomiCarnivoro(this));
		especies.add(new EspecieGomiCanibal(this, 1));
		especies.add(new EspecieGomiCanibal(this, 1));
		especies.add(new EspecieGomiOmnivoro(this));
		especies.add(new EspecieGomiOmnivoro(this));

		return especies;
	}
	// ===============================================================================================

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}
}
