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
	private int fase, mov;

	public PlantaSaludable() {
		super();
		img = new PShape[6];
		this.app = Mundo.ObtenerInstancia().getApp();
		posX = (int) (app.random(-500, 500));
		posY = (int) (app.random(-500, 500));
		vida = 100;
		mov = 0;

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
		fase = (int) PApplet.map(vida, 0, 100, 0, 4);
		app.shapeMode(PApplet.CENTER);
		app.shape(img[4 - fase], posX, posY);
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
		// System.out.println("posX: "+posX+" posY: "+posY);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (vida > 0) {
			vida--;
			return true;
		} else
			return false;
	}

	public void cargaDeImagenes() {
		for (int i = 0; i < 5; i++) {
			img[i] = app.loadShape("../dataAmids/Plants/planta-0" + i + ".svg");
		}
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return posY;
	}
}
