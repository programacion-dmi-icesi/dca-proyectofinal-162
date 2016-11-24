package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaVenenosa extends PlantaAbstracta {

	private PShape[] img;
	private int vida;
	private PApplet app;
	private PVector pos;
	private int fase;
	private int mov;

	public PlantaVenenosa() {
		this.app = Mundo.ObtenerInstancia().getApp();
		img = new PShape[5];

		pos = new PVector(app.random(-500,500), app.random(-500,500));
		cargaDeImagen();
		mov = 0;
		vida = 100;
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

		fase = (int) PApplet.map(vida, 0, 100, 0, 4);
		app.shapeMode(PApplet.CENTER);
		app.shape(img[4 - fase], pos.x, pos.y);
		app.shapeMode(PApplet.CORNER);

		if (vida == 0)
			mov = 1;

		switch (mov) {
		case 0:
			vida -= 1;
			break;
		case 1:
			vida = 0;
			break;
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (vida > 0) {
			vida--;
			return true;
		} else
			return false;
	}

	public void cargaDeImagen() {
		for (int i = 9; i > 4; i--) {
			img[i - 5] = app.loadShape("../dataAmids/Plants/planta-0" + i + ".svg");
		}
	}
}
