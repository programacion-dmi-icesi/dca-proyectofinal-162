package programaciondmi.dca.ecosistemas.lopezmoralesurango;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta{
	private PImage PlantaM;
	private boolean mostrar=true;
	private int vida;
	
	public PlantaMala(int x,int y) {
		super(x,y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		PlantaM=app.loadImage("planttwo.png");
		
		vida = 4;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		if(mostrar == true){
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(PlantaM, x, y);
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if(vida > 0 ){
			
			mostrar = true;
		}
		return true;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	
	
}
