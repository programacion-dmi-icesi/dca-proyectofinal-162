package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Random;

import processing.core.PApplet;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Venenosa extends PlantaAbstracta {

	private int recursos;
	private Random random;

	public Venenosa() {
		super();
		recursos = 3;
		this.random = new Random();
		x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);

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
		app.fill(0, 150, 0);
		app.ellipse(x, y, 50, 50);
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
