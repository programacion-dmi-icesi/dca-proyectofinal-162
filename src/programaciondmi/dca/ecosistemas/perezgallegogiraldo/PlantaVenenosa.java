package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaVenenosa extends PlantaAbstracta {

	private PShape[] img;
	private int vida;
	private PApplet app;
	private PVector pos;

	public PlantaVenenosa() {
		this.app = Mundo.ObtenerInstancia().getApp();
		img = new PShape[5];

		pos = new PVector(0, 500);
		cargaDeImagen();
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
		app.shape(img[2], pos.x, pos.y);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	public void cargaDeImagen() {
		for (int i = 5; i < 10; i++) {
			img[i-5] = app.loadShape("data/Plants/planta-0" + i + ".svg");
		}
	}
}
