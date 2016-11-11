package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class Cannibalbot extends EspecieAbstracta implements ICanibal {

	private int vida;
	private float fuerza;
	private int velocidad;
	private float energia;
	private int ciclo;
	private PVector dir;
	PImage[] cannibal = new PImage[20];
	int posicionpj = 0;
	int contador=5;
	int movCannibalbot=0;
	private Random random;
	public Cannibalbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 5;
		this.velocidad =2;
		this.energia = 250;
		this.fuerza = 100;
		
		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));
		ciclo = 0;
		// PERSONAJE ASPECTO
		PApplet app = Mundo.ObtenerInstancia().getApp();
		//espalda del magobot
		cannibal[0] = app.loadImage("DataTikiBots/Cannibalbot/cA001.png");
		cannibal[1] = app.loadImage("DataTikiBots/Cannibalbot/cA002.png");
		cannibal[2] = app.loadImage("DataTikiBots/Cannibalbot/cA003.png");
		cannibal[3] = app.loadImage("DataTikiBots/Cannibalbot/cA004.png");
		cannibal[4] = app.loadImage("DataTikiBots/Cannibalbot/cA005.png");

		//perfil derecho del Cannibalbot
		cannibal[5] = app.loadImage("DataTikiBots/Cannibalbot/cD001.png");
		cannibal[6] = app.loadImage("DataTikiBots/Cannibalbot/cD002.png");
		cannibal[7] = app.loadImage("DataTikiBots/Cannibalbot/cD003.png");
		cannibal[8] = app.loadImage("DataTikiBots/Cannibalbot/cD004.png");
		cannibal[9] = app.loadImage("DataTikiBots/Cannibalbot/cD005.png");

		//frente del Cannibalbot
		cannibal[10] = app.loadImage("DataTikiBots/Cannibalbot/cF001.png");
		cannibal[11] = app.loadImage("DataTikiBots/Cannibalbot/cF002.png");
		cannibal[12] = app.loadImage("DataTikiBots/Cannibalbot/cF003.png");
		cannibal[13] = app.loadImage("DataTikiBots/Cannibalbot/cF004.png");
		cannibal[14] = app.loadImage("DataTikiBots/Cannibalbot/cF005.png");

		//perfil izquierdo del Cannibalbot
		cannibal[15] = app.loadImage("DataTikiBots/Cannibalbot/cZ001.png");
		cannibal[16] = app.loadImage("DataTikiBots/Cannibalbot/cZ002.png");
		cannibal[17] = app.loadImage("DataTikiBots/Cannibalbot/cZ003.png");
		cannibal[18] = app.loadImage("DataTikiBots/Cannibalbot/cZ004.png");
		cannibal[19] = app.loadImage("DataTikiBots/Cannibalbot/cZ005.png");

		
		Thread nt = new Thread(this);
		nt.start();
		
	}
	

	


	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		if (energia > 0) {
			
			buscarComida();
			
					if (ciclo % 30 == 0) {
					// Definir una direccion aleatoria cada 3 segundos
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					cambiarDireccion(new PVector(targetX, targetY));
					System.out.println("CAMBIO DIRECCION!");
					}
		
				
			// moverse en la dirección asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}
		
		if(this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0){
			this.dir.x*=-1;			
		}
		if(this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0){
			this.dir.y *= -1;			
		}
		

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
		
		app.image(cannibal[contador], x, y, 100,100);
		app.fill(255,0,0);
		
	
		
	}
	
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
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
	
	private void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}
}
