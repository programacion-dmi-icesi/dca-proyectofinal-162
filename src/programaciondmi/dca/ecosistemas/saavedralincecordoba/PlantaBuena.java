package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta{
	
	boolean mostrar=true;
	private PImage[] plantaB = new PImage[3];
	private int planta=0;
	public PlantaBuena() {
		// TODO Auto-generated constructor stub
	}
	
	public PlantaBuena(int x, int y) {
		super(x,y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		plantaB[0]= app.loadImage("../data/plantab.png");
		plantaB[1]= app.loadImage("../data/plantab2.png");
		plantaB[2]= app.loadImage("../data/platab3.png");
		
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
		app.image(plantaB[planta],x,y);
		}	
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 60){

			mostrar= false;
			
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
