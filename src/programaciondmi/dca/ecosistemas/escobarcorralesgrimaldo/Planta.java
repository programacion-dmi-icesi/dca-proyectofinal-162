//comienza clase Planta
//importa librerias

package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Planta extends PlantaAbstracta {

	// establece variables
	private int x, y, vida, viva = 0;
	private boolean muere = false;
	// arreglo de imagenes
	private PImage[] plantaB = new PImage[3];
	private PImage[] plantaM = new PImage[3];
	private PApplet app;
	// establece si la planta es buena o mala
	private int clasePlanta;

	// Comienza constructor de Planta
	public Planta(int i) {

		this.clasePlanta = i;
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.app = Mundo.ObtenerInstancia().getApp();

		// inicializa el arreglo de im�genes
		plantaB[0] = app.loadImage("../data/buenatres.png");
		plantaB[1] = app.loadImage("../data/buenados.png");
		plantaB[2] = app.loadImage("../data/buenauno.png");

		plantaM[0] = app.loadImage("../data/malatres.png");
		plantaM[1] = app.loadImage("../data/malados.png");
		plantaM[2] = app.loadImage("../data/malauno.png");

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		// dibuja las plantas en el lienzo
		PApplet app = Mundo.ObtenerInstancia().getApp();

		switch (clasePlanta) {

		case 0:

			app.image(plantaB[viva], x, y,50,50);
			break;

		case 1:

			app.image(plantaM[viva], x, y,50,50);
			break;
		}
	}

	public void ataque() {
		viva++;

		if (viva > 3) {
			muere = true;
		}
	}

	public boolean isMuere() {
		return muere;
	}

	public void setMuere(boolean muere) {
		this.muere = muere;
	}

	public int getClasePlanta() {
		return clasePlanta;
	}

	public void setClasePlanta(int clasePlanta) {
		this.clasePlanta = clasePlanta;
	}

	public int getViva() {
		return viva;
	}

	public void setViva(int viva) {
		this.viva = viva;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
