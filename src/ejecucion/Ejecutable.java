package ejecucion;

import processing.core.PApplet;

public class Ejecutable extends PApplet {
	private Mundo mundo;
	public static void main(String[] args) {
		PApplet.main("ejecucion.Ejecutable", args);
	}
	
	public void settings(){
		fullScreen();
	}
	
	public void setup(){
		mundo = Mundo.construirInstancia(this);
	}
	
	public void draw(){
		mundo.dibujar();
	}
}
