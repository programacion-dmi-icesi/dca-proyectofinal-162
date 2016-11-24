package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class Cannibalbot extends EspecieAbstracta implements ICanibal {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int vida;
	private float fuerza, energia, defensa;
	private float velocidad;
	private int ciclo;
	private PVector dir;
	PImage[] cannibal = new PImage[20];
	PImage poison, ivel, ienergy, iforce, idefense;
	int posicionpj = 0;
	int contador;
	int movCannibalbot = 0;
	private Random random;
	int vista;
	int movCanibal;
	private EspecieAbstracta especieCercana;
	
	private boolean animonbuena =false;
	private boolean animonmala =false;
	public Cannibalbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 100;
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

		// Iconos
		poison = app.loadImage("DataTikiBots/iconos/envenenado.png");
		ivel = app.loadImage("DataTikiBots/iconos/iconoVelocidad.png");
		ienergy = app.loadImage("DataTikiBots/iconos/iconoEnergia.png");
		iforce = app.loadImage("DataTikiBots/iconos/iconoFuerza.png");
		idefense = app.loadImage("DataTikiBots/iconos/iconoDefensa.png");

		Thread nt = new Thread(this);
		nt.start();

	}

	public void comer(EspecieAbstracta victima) {
		if (victima.recibirDano(this)) {
			try {
					if(vida!=100){
						vida+=20;
					}
					animonbuena =true;
					animonmala=false;
					fuerza +=20;
					defensa +=20;
					velocidad += 0.05;
					
					switch (vida) {
					case 100:
						setEstado(NORMAL);
						break;
					case 80:
						setEstado(ENFERMO);
						break;
					case 60:
						setEstado(EXTASIS);
						break;
					case 40:
						setEstado(MUERTO);
						animonbuena = false;
						animonmala = false;
						break;
					}

				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Mundo.ObtenerInstancia().getEspecies().remove(victima);
			this.ecosistema.getEspecies().remove(victima);
			energia += 5;
		}
	}

	public void mover() {
		if (energia > 0) {
			buscarComida();
			
			if(especieCercana != null){
				comer(especieCercana);
			}

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

		/*if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.dir.x *= -1;
		}
		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.dir.y *= -1;
		}*/
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
		
		if (animonbuena==true) {
			app.fill(0);
			app.text("+20", x,y-70);
			app.text("+20", x+30,y-70);
			app.text("+0.05", x+60,y-70);
			app.image(iforce, x, y-60, 30,30);
			app.image(idefense, x+30, y-60, 30,30);
			app.image(ivel, x+60, y-60, 30,30);
		
		}
		if (animonmala==true) {
			app.fill(0);
			app.text("-20", x,y-70);
			app.text("-20", x+30,y-70);
			app.text("-0.01", x+60,y-70);
			app.image(iforce, x, y-60, 30,30);
			app.image(idefense, x+30, y-60, 30,30);
			app.image(ivel, x+60, y-60, 30,30);
		
		}
		
		if (contador == 5 * vista) {
			contador = (vista - 1) * 5;
		}
		// Imagen Personaje
		app.image(cannibal[contador], x, y, 100, 100);
		app.fill(255, 0, 0);
		if (app.frameCount % 10 == 0) {
			contador++;
		}
	
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
	
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida / 2)) {
			vida -= 20;
			try {
				lastimador.setEstado(NORMAL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	private void buscarComida() {
		synchronized (Mundo.ObtenerInstancia().getEspecies()) {
			List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
			ListIterator<EspecieAbstracta> iterador = todas.listIterator();
			boolean encontro = false;
			while (!encontro && iterador.hasNext()) {
				EspecieAbstracta e = iterador.next();
				if ((e instanceof Birdbot) && !e.equals(this)) {
					float dist = PApplet.dist(x, y, e.getX(), e.getY());
	
					if (dist < energia) {
						encontro = true;
						especieCercana = e;
						// Cambiar la dirección
						cambiarDireccion(new PVector(especieCercana.getX(), especieCercana.getY()));
					}
					
				}
				if ((e instanceof Magusbot) && !e.equals(this)) {
					float dist = PApplet.dist(x, y, e.getX(), e.getY());
					if (dist < energia) {
						encontro = true;
						especieCercana = e;
						// Cambiar la dirección
						cambiarDireccion(new PVector(especieCercana.getX(), especieCercana.getY()));
					}
				}
				if ((e instanceof SaberBot) && !e.equals(this)) {
					float dist = PApplet.dist(x, y, e.getX(), e.getY());
					if (dist < energia) {
						encontro = true;
						especieCercana = e;
						// Cambiar la dirección
						cambiarDireccion(new PVector(especieCercana.getX(), especieCercana.getY()));
					}
				}
			}
			// asegurarse de que la referencia sea null;
			if (!encontro) {
				especieCercana = null;
				//System.out.println("No encontró una pareja cercana");
			}
		}
	}
}
