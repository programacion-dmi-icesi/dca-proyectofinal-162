package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int vida;
	private int x;
	private int y;
	PImage[] Mplanta = new PImage[3];
	
	public PlantaMala(EcosistemaTikiBots ecosistemaTikiBots){
		this.x = app.mouseX;
		this.y = app.mouseY;
		this.vida = 5;
		
		Mplanta[0] = app.loadImage("DataTikiBots/planta_mala/plantaMala01.png");
		Mplanta[1] = app.loadImage("DataTikiBots/planta_mala/plantaMala02.png");
		Mplanta[2] = app.loadImage("DataTikiBots/planta_mala/plantaMala03.png");
	}

	@Override
	public void run() {
		while (vida > 0) {
			
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void dibujar() {
		System.out.println("imagen planta mala");
		app.image(Mplanta[0],x,y);
	}

}
