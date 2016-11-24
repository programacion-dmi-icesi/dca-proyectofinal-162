package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Venenosa extends PlantaAbstracta {

	private int recursos;
	private PImage[] plant;
	private PImage[] particulas;
	private int frame;
	private float ciclo, cambio;

	public Venenosa(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();

		this.plant = new PImage[4];
		particulas = new PImage[32];
		this.cambio = 5;

		for (int i = 0; i < plant.length; i++) {
			this.plant[i] = app.loadImage("propheticData/plantaMal/mala_" + i + ".png");
		}
		
		for (int i = 0; i < particulas.length; i++) {
			particulas[i] = app.loadImage("propheticData/plantaMal/particulasMala_" + i + ".png");
		}
		

		recursos = 0;

		this.x = x;
		this.y = y;

		Thread nt = new Thread(this);
		nt.start();

	}
	
	@Override
	public void run() {
		while (true) {
			sumarFrames();
			try {
				Thread.sleep(10);
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
		app.image(plant[recursos], x, y,100,100);
		app.image(particulas[frame], x, y);
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
