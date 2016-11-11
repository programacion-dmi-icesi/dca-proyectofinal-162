package programaciondmi.dca.ejecucion;

import processing.core.PApplet;

public class Ejecutable extends PApplet {
	private Mundo mundo;
	public static void main(String[] args) {
		PApplet.main("programaciondmi.dca.ejecucion.Ejecutable", args);
	}
	
	public void settings(){
		//fullScreen();
//		fullScreen();
		size(500, 500);
	}
	
	public void setup(){
		mundo = Mundo.construirInstancia(this);
		mundo.cargarEcosistemas();
	}
	
	public void draw(){
		background(255);
		mundo.dibujar();
	}
	
	public void mousePressed(){
		mundo.click();
	}
}
