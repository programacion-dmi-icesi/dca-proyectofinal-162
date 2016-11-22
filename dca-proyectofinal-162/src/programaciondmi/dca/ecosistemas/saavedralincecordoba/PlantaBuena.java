package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta{
	
	boolean mostrar=true;
	
	public PlantaBuena() {
		// TODO Auto-generated constructor stub
	}
	
	public PlantaBuena(int x, int y) {
		super(x,y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		if(mostrar){
			
		
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(0,255,0);
		app.ellipse(x, y, 30, 30);
		}	
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 60){

			mostrar= false;
			return true;
		}
		
		return false;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	

}
