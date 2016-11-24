package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public abstract class PlantaMala implements Runnable{
	
	protected int x;
	protected int y;	
	protected PApplet app;
	protected boolean PlantaVida=true;
	protected ArrayList<PlantaMala>malas;
	protected  PImage Malauno, Malados;
	
	
	
	public PlantaMala(int x, int y){
		this.app = Mundo.ObtenerInstancia().getApp();
		this.x = (int)app.random(2000);
		this.y =  (int)app.random(2000) ;
		Malauno= app.loadImage("../data/Malados.png");
		Malados=app.loadImage("../data/Malauno.png");
	}
		
//	public abstract void dibujar();
	public void dibujar(){
		app.image(Malauno, x, y);
		app.image(Malados, x, y);
	}
	
	/**
	 * Método encargadado de recibir el daño ocasionado por el ataque una especie abstracta
	 * el método deberá validar su posicion respecto a la especie para saber si se lastima o no.
	 * 
	 * @param lastimador quien hace daño a la planta actual
	 * @return true si pudo hacer daño y false si no puedo dañar a la especie actual 
	 */
	public abstract boolean recibirDano(EspecieAbstracta lastimador);
	
	
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}







