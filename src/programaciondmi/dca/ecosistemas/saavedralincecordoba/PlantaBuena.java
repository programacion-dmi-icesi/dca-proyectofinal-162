package programaciondmi.dca.ecosistemas.saavedralincecordoba;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;
import processing.core.PApplet;

public class PlantaBuena extends PlantaAbstracta{
private float posX;
private float posY;
private int vida=3;
private PImage plantaB;
private PApplet app;

public PlantaBuena(){
	app= Mundo.ObtenerInstancia().getApp();
	plantaB= app.loadImage("plantab.png");
}

public float getposX(){
	return posX;
}
public float getposY(){
	return posY;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	
}
@Override
public void dibujar() {
	// TODO Auto-generated method stub
	app.image(plantaB, posX, posY);
}
@Override
public boolean recibirDano(EspecieAbstracta lastimador) {
	// TODO Auto-generated method stub
	return false;
} 


}
