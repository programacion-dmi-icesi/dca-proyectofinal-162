package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.ArrayList;
import java.util.LinkedList;

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
	protected float contCrecer;
	protected int oWith;
	protected int oHeight;
	protected long sleeping;

	public PlantaPapu() {
		app = EcosistemaPapus.app;
		x = (int) (Math.random() * 600);
		y = (int) (Math.random() * 400);
		sleeping = 2000;
		pos = new PVector(x, y);
		contCrecer = 1;
		vive = true;

	}
	
	public PlantaPapu(int x, int y) {
		super(x, y);
		app = EcosistemaPapus.app;
		this.x = x;
		this.y = y;
		sleeping = 2000;
		pos = new PVector(x, y);
		contCrecer = 1;
		vive = true;

	}

	@Override
	public void run() {
		while (vive) {
			try {
				crecer();
				Thread.sleep(sleeping);
				contCrecer += 0.5f;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void crecer() {

		if (image.width >= oWith && image.height >= oHeight) {
			image.resize((int) ((oWith / 2) * contCrecer), (int) ((oHeight / 2) * contCrecer));
		}
	}
	
	protected void muerto(LinkedList<PlantaAbstracta> lista, PlantaAbstracta planta){
		
		
	}

	public void muerto(ArrayList<PlantaAbstracta> list, PlantaAbstracta planta) {

	}

}
 