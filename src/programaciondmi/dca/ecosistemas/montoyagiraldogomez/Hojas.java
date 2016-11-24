package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hojas extends PlantaAbstracta {

	private int recursos;
	private PImage[] plant;

	public Hojas(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();

		this.plant = new PImage[4];

		for (int i = 0; i < plant.length; i++) {
			this.plant[i] = app.loadImage("propheticData/plantaBue/buena_" + i + ".png");
		}

		this.x = x;
		this.y = y;

		this.recursos = 0;

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (recursos > 0) {
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(plant[recursos], x, y, 100, 100);
		app.fill(255, 0, 0, 100);
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
