package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hojas extends PlantaAbstracta {

	private int recursos;
	private Random random;
	private PImage plant;

	public Hojas() {
		super();
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.plant = app.loadImage("normal1.png");
		this.random = new Random();

		this.recursos = 3;

		x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while(recursos > 0){
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
			System.out.println("Me comieron"+recursos);
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