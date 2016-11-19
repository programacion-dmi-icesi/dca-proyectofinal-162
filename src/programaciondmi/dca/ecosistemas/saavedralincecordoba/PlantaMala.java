package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import processing.core.PImage;
import processing.core.PApplet;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {
	private float posX;
	private float posY;
	private PImage plantaM;
	private PApplet app;
	private int vida;

	public PlantaMala() {
		app = Mundo.ObtenerInstancia().getApp();
		vida = 3;
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
		app.image(plantaM, posX, posY);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		if (vida > 0) {
			vida--;
			System.out.println("mordido" + vida);
			return true;
		}
		return false;
	}

	public float getposX() {
		return posX;
	}

	public float getposY() {
		return posY;
	}

}
