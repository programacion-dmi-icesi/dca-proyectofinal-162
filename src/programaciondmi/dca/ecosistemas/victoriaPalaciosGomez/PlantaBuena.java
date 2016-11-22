package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import processing.core.PApplet;
import programaciondmi.dca.core.EspecieAbstracta;

public abstract class PlantaBuena implements Runnable{
	
	protected int x;
	protected int y;	
	protected PApplet app;
	protected boolean PlantaVida=true;
	
	
	
	public PlantaBuena(PApplet app,int x, int y){
		this.app=app;
		this.x = (int)app.random(1200);
		this.y =  (int)app.random(1200) ;
	}
		
//	public abstract void dibujar();
	public void dibujar(){
		app.ellipse(x, y, 20, 20);
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







