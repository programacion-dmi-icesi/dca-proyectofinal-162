package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class MurHerbivoro extends EspecieAbstracta implements IHerbivoro {

	private PImage bat;
	private int vida;
	private float energia, vel;
	private PlantaAbstracta plantaCerca;
	private PVector pos;
	private int display;
	private int ciclo;
	private int estadoVeneno, tiempoEnvenenado;
	private boolean puedeComer;

	private Random random;

	public MurHerbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.energia = 200;
		this.vel = (float) 3.5;

		ciclo = 0;

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));

		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.bat = app.loadImage("Murcielago.png");
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (victima != null) {
			float d = PApplet.dist(x, y, victima.getX(), victima.getY());
			if(d<80 && puedeComer){
				if ((victima instanceof Venenosa) && estadoVeneno == 0) {
					estado = ENVENENADO;
					estadoVeneno = 1;
				}
				victima.recibirDano(this);
				puedeComer=false;
			}
		}

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(bat, x, y);
		veneno();
	}

	@Override
	public void mover() {
		if (energia > 0) {
			buscarPlanta();
			if (plantaCerca != null) {
				comerPlanta(plantaCerca);
			}
			if (ciclo % 30 == 0) {
				int targetX = random.nextInt();
				int targetY = random.nextInt();
				redireccionar(new PVector(targetX, targetY));
			}

			this.x += pos.x;
			this.y += pos.y;
			energia -= 0.01;

		}

		if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.pos.x *= -1;
		}

		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.pos.y *= -1;
		}

		if (energia < 0) {
			vida -= 5;
		}

		if (vida <= 0) {
			estado = MUERTO;
		}

		PApplet app = Mundo.ObtenerInstancia().getApp();
		if (app.frameCount % 60 == 0) {
			puedeComer = true;
		}

	}

	private void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			PlantaAbstracta p = iterador.next();
			float d = PApplet.dist(x, y, p.getX(), p.getY());
			if (d < energia * 2) {
				encontro = true;
				plantaCerca = p;
				redireccionar(new PVector(plantaCerca.getX(), plantaCerca.getY()));
			}
		}

		if (!encontro) {
			plantaCerca = null;
		}
	}

	private void veneno() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(0, 255, 0);
		app.noStroke();
		switch (estadoVeneno) {
		case 1:
			app.ellipse(x - 30, y - 50, 20, 20);
			if (tiempoEnvenenado % 60 == 0 && vida > 0) {
				vida--;
			}
			break;
		case 2:
			app.ellipse(x - 30, y - 50, 20, 20);
			app.ellipse(x, y - 50, 20, 20);
			if (tiempoEnvenenado % 60 == 0 && vida > 0) {
				vida -= 2;
			}
			break;
		case 3:
			for (int i = -30; i <= 30; i += 30) {
				app.ellipse(x + i, y - 50, 20, 20);
			}
			if (tiempoEnvenenado % 60 == 0 && vida > 0) {
				vida -= 3;
			}
			break;
		}

		if (estadoVeneno < 3 && estadoVeneno > 0) {
			tiempoEnvenenado++;
			if (tiempoEnvenenado % 600 == 0) {
				estadoVeneno++;
				tiempoEnvenenado = 0;
			}
		}
	}

	private void redireccionar(PVector target) {
		PVector location = new PVector(x, y);
		pos = PVector.sub(target, location);
		pos.normalize();
		pos.mult(vel);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (vida > 0) {
			vida -= 5;
			return true;
		}
		return false;
	}

}
