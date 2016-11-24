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

	private int id, x, y;
	private EspecieAbstracta actual;

	private PApplet app = Mundo.ObtenerInstancia().getApp();
	private LogoGomiCabra boton;

	public EcosistemaGomiCabra() {
		super();

		Mundo logoGomi = Mundo.ObtenerInstancia();
		boton = new LogoGomiCabra("./dataGomiCabra/icono.svg", this);
		logoGomi.agregarBoton(boton);
	}

	@Override
	public void dibujar() {

		synchronized (plantas) {

			if (app.dist(boton.getX(), boton.getY(), app.mouseX, app.mouseY) <= 30) {
				if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
					if (app.mouseButton == app.LEFT) {
						plantas.add(new PlantaGomiCabra(this, 0));
					} else if (app.mouseButton == app.RIGHT) {
						plantas.add(new PlantaGomiCabra(this, 1));
					}
				}

			}
			// pinta las plantas.
			Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
			PlantaAbstracta actualPlanta;
			while (iteradorPlantas.hasNext()) {
				actualPlanta = iteradorPlantas.next();
				if (actualPlanta instanceof PlantaGomiCabra) {
					PlantaGomiCabra gomiPlanta = (PlantaGomiCabra) actualPlanta;

					gomiPlanta.dibujar();

				}

			}

			for (PlantaAbstracta planta : plantas) {

				// remueve la planta cuando ya es comida completamente.

				if (planta instanceof PlantaGomiCabra) {
					PlantaGomiCabra gomiPlanta = (PlantaGomiCabra) planta;

					gomiPlanta.dibujar();
					if (gomiPlanta.isMuerto()) {
						plantas.remove(planta);
						break;
					}
				}
			}
		}

		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				actual = iteradorEspecies.next();
				actual.dibujar();
			}

			for (EspecieAbstracta especie : especies) {
				// remueve la planta cuando ya es comida completamente.
				if (especie.getEstado() == EspecieAbstracta.MUERTO) {

					especies.remove(especie);
					System.out.println("se ha eliminado una especie");
					break;
				}
			}

		}
	}

	@Override

	/**
	 * Aquí se crean las especies iniciales.
	 */
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especiesIniciales = new LinkedList<EspecieAbstracta>();

		synchronized (especies) {

			especiesIniciales.add(new EspecieGomiHerbivoro(this));
			especiesIniciales.add(new EspecieGomiHerbivoro(this));
			especiesIniciales.add(new EspecieGomiHerbivoro(this));
			especiesIniciales.add(new EspecieGomiHerbivoro(this));

			especiesIniciales.add(new EspecieGomiCarnivoro(this));
			especiesIniciales.add(new EspecieGomiCarnivoro(this));

			especiesIniciales.add(new EspecieGomiCanibal(this));
			especiesIniciales.add(new EspecieGomiCanibal(this));
			especiesIniciales.add(new EspecieGomiCanibal(this));

			/*
			 * especiesIniciales.add(new EspecieGomiOmnivoro(this));
			 * especiesIniciales.add(new EspecieGomiOmnivoro(this));
			 * especiesIniciales.add(new EspecieGomiOmnivoro(this));
			 * especiesIniciales.add(new EspecieGomiOmnivoro(this));
			 */
		}

		return especiesIniciales;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		return plantas;
	}

	@Override
	/**
	 * aqui se crean los individuos queu saldrán periodicamente
	 * 
	 */
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especiesG = new LinkedList<EspecieAbstracta>();
		synchronized (especies) {

			especiesG.add(new EspecieGomiHerbivoro(this));
			especiesG.add(new EspecieGomiHerbivoro(this));
			especiesG.add(new EspecieGomiHerbivoro(this));
			especiesG.add(new EspecieGomiHerbivoro(this));
			especiesG.add(new EspecieGomiHerbivoro(this));
			especiesG.add(new EspecieGomiHerbivoro(this));

			especiesG.add(new EspecieGomiCarnivoro(this));
			// especiesG.add(new EspecieGomiOmnivoro(this));
			// especiesG.add(new EspecieGomiCanibal(this));

		}

		return especiesG;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}
}
