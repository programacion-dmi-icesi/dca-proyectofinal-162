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
	protected PApplet app;
	
	public PlantaPapu() {
		app = EcosistemaPapus.app;
		pos = new PVector(x,y);
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
