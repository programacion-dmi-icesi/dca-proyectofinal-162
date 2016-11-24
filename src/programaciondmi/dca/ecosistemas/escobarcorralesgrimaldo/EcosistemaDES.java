package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaDES extends EcosistemaAbstracto {
	private PApplet app = Mundo.ObtenerInstancia().getApp();
	private Logo logoMuf;
	private Planta planta;
	private ArrayList<Planta> plantas = new ArrayList<Planta>();
	private int tipoP = 1, x, y, clasePlanta, vista;

	public EcosistemaDES() {

		super();

		Mundo app2 = Mundo.ObtenerInstancia();
		logoMuf = new Logo("../data/mufs.svg", this);
		app2.agregarBoton(logoMuf);

	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		Carnivoro car = new Carnivoro(this);
		especies.add(car);

		Omnivoro omn = new Omnivoro(this);
		especies.add(omn);

		Herbivoro herb = new Herbivoro(this);
		especies.add(herb);

		Canibal can = new Canibal(this);
		especies.add(can);

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

		Herbivoro herb = new Herbivoro(this);
		especies.add(herb);

		Canibal can = new Canibal(this);
		especies.add(can);

		Carnivoro car = new Carnivoro(this);
		especies.add(car);

		Omnivoro omn = new Omnivoro(this);
		especies.add(omn);

		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}

	@Override
	public void dibujar() {

		synchronized (plantas) {
			if (app.dist(logoMuf.getX(), logoMuf.getY(), app.mouseX, app.mouseY) <= 30) {
				if (Mundo.ObtenerInstancia().getApp().mousePressed == true) {
					if (app.mouseButton == app.LEFT) {
						plantas.add(new Planta(0));
					} else if (app.mouseButton == app.RIGHT) {
						plantas.add(new Planta(1));
					}
				}
			}

			for (int i = 0; i < plantas.size(); i++) {
				Planta plant = plantas.get(i);
				plant.dibujar();
			}

		}
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}

			for (EspecieAbstracta herbi : especies) {
				for (PlantaAbstracta planti : plantas) {

					if (herbi instanceof IHerbivoro) {
						float d = app.dist(((Planta) planti).getX(), ((Planta) planti).getY(), herbi.getX(),
								herbi.getY());

						if (d < 100) {

							planta = (Planta) planti;

							if (vista <= 1) {
								vista++;
							}

							planta.setViva(vista);

						}
					}
				}
			}
		}
	}
}
