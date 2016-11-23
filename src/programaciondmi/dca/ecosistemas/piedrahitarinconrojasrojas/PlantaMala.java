package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int vida, contador, cont;
	private int x;
	private int y;
	PImage[] Mplanta = new PImage[3];
	
	public PlantaMala(int x, int y){
		this.x = x;
		this.y = y;
		this.vida = 5;
		contador = 0;
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
		//System.out.println("imagen planta mala");
		app.image(Mplanta[contador],x,y);
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

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		cont++;
		
		
		
		if(cont%300 == 0){
			if(contador<2){
				contador+=1;
			}else{
				
			}
		}
		
		
		 
		 System.out.println(contador);
		
		return false;
	}

}
