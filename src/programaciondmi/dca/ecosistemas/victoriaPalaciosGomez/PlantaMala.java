package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {
	
	protected float x;
	protected float y;	
	protected PApplet app;
	protected boolean PlantaVida=true;
	private int index;
	protected ArrayList<PlantaMala>malas;
	protected  PImage[] plantaM = new PImage[4];
	
	
	
	public PlantaMala(PApplet ecosistema, float x, float y){
		super(ecosistema);
		this.app = Mundo.ObtenerInstancia().getApp();
		this.x = x;
		this.y = y;
	}
		
//	public abstract void dibujar();
	public void dibujar(){
		app.image(plantaM[index], x, y);
	}
	
	/**
	 * Método encargadado de recibir el daño ocasionado por el ataque una especie abstracta
	 * el método deberá validar su posicion respecto a la especie para saber si se lastima o no.
	 * 
	 * @param lastimador quien hace daño a la planta actual
	 * @return true si pudo hacer daño y false si no puedo dañar a la especie actual 
	 */
	//public abstract boolean recibirDano(EspecieAbstracta lastimador);
	
	private void images(){
		plantaM[0] = app.loadImage("../data/pMala-01.png");
		plantaM[1] = app.loadImage("../data/pMala-02.png");
		plantaM[2] = app.loadImage("../data/pMala-03.png");
		plantaM[3] = app.loadImage("../data/pMala-04.png");
	}
	
	
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
	
	public int getIndex(){
		return this.index;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		index++;
		if (index > 3) {
			index = 0;
		}
		return false;
	}

}







