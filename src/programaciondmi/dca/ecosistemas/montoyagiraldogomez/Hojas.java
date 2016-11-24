package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hojas extends PlantaAbstracta {

	private int recursos;
	private PImage plant;

	public Hojas(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.plant = app.loadImage("propheticData/normal1.png");

		this.x = x;
		this.y = y;

		this.recursos = 3;

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
		app.image(plant, x, y);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (recursos > 0) {
			recursos--;
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
