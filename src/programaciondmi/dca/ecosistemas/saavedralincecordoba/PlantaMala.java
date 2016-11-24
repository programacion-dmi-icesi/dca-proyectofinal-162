package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {

	boolean mostrar = true;
	private PImage[] plantaM = new PImage[3];
	private int vida = 100;
	private int contador = 0;
	private int x;
	private int y;

	public PlantaMala() {
		// TODO Auto-generated constructor stub
	}

	public PlantaMala(int x, int y) {
		super(x, y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		plantaM[0] = app.loadImage("../data/plantam.png");
		plantaM[1] = app.loadImage("../data/plantam2.png");
		plantaM[2] = app.loadImage("../data/plantam3.png");

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		if (mostrar) {
			app.image(plantaM[contador], x, y);
		}

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if (contador < 2) {
			vida -= 20;
			contador += 1;
		} else {
			if (vida != 0) {
				vida -= 20;
				contador = 0;
			} else {
				return true;
			}
		}
		return false;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	private void ocultarPlanta() {
		// TODO Auto-generated method stub
		if (vida == 0) {
			contador++;
			if (contador % 300 == 0) {
				setMostrar(false);
			}
		}
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
