package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.PlantaAbstracta;

public abstract class PlantaPapu extends PlantaAbstracta {

	protected PVector pos;
	protected float vida;
	protected boolean vive;
	protected PApplet app;
	protected PImage image;

	public PlantaPapu() {
		app = EcosistemaPapus.app;
		x = 300;
		y = 0;

		pos = new PVector(x, y);
	}

	@Override
	public void run() {
while (vive) {
	try {
		crecer();
		Thread.sleep(10000);
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	}

	public void botones() {


	}

	public void mover(float x, float y) {
		pos.x = x;
		pos.y = y;

	}

	public void crecer() {

	}

	public void muerto(ArrayList<PlantaAbstracta> list, PlantaAbstracta planta) {

	}

}
