package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.List;
import java.util.ListIterator;
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
	private float fuerza, defensa;
	private int velocidad;
	private float energia;
	private int ciclo;
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private PVector dir;
	PImage[] magobot = new PImage[20];
	PImage mDefensa, mEnergia, mFuerza, mVelocidad, mPocion;
	int posicionpj;
	int contador;
	int vista;
	int movMagusBot;
	private Random random;
	private boolean animonbuena = false;
	private boolean animonmala = false;
	private PlantaAbstracta plantaCerca;

	public Magusbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 100;
		this.velocidad = 2;
		this.energia = 250;
		this.fuerza = 100;
		this.defensa = 100;

		contador = 0;
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

		mPocion = app.loadImage("DataTikiBots/iconos/envenenado.png");
		mVelocidad = app.loadImage("DataTikiBots/iconos/iconoVelocidad.png");
		mEnergia = app.loadImage("DataTikiBots/iconos/iconoEnergia.png");
		mFuerza = app.loadImage("DataTikiBots/iconos/iconoFuerza.png");
		mDefensa = app.loadImage("DataTikiBots/iconos/iconoDefensa.png");

		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	public void dibujar() {// pintar

		// animaciones encima de la barra superior (iconos)

		if (animonbuena == true) {
			app.fill(0);
			app.text("+20", x, y - 70);
			app.text("+20", x + 30, y - 70);
			app.text("+0.05", x + 60, y - 70);
			app.image(mFuerza, x, y - 60, 30, 30);
			app.image(mDefensa, x + 30, y - 60, 30, 30);
			app.image(mVelocidad, x + 60, y - 60, 30, 30);

		}
		if (animonmala == true) {
			app.fill(0);
			app.text("-20", x, y - 70);
			app.text("-20", x + 30, y - 70);
			app.text("-0.01", x + 60, y - 70);
			app.image(mFuerza, x, y - 60, 30, 30);
			app.image(mDefensa, x + 30, y - 60, 30, 30);
			app.image(mVelocidad, x + 60, y - 60, 30, 30);

		}

		app.noStroke();
		switch (vida) {

		case 100:

			app.fill(0, 255, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			app.rect(x + 65, y - 20, 20, 10);
			app.rect(x + 85, y - 20, 20, 10);
			break;
		case 80:
			app.fill(255, 255, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			app.rect(x + 65, y - 20, 20, 10);
			break;
		case 60:
			app.fill(255, 0, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			break;
		case 40:
			app.fill(255, 0, 255);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			break;
		case 20:
			app.fill(255, 0, 255);
			app.rect(x + 05, y - 20, 20, 10);
			break;
		case 0:

			break;
		}

		if (getEstado() == NORMAL) {

			app.noTint();
		}
		if (getEstado() == ENFERMO) {

			app.tint(0, 255, 0);
		}

		if (getEstado() == ENVENENADO) {

			app.tint(156, 28, 247);
		}

		if (getEstado() == EXTASIS) {
			app.tint(28, 45, 247);
		}

		if (getEstado() == MUERTO) {
			app.image(mPocion, x + 25, y - 60, 50, 50);
			app.tint(63, 63, 63);

		}

		PApplet app = Mundo.ObtenerInstancia().getApp();
		// Image Personaje

		if (contador == 5 * vista) {
			contador = (vista - 1) * 5;
		}

		// System.out.println(magobot[contador]);

		app.image(magobot[contador], x, y, 100, 100);
		app.fill(255, 0, 0);

		if (app.frameCount % 10 == 0) {
			contador++;
		}

		app.noStroke();
		switch (vida) {

		case 100:
			app.fill(0, 255, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			app.rect(x + 65, y - 20, 20, 10);
			app.rect(x + 85, y - 20, 20, 10);
			break;
		case 80:
			app.fill(255, 255, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			app.rect(x + 65, y - 20, 20, 10);
			break;
		case 60:
			app.fill(255, 0, 0);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			app.rect(x + 45, y - 20, 20, 10);
			break;
		case 40:
			app.fill(255, 0, 255);
			app.rect(x + 05, y - 20, 20, 10);
			app.rect(x + 25, y - 20, 20, 10);
			break;
		case 20:
			app.fill(255, 0, 255);
			app.rect(x + 05, y - 20, 20, 10);
			break;
		case 0:

			break;
		}

	}

	public void mover() {
		// TODO Auto-generated method stub
		if (energia > 0) {

			// buscarComida();
		} else {
			buscarComida();

			if (plantaCerca != null) {
				comerPlanta(plantaCerca);
			}

			if (ciclo % 30 == 0 && plantaCerca == null) {
				// Definir una direccion aleatoria cada 3 segundos
				// Siguiente posici�n en x
				int targetX = random.nextInt();
				// Siguiente posici�n en y
				int targetY = random.nextInt();
				cambiarDireccion(new PVector(targetX, targetY));
				// System.out.println(x+" "+y);
				if (targetX > this.x && targetY > this.y) {// derechaArriba
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

			// moverse en la direcci�n asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}

	}

	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
			}
		}
	}

	private void buscarComida() {
		synchronized (this.ecosistema.getPlantas()) {
			List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
			ListIterator<PlantaAbstracta> iterador = all.listIterator();
			boolean encontro = false;
			// System.out.println(all.size());

			while (iterador.hasNext()) {
				PlantaAbstracta planta = iterador.next();
				if (planta instanceof PlantaMala) {
					PlantaMala pmala = (PlantaMala) planta;
					float distancia = app.dist(x, y, pmala.getX(), pmala.getY());
					if (distancia < energia) {
						encontro = true;
						plantaCerca = planta;
						cambiarDireccion(new PVector(pmala.getX(), pmala.getY()));

					}
				} else if (planta instanceof PlantaBuena) {
					PlantaBuena pbuena = (PlantaBuena) planta;
					float distancia = app.dist(x, y, pbuena.getX(), pbuena.getY());
					if (distancia < energia) {
						encontro = true;
						plantaCerca = planta;
						cambiarDireccion(new PVector(pbuena.getX(), pbuena.getY()));

					}
				}

			}

			if (!encontro) {
				plantaCerca = null;
			}
		}
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana=" + ", dir=" + dir
				+ ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

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

	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		if (victima.recibirDano(this)) {
			try {
				if (victima.getClass() == PlantaMala.class) {
					PlantaMala m = (PlantaMala) victima;
					fuerza -= 20;
					defensa -= 20;
					vida -= 20;
					velocidad -= 0.01;

					animonmala = true;
					animonbuena = false;

					switch (vida) {
					case 100:
						setEstado(NORMAL);

						break;
					case 80:
						setEstado(ENFERMO);
						break;
					case 60:
						setEstado(EXTASIS);// antes de morir
						break;
					case 40:
						setEstado(MUERTO);
						animonbuena = false;
						animonmala = false;
						break;
					}
				}

				if (victima.getClass() == PlantaBuena.class) {
					PlantaBuena m = (PlantaBuena) victima;

					animonbuena = true;
					animonmala = false;
					fuerza += 20;
					defensa += 20;
					velocidad += 0.05;

					if (vida <= 80) {
						vida += 20;
					}

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
						break;
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Mundo.ObtenerInstancia().getPlantas().remove(victima);
			this.ecosistema.getPlantas().remove(victima);
			energia += 5;
		}
	}

}