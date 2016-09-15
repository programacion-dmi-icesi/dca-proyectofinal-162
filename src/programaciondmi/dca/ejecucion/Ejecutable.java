package programaciondmi.dca.ejecucion;

import processing.core.PApplet;

public class Ejecutable extends PApplet {
	private Mundo mundo;
	public static void main(String[] args) {
		PApplet.main("programaciondmi.dca.ejecucion.Ejecutable", args);
	}
	
	public void settings(){
		//fullScreen();
		size(500, 500);
	}
	
	public void setup(){
		mundo = Mundo.construirInstancia(this);
	}
	
	public void draw(){
		mundo.dibujar();
	}
}
