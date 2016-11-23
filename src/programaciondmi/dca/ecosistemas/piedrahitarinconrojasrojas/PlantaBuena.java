package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int vida, contador, cont;
	private int x;
	private int y;
	PImage[] Bplanta = new PImage[3];
	private boolean mostrar;
	
	public PlantaBuena(int x, int y){
		this.x = x;
		this.y = y;
		this.vida = 100;
		contador = 0;
		mostrar = true;
		Bplanta[0] = app.loadImage("DataTikiBots/planta_buena/plantaBuena01.png");
		Bplanta[1] = app.loadImage("DataTikiBots/planta_buena/plantaBuena02.png");
		Bplanta[2] = app.loadImage("DataTikiBots/planta_buena/plantaBuena03.png");
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
		
		
		
		if(mostrar){
			
			app.noStroke();
			switch (vida) {
			
			case 100: 
				app.fill(0,255,0);
				app.rect(x+05, y-20, 20, 10);
				app.rect(x+25, y-20, 20, 10);
				app.rect(x+45, y-20, 20, 10);
				app.rect(x+65, y-20, 20, 10);
				app.rect(x+85, y-20, 20, 10);
			break;
			case 80:
				app.fill(255,255,0);
				app.rect(x+05, y-20, 20, 10);
				app.rect(x+25, y-20, 20, 10);
				app.rect(x+45, y-20, 20, 10);
				app.rect(x+65, y-20, 20, 10);
				break;
			case 60:
				app.fill(255,0,0);
				app.rect(x+05, y-20, 20, 10);
				app.rect(x+25, y-20, 20, 10);
				app.rect(x+45, y-20, 20, 10);
				break;
			case 40:
				app.fill(255,0,255);
				app.rect(x+05, y-20, 20, 10);
				app.rect(x+25, y-20, 20, 10);
				break;
			case 20:
				app.fill(255,0,255);
				app.rect(x+05, y-20, 20, 10);
				break;
			case 0:
				
				break;
			}
			
			app.image(Bplanta[contador],x,y);
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

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		
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

}
