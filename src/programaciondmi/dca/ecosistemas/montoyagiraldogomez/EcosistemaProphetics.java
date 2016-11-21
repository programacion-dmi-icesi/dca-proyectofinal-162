package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.exceptions.EcosistemaException;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaProphetics extends EcosistemaAbstracto {

	protected PImage[] display, plants;
	private int time;
	private boolean colocar, puedeColocar;

	public EcosistemaProphetics() {
		super();

		PApplet app = Mundo.ObtenerInstancia().getApp();

		Icono ico = new Icono("data/marca.svg", this);
		Mundo.ObtenerInstancia().agregarBoton(ico);

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

		PApplet app = Mundo.ObtenerInstancia().getApp();

		Venenosa veneno = new Venenosa((int) app.random(20, app.width - 20), (int) app.random(20, app.height - 20));
		plantas.add(veneno);

		Hojas buena = new Hojas((int) app.random(20, app.width - 20), (int) app.random(20, app.height - 20));
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

		PApplet app = Mundo.ObtenerInstancia().getApp();

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

		PApplet app = Mundo.ObtenerInstancia().getApp();
		
		if(app.frameCount%30==0){
			puedeColocar=true;
		}

		int camX = Mundo.ObtenerInstancia().getCamX();
		int camY = Mundo.ObtenerInstancia().getCamY();

		if (colocar && puedeColocar) {

			if (app.mousePressed) {

				if (app.mouseButton == PConstants.LEFT) {
					System.out.println("Me los papie");
					plantas.add(
							new Hojas(app.mouseX - ((app.width) - camX), (int) (app.mouseY - ((app.height) - camY))));
					puedeColocar=false;
				} else if (app.mouseButton == PConstants.RIGHT) {
					plantas.add(new Venenosa(app.mouseX - ((app.width) - camX),
							app.mouseY - ((app.height) - camY)));
					puedeColocar=false;
				}
			}
		}
	}

	public void setColocar(boolean colocar) {
		this.colocar = colocar;
	}

}
