package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import processing.core.PShape;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaSaludable extends PlantaAbstracta {

	private int posX, posY;
	private PApplet app;
	private int vida;
	private PShape[] img;

	public PlantaSaludable() {
		super();
		img = new PShape[6];
		this.app = Mundo.ObtenerInstancia().getApp();
		posX = (int) (app.random(0, 500));
		posY = (int) (app.random(0, 500));
		vida = 100;

		cargaDeImagenes();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (vida > 0) {
			try {

			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.shape(img[2], posX, posY);
		// System.out.println("posX: "+posX+" posY: "+posY);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	public void cargaDeImagenes() {
		for (int i = 0; i < 5; i++) {
			img[i] = app.loadShape("data/plants/planta-0" + i + ".svg");
		}
	}
}
