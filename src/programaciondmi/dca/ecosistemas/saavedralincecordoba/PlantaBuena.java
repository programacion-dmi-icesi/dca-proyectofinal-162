package programaciondmi.dca.ecosistemas.saavedralincecordoba;
import processing.core.PImage;
import programaciondmi.dca.ejecucion.Mundo;
import processing.core.PApplet;

public class PlantaBuena extends Thread{
private float posX;
private float posY;
private int vida=3;
private PImage plantaB;
private PApplet app;

public void plantaBuena(){
	app= Mundo.ObtenerInstancia().getApp();
	plantaB= app.loadImage("plantab.png");
}
public void run(){
	
}
public void pintar(){
	app.image(plantaB, posX, posY);
}

}
