package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena {
	
	protected int x, y;
	protected PApplet app;
	protected PImage  Buenauno, Buenados;
	protected ArrayList<PlantaBuena>buenas;
	
	
	public PlantaBuena( int x,int y){
		this.app = Mundo.ObtenerInstancia().getApp();
		this.x= (int)app.random(2000);
		this.y=(int)app.random(2000);
		Buenauno= app.loadImage("../data/Malados.png");
		Buenados=app.loadImage("../data/Malauno.png");
		
	}
	
	public void dibujar(){
		app.image(Buenauno, x, y);
		app.image(Buenados, x, y);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
  
	
}
