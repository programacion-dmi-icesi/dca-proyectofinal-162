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
	int contador;
	int movCannibalbot = 0;
	private Random random;
	int vista;
	int movCanibal;

	public Cannibalbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 5;
		this.velocidad = 3;
		this.energia = 200;
		this.fuerza = 90;

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));
		ciclo = 0;
		// PERSONAJE ASPECTO
		PApplet app = Mundo.ObtenerInstancia().getApp();
		// espalda del magobot
		cannibal[0] = app.loadImage("DataTikiBots/Cannibalbot/cA001.png");
		cannibal[1] = app.loadImage("DataTikiBots/Cannibalbot/cA002.png");
		cannibal[2] = app.loadImage("DataTikiBots/Cannibalbot/cA003.png");
		cannibal[3] = app.loadImage("DataTikiBots/Cannibalbot/cA004.png");
		cannibal[4] = app.loadImage("DataTikiBots/Cannibalbot/cA005.png");

		// perfil derecho del Cannibalbot
		cannibal[5] = app.loadImage("DataTikiBots/Cannibalbot/cD001.png");
		cannibal[6] = app.loadImage("DataTikiBots/Cannibalbot/cD002.png");
		cannibal[7] = app.loadImage("DataTikiBots/Cannibalbot/cD003.png");
		cannibal[8] = app.loadImage("DataTikiBots/Cannibalbot/cD004.png");
		cannibal[9] = app.loadImage("DataTikiBots/Cannibalbot/cD005.png");

		// frente del Cannibalbot
		cannibal[10] = app.loadImage("DataTikiBots/Cannibalbot/cF001.png");
		cannibal[11] = app.loadImage("DataTikiBots/Cannibalbot/cF002.png");
		cannibal[12] = app.loadImage("DataTikiBots/Cannibalbot/cF003.png");
		cannibal[13] = app.loadImage("DataTikiBots/Cannibalbot/cF004.png");
		cannibal[14] = app.loadImage("DataTikiBots/Cannibalbot/cF005.png");

		// perfil izquierdo del Cannibalbot
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
				// siguiente posicion en X
				int targetX = random.nextInt();
				// siguiente posicion en Y
				int targetY = random.nextInt();
				cambiarDireccion(new PVector(targetX, targetY));
				// diagonal derecha arriba
				if (targetX > this.x && targetY > this.y) {
					vista = 1;
					contador = (vista - 1) * 5;
					// diagonal izquierda abajo
				} else if (targetX < this.x && targetY < this.y) {
					vista = 2;
					contador = (vista - 1) * 5;
					// diagonal izquierda arriba
				} else if (targetX > this.x && targetY < this.y) {
					vista = 3;
					contador = (vista - 1) * 5;
					// diagonal derecha abajo
				} else if (targetX < this.x && targetY > this.y) {
					vista = 4;
					contador = (vista - 1) * 5;
				}
			}

			// moverse en la direccion asignada
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}

		if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.dir.x *= -1;
		}
		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.dir.y *= -1;
		}
	}

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

	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		if(contador ==5*vista){
			contador= (vista-1)*5;
		}
		// Imagen Personaje
		app.image(cannibal[contador], x, y,100,100);
		app.fill(255,0,0);
		if(app.frameCount%10 == 0){
			contador++;
		}
		
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
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	public boolean recibirDano(EspecieAbstracta lastimador) {
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
