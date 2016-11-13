package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaGomiCabra extends EcosistemaAbstracto {

	private int id, x, y, vista;
	private ArrayList<PlantaGomiCabra> plantas = new ArrayList<PlantaGomiCabra>();
	private EspecieAbstracta actual;
	private LogoGomiCabra boton;

	public EcosistemaGomiCabra() {
		super();

		Mundo logoGomi = Mundo.ObtenerInstancia();
		boton = new LogoGomiCabra("./dataGomiCabra/icono.svg", this);
		logoGomi.agregarBoton(boton);
	}

	@Override
	public void dibujar() {
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				actual = iteradorEspecies.next();
				actual.dibujar();

			}
		}

		// dice si es mala o buena

		if (boton.getCrearB() == true) {
			id = 0;// buena
		}
		if (boton.getCrearM() == true) {
			id = 1; // mala
		}

		if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
			plantas.add(new PlantaGomiCabra(this, id));
		}

		System.out.println(boton.getCrearM());

		for (int i = 0; i < plantas.size(); i++) {
			PlantaGomiCabra planti = plantas.get(i);
			planti.dibujar();
		}

	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		EspecieGomiCanibal gomiCanibal = new EspecieGomiCanibal(this, vista);
		especies.add(gomiCanibal);

		EspecieGomiCarnivoro gomiCarnivoro = new EspecieGomiCarnivoro(this);
		especies.add(gomiCarnivoro);

		EspecieGomiHerbivoro gomiHerbivoro = new EspecieGomiHerbivoro(this, vista);
		especies.add(gomiHerbivoro);

		EspecieGomiOmnivoro gomiOmnivoro = new EspecieGomiOmnivoro(this);
		especies.add(gomiOmnivoro);

		gomiHerbivoro = new EspecieGomiHerbivoro(this, vista);
		especies.add(gomiHerbivoro);

		gomiCanibal = new EspecieGomiCanibal(this, vista);
		especies.add(gomiCanibal);

		gomiCarnivoro = new EspecieGomiCarnivoro(this);
		especies.add(gomiCarnivoro);

		gomiOmnivoro = new EspecieGomiOmnivoro(this);
		especies.add(gomiOmnivoro);

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		// TODO LLenar las lista de plantas iniciales

		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub

		EspecieGomiHerbivoro gomiHerbivoro = new EspecieGomiHerbivoro(this, vista);
		especies.add(gomiHerbivoro);

		EspecieGomiCanibal gomiCanibal = new EspecieGomiCanibal(this, vista);
		especies.add(gomiCanibal);

		EspecieGomiCarnivoro gomiCarnivoro = new EspecieGomiCarnivoro(this);
		especies.add(gomiCarnivoro);

		EspecieGomiOmnivoro gomiOmnivoro = new EspecieGomiOmnivoro(this);
		especies.add(gomiOmnivoro);

		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}

}
