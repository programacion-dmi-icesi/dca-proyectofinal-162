package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hojas extends PlantaAbstracta {

	private int recursos;
	private PImage[] plant;
	private PImage[] particulas;
	private int frame;
	private float ciclo, cambio;

	public Hojas(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();

		this.plant = new PImage[4];
		particulas = new PImage[32];
		this.cambio = 5;

		for (int i = 0; i < plant.length; i++) {
			this.plant[i] = app.loadImage("propheticData/plantaBue/buena_" + i + ".png");
		}
		for (int i = 0; i < particulas.length; i++) {
			particulas[i] = app.loadImage("propheticData/plantaBue/particulasBuena_" + i + ".png");
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
			sumarFrames();
			try {
				this.ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	private void sumarFrames() {
		if (ciclo % cambio == 0) {
			frame++;
			if (frame > particulas.length - 1) {
				frame = 0;
			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(plant[recursos], x, y, 100, 100);
		app.image(particulas[frame], x, y);
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
