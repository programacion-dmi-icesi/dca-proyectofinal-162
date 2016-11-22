package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int vida;
	private int x;
	private int y;
	PImage[] Bplanta = new PImage[3];
	
	public PlantaBuena(EcosistemaTikiBots ecosistemaTikiBots){
		this.x = app.mouseX;
		this.y = app.mouseY;
		this.vida = 5;
		
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
		PApplet app = Mundo.ObtenerInstancia().getApp();
		//System.out.println("imagen planta mala");
		app.image(Bplanta[0],x,y);
	
		// PINTAR BARRA DE VIDA
		
		app.noStroke();
		app.fill(255,0,0);
		switch (vida) {
		
		case 5: 
			
		app.rect(x+05, y-20, 20, 10);
		app.rect(x+25, y-20, 20, 10);
		app.rect(x+45, y-20, 20, 10);
		app.rect(x+65, y-20, 20, 10);
		app.rect(x+85, y-20, 20, 10);
		break;
		case 4: 
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			app.rect(x+45, y-20, 20, 10);
			app.rect(x+65, y-20, 20, 10);
			break;
		case 3:
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			app.rect(x+45, y-20, 20, 10);
			break;
		case 2:
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			break;
		case 1:
			app.rect(x+05, y-20, 20, 10);
			break;
		case 0:
			break;
		}

		//System.out.println("imagen planta mala");
		app.image(Bplanta[0],x,y);

	}

}
