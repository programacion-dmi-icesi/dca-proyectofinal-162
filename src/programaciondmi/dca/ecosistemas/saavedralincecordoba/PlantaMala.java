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
	
	public void PlantaMala(){
		app= Mundo.ObtenerInstancia().getApp();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
