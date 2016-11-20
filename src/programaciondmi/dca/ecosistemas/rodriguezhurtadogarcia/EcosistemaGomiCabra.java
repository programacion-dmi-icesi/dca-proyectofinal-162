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

	private int id, x, y, vista;
	private EspecieAbstracta actual;
	private PlantaAbstracta actualPlanta;
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
		//
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {

				actual = iteradorEspecies.next();
				actual.dibujar();
			}

			synchronized (plantas) {
				if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
					if (app.mouseButton == app.LEFT) {
						plantas.add(new PlantaGomiCabra(this, 0));
					} else if (app.mouseButton == app.RIGHT) {
						plantas.add(new PlantaGomiCabra(this, 1));
					}
				}
				Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
				while (iteradorPlantas.hasNext()) {
					actualPlanta = iteradorPlantas.next();
					actualPlanta.dibujar();
				}
			}

			for (EspecieAbstracta es : especies) {
				for (PlantaAbstracta planta : plantas) {

					if (es instanceof IHerbivoro) {
						float d = app.dist(((PlantaGomiCabra) planta).getX(), ((PlantaGomiCabra) planta).getY(), es.getX(), es.getY());

						if (d < 100) {
							((IHerbivoro) es).comerPlanta(planta);

							PlantaGomiCabra p = (PlantaGomiCabra) planta;
							if (p.isMuerto())
								plantas.remove(planta);
							GomiCabra esp = (GomiCabra) es;
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
		// EspecieGomiHerbivoro gomiHerbivoro = new EspecieGomiHerbivoro(this,
		// vista);

		especies.add(new EspecieGomiHerbivoro(this, vista));
		especies.add(new EspecieGomiHerbivoro(this, vista));

		especies.add(new EspecieGomiCarnivoro(this));
		especies.add(new EspecieGomiCarnivoro(this));

		especies.add(new EspecieGomiCanibal(this, 1));
		especies.add(new EspecieGomiCanibal(this, 1));

		especies.add(new EspecieGomiOmnivoro(this));
		especies.add(new EspecieGomiOmnivoro(this));

		especies.add(new HijoGomiCabra(this));
		especies.add(new HijoGomiCabra(this));

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		especies.add(new EspecieGomiHerbivoro(this, vista));

		especies.add(new EspecieGomiCarnivoro(this));

		especies.add(new EspecieGomiCanibal(this, 1));

		especies.add(new EspecieGomiOmnivoro(this));

		especies.add(new HijoGomiCabra(this));
		return especies;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}
}
