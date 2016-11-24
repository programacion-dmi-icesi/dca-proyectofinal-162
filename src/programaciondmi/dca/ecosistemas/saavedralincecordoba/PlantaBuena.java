package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {

	boolean mostrar = true;
	private PImage[] plantaB = new PImage[3];
	private int vida = 100;
	private int contador,cont;

	public PlantaBuena() {
		// TODO Auto-generated constructor stub
	}

	public PlantaBuena(int x, int y) {
		super(x, y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		contador=0;
		plantaB[0] = app.loadImage("../data/plantab.png");
		plantaB[1] = app.loadImage("../data/platab3.png");
		plantaB[2] = app.loadImage("../data/plantab2.png");

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		if (mostrar) {
			app.image(plantaB[contador],x,y);
		}

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if(contador<2){
			vida -=20;
			contador+=1;
		}else{
			if(vida!=0){
				vida -=20;
				contador=0;
			}else{
				return true;
			}
		}
	return false;
	}


	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	private void ocultarPlanta() {
		// TODO Auto-generated method stub
		if(vida==0){
			cont++;
			if(cont%300 == 0){
				setMostrar(false);
			}
		}
	}

}
