package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;


import processing.core.PApplet;  
import processing.core.PImage;
import java.util.ArrayList;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class SaberBot extends EspecieAbstracta implements ICarnivoro{
	

	
	private int vida;
	private int velocidad;
	private int ciclo;
	private PVector dir;
	private int alto, ancho;
	PImage[] pjcarnivoro = new PImage[14];
	int posicionpj = 0;
	int contador=0;
	int movSaberBot=0;
	public SaberBot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 5;
		this.velocidad =2;
		this.alto=60;
		this.ancho= 60;
		this.movSaberBot= (int) (Math.random()*4+1);


		
	
		// PERSONAJE ASPECTO
		PApplet app = Mundo.ObtenerInstancia().getApp();
		//espalda del magobot
		pjcarnivoro[0] = app.loadImage("DataTikiBots/saberbot/sA01.png");
		pjcarnivoro[1] = app.loadImage("DataTikiBots/saberbot/sA02.png");
		pjcarnivoro[2] = app.loadImage("DataTikiBots/saberbot/sA03.png");
		pjcarnivoro[3] = app.loadImage("DataTikiBots/saberbot/sA04.png");
		//perfil derecho del magobot
		pjcarnivoro[4] = app.loadImage("DataTikiBots/saberbot/sD01.png");
		pjcarnivoro[5] = app.loadImage("DataTikiBots/saberbot/sD02.png");
		pjcarnivoro[6] = app.loadImage("DataTikiBots/saberbot/sD03.png");
		//frente del magobot
		pjcarnivoro[7] = app.loadImage("DataTikiBots/saberbot/sF01.png");
		pjcarnivoro[8] = app.loadImage("DataTikiBots/saberbot/sF02.png");
		pjcarnivoro[9] = app.loadImage("DataTikiBots/saberbot/sF03.png");
		pjcarnivoro[10] = app.loadImage("DataTikiBots/saberbot/sF04.png");
		//perfil izquierdo del magobot
		pjcarnivoro[11] = app.loadImage("DataTikiBots/saberbot/sZ01.png");
		pjcarnivoro[12] = app.loadImage("DataTikiBots/saberbot/sZ02.png");
		pjcarnivoro[13] = app.loadImage("DataTikiBots/saberbot/sZ03.png");
		
		Thread nt = new Thread(this);
		nt.start();
		
	}
	

	


	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
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

		// Image Personaje
		
		app.image(pjcarnivoro[contador], app.width/2, app.height/2);
		
	
		switch (movSaberBot){
		case 1: 
			
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break; 
		}
		
	}

	@Override
	public void mover() {

		switch (movSaberBot){
		
		case 0:
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		
		}
	}
	
	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida/2)){
			vida -= 5;
			try {
				lastimador.setEstado(EXTASIS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}

	
	
	
}
