package programaciondmi.dca.ecosistemas.lopezmoralesurango;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {
	private PImage PlantaB;
	private int vida;
	private boolean mostrar = true;
	

	public PlantaBuena(int x, int y) {
		super(x,y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		PlantaB = app.loadImage("plantone.png");
		// TODO Auto-generated constructor stub
		
		vida= 4;
	}

	@Override
	public void run() {
		// TODO Auto-generated methodnm stub

	}

	@Override
	public void dibujar() {
		if (mostrar) {
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.image(PlantaB, x /*+ 100*/, y /*- 50*/);
		}
	}
	

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		//if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 60){
		
		if(vida > 0 ){
		
		mostrar = true;
			//mostrar = false;
		}
		return true;
		//return false;
}
	//}

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
