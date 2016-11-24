package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Venenosa extends PlantaAbstracta {

	private int recursos;
	private PImage[] plant;

	public Venenosa(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();

		this.plant = new PImage[4];

		for (int i = 0; i < plant.length; i++) {
			this.plant[i] = app.loadImage("propheticData/plantaMal/mala_" + i + ".png");
		}

		recursos = 0;

		this.x = x;
		this.y = y;

		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void run() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(plant[recursos], x, y,100,100);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (recursos < 2) {
			recursos++;
			return true;
		}
		return false;
	}

	public int getRecursos() {
		return recursos;
	}

	public void setRecursos(int recursos) {
		this.recursos = recursos;
	}

}
