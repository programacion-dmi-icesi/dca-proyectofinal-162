package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Venenosa extends PlantaAbstracta {

	private int recursos;
	private Random random;
	private PImage plant;

	public Venenosa(int x, int y) {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.plant = app.loadImage("carnivora1.png");
		recursos = 3;
		this.random = new Random();

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
		app.image(plant, x, y);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (recursos > 0) {
			recursos--;
			System.out.println("Me comieron" + recursos);
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
