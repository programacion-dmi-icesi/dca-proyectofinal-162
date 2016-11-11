package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaProphetics extends EcosistemaAbstracto {

	protected PImage[] display,plants;

	public EcosistemaProphetics() {
		super();

		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.display = new PImage[5];
		this.plants = new PImage[2];

		this.display[0] = app.loadImage("Canibal.png");
		this.display[1] = app.loadImage("Depredador.png");
		this.display[2] = app.loadImage("Herb.png");
		this.display[3] = app.loadImage("Hijo.png");
		this.display[4] = app.loadImage("Murcielago.png");
		
		this.plants[0] = app.loadImage("carnivora1.png");
		this.plants[1] = app.loadImage("normal1.png");
		
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		BuhoApareable apareable = new BuhoApareable(this);
		especies.add(apareable);

		BuhoCanibal canibal = new BuhoCanibal(this);
		especies.add(canibal);

		MurHerbivoro murcielago = new MurHerbivoro(this);
		especies.add(murcielago);
		
		BuhoDepredador depredador = new BuhoDepredador(this);
		especies.add(depredador);

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {

		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		Venenosa veneno = new Venenosa();
		plantas.add(veneno);
		
		Hojas buena = new Hojas();
		plantas.add(buena);

		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {

		BuhoCanibal canibal = new BuhoCanibal(this);
		especies.add(canibal);

		BuhoApareable apareable = new BuhoApareable(this);
		especies.add(apareable);
		
		MurHerbivoro murcielago = new MurHerbivoro(this);
		especies.add(murcielago);
		
		BuhoDepredador depredador = new BuhoDepredador(this);
		especies.add(depredador);

		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {

		Venenosa veneno = new Venenosa();
		plantas.add(veneno);
		
		Hojas buena = new Hojas();
		plantas.add(buena);
		return null;
	}

	@Override
	public void dibujar() {

		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlanta = plantas.iterator();
			while (iteradorPlanta.hasNext() && !plantas.isEmpty()) {
				PlantaAbstracta actual = iteradorPlanta.next();
				actual.dibujar();
				if (actual instanceof Venenosa) {
					if (((Venenosa) actual).getRecursos() <= 0) {
						// plantas.remove(actual);
					}
				}
			}
		}

		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
		}
	}

}
