package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {
	
	private int vida;
	private float fuerza;
	private int velocidad;
	private float energia;
	private int ciclo;
	private PVector dir;
	PImage[] Mplanta = new PImage[3];
	
	public PlantaMala(EcosistemaTikiBots ecosistemaTikiBots){
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.x = app.mouseX;
		this.y = app.mouseY;
		this.vida = 5;
		this.velocidad =2;
		this.energia = 250;
		this.fuerza = 100;
		
		Mplanta[0] = app.loadImage("DataTikiBots/planta_mala/pantaMala01.png");
		Mplanta[1] = app.loadImage("DataTikiBots/planta_mala/pantaMala02.png");
		Mplanta[2] = app.loadImage("DataTikiBots/planta_mala/pantaMala03.png");
	}

	@Override
	public void run() {
		while (vida > 0) {
			
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(Mplanta[0],x,y);
	}

}
