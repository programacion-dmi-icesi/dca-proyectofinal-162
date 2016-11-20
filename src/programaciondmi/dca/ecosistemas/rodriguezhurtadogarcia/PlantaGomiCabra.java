package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaGomiCabra extends PlantaAbstracta {

	private int x, y, camX, camY;
	private PImage[] plantaBuena = new PImage[4];
	private PImage[] plantaMala = new PImage[4];
	private PApplet app;
	private int id; // esta variable dice si es mala o buena
	private int estado = 0;
	private boolean muerto = false;

	// ========================================================================================================================================
	public PlantaGomiCabra(EcosistemaGomiCabra ecosistema, int id) {
		super();
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);

		plantaBuena[0] = app.loadImage("../dataGomiCabra/plantaBuena/plantaBuena1.png");
		plantaBuena[1] = app.loadImage("../dataGomiCabra/plantaBuena/plantaBuena2.png");
		plantaBuena[2] = app.loadImage("../dataGomiCabra/plantaBuena/plantaBuena3.png");
		plantaBuena[3] = app.loadImage("../dataGomiCabra/plantaBuena/plantaBuena4.png");

		plantaMala[0] = app.loadImage("../dataGomiCabra/plantaMala/plantaMala1.png");
		plantaMala[1] = app.loadImage("../dataGomiCabra/plantaMala/plantaMala2.png");
		plantaMala[2] = app.loadImage("../dataGomiCabra/plantaMala/plantaMala3.png");
		plantaMala[3] = app.loadImage("../dataGomiCabra/plantaMala/plantaMala4.png");
		this.id = id;
	}

	// ========================================================================================================================================
	@Override
	public void run() {
	}

	// ========================================================================================================================================
	@Override
	public void dibujar() {
		switch (id) {
		case 0:
			app.image(plantaBuena[estado], x, y);
			break;
		case 1:
			app.image(plantaMala[estado], x, y);
			break;
		}
	}

	// ========================================================================================================================================
	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		return false;
	}

	// ========================================================================================================================================
	public void mordisco() {
		estado++;
		if (estado > 3) {
			muerto = true;
		}
	}

	// ========================================================================================================================================
	public boolean isMuerto() {
		return muerto;
	}

	// ========================================================================================================================================
	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

}
