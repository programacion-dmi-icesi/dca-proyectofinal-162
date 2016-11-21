package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Magusbot extends EspecieAbstracta implements IOmnivoro {

	private int vida;
	private float fuerza;
	private int velocidad;
	private float energia;
	private int ciclo;
	private PVector dir;
	PImage[] magobot = new PImage[20];
	int posicionpj;

	int contador;
	int vista;
	int movMagusBot;
	private Random random;

	public Magusbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 5;
		this.velocidad = 2;
		this.energia = 250;
		this.fuerza = 100;

		// this.movMagusBot= (int) (Math.random()*4+1);

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));
		ciclo = 0;

		// PERSONAJE ASPECTO
		PApplet app = Mundo.ObtenerInstancia().getApp();
		// espalda del magobot
		magobot[0] = app.loadImage("DataTikiBots/Magusbot/mA01.png");
		magobot[1] = app.loadImage("DataTikiBots/Magusbot/mA02.png");
		magobot[2] = app.loadImage("DataTikiBots/Magusbot/mA03.png");
		magobot[3] = app.loadImage("DataTikiBots/Magusbot/mA04.png");
		magobot[4] = app.loadImage("DataTikiBots/Magusbot/mA05.png");
		// perfil derecho del magobot
		magobot[5] = app.loadImage("DataTikiBots/Magusbot/mD01.png");
		magobot[6] = app.loadImage("DataTikiBots/Magusbot/mD02.png");
		magobot[7] = app.loadImage("DataTikiBots/Magusbot/mD03.png");
		magobot[8] = app.loadImage("DataTikiBots/Magusbot/mD04.png");
		magobot[9] = app.loadImage("DataTikiBots/Magusbot/mD05.png");
		// frente del magobot
		magobot[10] = app.loadImage("DataTikiBots/Magusbot/mF01.png");
		magobot[11] = app.loadImage("DataTikiBots/Magusbot/mF02.png");
		magobot[12] = app.loadImage("DataTikiBots/Magusbot/mF03.png");
		magobot[13] = app.loadImage("DataTikiBots/Magusbot/mF04.png");
		magobot[14] = app.loadImage("DataTikiBots/Magusbot/mF05.png");
		// perfil izquierdo del magobot
		magobot[15] = app.loadImage("DataTikiBots/Magusbot/mZ01.png");
		magobot[16] = app.loadImage("DataTikiBots/Magusbot/mZ02.png");
		magobot[17] = app.loadImage("DataTikiBots/Magusbot/mZ03.png");
		magobot[18] = app.loadImage("DataTikiBots/Magusbot/mZ04.png");
		magobot[19] = app.loadImage("DataTikiBots/Magusbot/mZ05.png");

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
	public void mover() {
		// TODO Auto-generated method stub
		if (energia > 0) {

			buscarComida();

			if (ciclo % 30 == 0) {
				// Definir una direccion aleatoria cada 5 segundos
				// Siguiente posición en x
				int targetX = random.nextInt();
				// Siguiente posición en y
				int targetY = random.nextInt();
				cambiarDireccion(new PVector(targetX, targetY));
				//System.out.println("CAMBIO DIRECCION!");
				if (targetX > this.x && targetY > this.y) {// derechaarriba
					vista = 1;
					contador = (vista - 1) * 5;
				} else if (targetX < this.x && targetY < this.y) {// izquierdaAbajo
					vista = 3;
					contador = (vista - 1) * 5;
				} else if (targetX < this.x && targetY > this.y) {// izquierdaArriba
					vista = 1;
					contador = (vista - 1) * 5;
				} else if (targetX > this.x && targetY < this.y) {// derechaAbajo
					vista = 4;
					contador = (vista - 1) * 5;
				}
			}

			// moverse en la direcciÃƒÂ³n asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}

		// if(this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0){
		// this.dir.x*=-1;
		// }
		// if(this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0){
		// this.dir.y *= -1;
		// }
		// if(this.x < Mundo.ObtenerInstancia().getApp().width || this.x > 0){
		// this.dir.x*=+1;
		// }
		// if(this.y < Mundo.ObtenerInstancia().getApp().height || this.y > 0){
		// this.dir.y *= +1;
		// }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
	public void dibujar() {// pintar
	
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.background(255);
		// Image Personaje

		if (contador == 5 * vista) {
			contador = (vista - 1) * 5;
		}
		System.out.println(magobot[contador]);
		
		app.image(magobot[contador], x, y, 100, 100);
		app.fill(255, 0, 0);
		if (app.frameCount % 10 == 0) {
			contador++;
		}

	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida / 2)) {
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
