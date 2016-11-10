package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

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

	public PlantaGomiCabra(EcosistemaGomiCabra ecosistema, int id, int x, int y) {
		super();

		camX=Mundo.ObtenerInstancia().getApp().width/2;
		camY=Mundo.ObtenerInstancia().getApp().height/2;
		
		app = Mundo.ObtenerInstancia().getApp();

		this.x = x;
		this.y = y;
		plantaBuena[0] = app.loadImage("../data/plantaBuena1.png");
		plantaBuena[1] = app.loadImage("../data/plantaBuena2.png");
		plantaBuena[2] = app.loadImage("../data/plantaBuena3.png");
		plantaBuena[3] = app.loadImage("../data/plantaBuena4.png");

		plantaMala[0] = app.loadImage("../data/plantaMala1.png");
		plantaMala[1] = app.loadImage("../data/plantaMala2.png");
		plantaMala[2] = app.loadImage("../data/plantaMala3.png");
		plantaMala[3] = app.loadImage("../data/plantaMala4.png");
		this.id = id;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub

		switch (id) {
		case 0:
			app.image(plantaBuena[0], x-camX, y-camY);
			break;
		case 1:
			app.image(plantaMala[0], x-camX, y-camY);
			break;
		}

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

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
