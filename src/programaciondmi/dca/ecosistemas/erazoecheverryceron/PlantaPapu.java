package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.PlantaAbstracta;

public abstract class PlantaPapu extends PlantaAbstracta {

	protected PVector pos;
	protected float vida;
	protected boolean murio;
	protected PImage plantaBuena, plantaMala;
	private PApplet app;
	
	public PlantaPapu() {
		pos = new PVector(x,y);
		plantaBuena = app.loadImage("plantaBuena.png");
		plantaMala = app.loadImage("plantaMala.png");
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	public void mover(float x, float y){
		pos.x = x;
		pos.y = y;
		
	}
	
	public void crecer(){
		
	}
	
	public void muerto(ArrayList<PlantaAbstracta> list, PlantaAbstracta planta){
		
	}
	
	

}
