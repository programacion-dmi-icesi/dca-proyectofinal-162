package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Magusbot extends EspecieAbstracta implements IOmnivoro  {

	
	private int vida;
	private int velocidad;
	private int ciclo;
	private PVector dir;
	private int alto, ancho;
	PImage[] magobot = new PImage[22];
	int posicionpj = 0;
	int contador=0;
	int movMagusBot=0;
	public Magusbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 5;
		this.velocidad =2;
		this.alto=60;
		this.ancho= 60;
		this.movMagusBot= (int) (Math.random()*4+1);
	
		
		
		// PERSONAJE ASPECTO
				PApplet app = Mundo.ObtenerInstancia().getApp();
				//espalda del magobot
				magobot[0] = app.loadImage("DataTikiBots/Magusbot/mA01.png");
				magobot[1] = app.loadImage("DataTikiBots/Magusbot/mA02.png");
				magobot[2] = app.loadImage("DataTikiBots/Magusbot/mA03.png");
				magobot[3] = app.loadImage("DataTikiBots/Magusbot/mA04.png");
				magobot[4] = app.loadImage("DataTikiBots/Magusbot/mA05.png");
				//perfil derecho del magobot
				magobot[5] = app.loadImage("DataTikiBots/Magusbot/mD01.png");
				magobot[6] = app.loadImage("DataTikiBots/Magusbot/mD02.png");
				magobot[7] = app.loadImage("DataTikiBots/Magusbot/mD03.png");
				magobot[8] = app.loadImage("DataTikiBots/Magusbot/mD04.png");
				magobot[9] = app.loadImage("DataTikiBots/Magusbot/mD05.png");
				magobot[10] = app.loadImage("DataTikiBots/Magusbot/mD06.png");
				//frente del magobot
				magobot[11] = app.loadImage("DataTikiBots/Magusbot/mF01.png");
				magobot[12] = app.loadImage("DataTikiBots/Magusbot/mF02.png");
				magobot[13] = app.loadImage("DataTikiBots/Magusbot/mF03.png");
				magobot[14] = app.loadImage("DataTikiBots/Magusbot/mF04.png");
				magobot[15] = app.loadImage("DataTikiBots/Magusbot/mF05.png");
				//perfil izquierdo del magobot
				magobot[16] = app.loadImage("DataTikiBots/Magusbot/mZ01.png");
				magobot[17] = app.loadImage("DataTikiBots/Magusbot/mZ02.png");
				magobot[18] = app.loadImage("DataTikiBots/Magusbot/mZ03.png");
				magobot[19] = app.loadImage("DataTikiBots/Magusbot/mZ04.png");
				magobot[20] = app.loadImage("DataTikiBots/Magusbot/mZ05.png");
				magobot[21] = app.loadImage("DataTikiBots/Magusbot/mZ06.png");

				
				Thread nt = new Thread(this);
				nt.start();
	
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}


}
