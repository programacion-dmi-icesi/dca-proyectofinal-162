package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaGomiCabra extends EcosistemaAbstracto {

	private int cambio = 1, id, x, y, vista;
	private ArrayList<PlantaGomiCabra> plantas = new ArrayList<PlantaGomiCabra>();
	private EspecieAbstracta actual;

	public EcosistemaGomiCabra() {
		super();

		Mundo logoGomi = Mundo.ObtenerInstancia();
		LogoGomiCabra boton = new LogoGomiCabra("./data/icono.svg", this);
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

		x = Mundo.ObtenerInstancia().getApp().mouseX;
		y = Mundo.ObtenerInstancia().getApp().mouseY;

		if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
			if (Mundo.ObtenerInstancia().getApp().frameCount % 20 == 0) {
				cambio++;
			}
			plantas.add(new PlantaGomiCabra(this, id, x, y));
		}

		// dice si es mala o buena

		if (cambio % 2 == 0) {
			id = 0;// buena
		} else {
			id = 1; // mala
		}

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

		PlantaGomiCabra plantaBuena = new PlantaGomiCabra(this, id, x, y);
		plantas.add(plantaBuena);

		plantaBuena = new PlantaGomiCabra(this, id, x, y);
		plantas.add(plantaBuena);

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

		PlantaGomiCabra plantaBuena = new PlantaGomiCabra(this, id, x, y);
		plantas.add(plantaBuena);

		return null;
	}

}
