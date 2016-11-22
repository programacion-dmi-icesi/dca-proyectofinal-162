package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta{
	
	public PlantaMala() {
		// TODO Auto-generated constructor stub
	}
	
	public PlantaMala(int x, int y) {
		super(x,y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(0);
		app.ellipse(x, y, 30, 30);
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
