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
	private boolean mostrar;
	
	public PlantaMala(int x, int y){
		this.x = x;
		this.y = y;
		this.vida = 100;
		contador = 0;
		mostrar = true;
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
		if(mostrar){
			app.image(Mplanta[contador],x,y);
		}
		ocultarPlanta();
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
		
			if(contador<2){
				vida -=50;
				contador+=1;
			}else{
				if(vida!=0){
					vida -=50;
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

}
