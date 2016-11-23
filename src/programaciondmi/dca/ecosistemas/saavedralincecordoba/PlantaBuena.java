package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {

	int mostrar = 0;
	private PImage[] plantaB = new PImage[3];
	private int planta = 0;
	private int vida = 2;

	public PlantaBuena() {
		// TODO Auto-generated constructor stub
	}

	public PlantaBuena(int x, int y) {
		super(x, y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		plantaB[0] = app.loadImage("../data/plantab.png");
		plantaB[1] = app.loadImage("../data/platab3.png");
		plantaB[2] = app.loadImage("../data/plantab2.png");

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		if (mostrar == 0) {

			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.image(plantaB[planta], x, y);
		}
		if (mostrar == 1) {

			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.image(plantaB[1], x, y);
		}
		if (mostrar == 2) {

			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.image(plantaB[2], x, y);
		}
		if (mostrar == 3) {

	
		}
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 60) {

			mostrar = 1;
			vida -= 1;
			if (vida == 1) {
				mostrar = 2;
			}
			if(vida==0){
				mostrar=3;
			}

		}

		return false;
	}

	public int isMostrar() {
		return mostrar;
	}

	public void setMostrar(int mostrar) {
		this.mostrar = mostrar;
	}

}
