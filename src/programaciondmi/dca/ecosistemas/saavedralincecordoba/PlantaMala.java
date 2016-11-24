package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {

	boolean mostrar = true;
	private PImage[] plantaM = new PImage[3];
	private int vida = 100;
	private int contador,cont;

	public PlantaMala() {
		// TODO Auto-generated constructor stub
	}

	public PlantaMala(int x, int y) {
		super(x, y);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		contador=3;
		plantaM[0] = app.loadImage("../data/plantam.png");
		plantaM[1] = app.loadImage("../data/plantam2.png");
		plantaM[2] = app.loadImage("../data/plantam3.png");

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		
		if (contador==3) {
			app.image(plantaM[0], x, y);
		}
		if (contador==2) {
			app.image(plantaM[1], x, y);
		}
		if (contador==1) {
			app.image(plantaM[2], x, y);
		}
		if (contador==0) {
			app.image(plantaM[2], x, y);
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {

		if (contador > 0) {
			contador--;
		return true;
			
		}
		return false;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	private void ocultarPlanta() {
		// TODO Auto-generated method stub
		if (vida == 0) {
			cont++;
			if (cont% 300 == 0) {
				setMostrar(false);
			}
		}
	}
	public int getCont(){
	return contador;
	}
	public void setCont(int contador){
		this.contador=contador;
	}
}
