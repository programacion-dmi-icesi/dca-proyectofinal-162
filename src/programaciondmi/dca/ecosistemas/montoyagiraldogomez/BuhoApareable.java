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
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.ejecucion.Mundo;

public class BuhoApareable extends EspecieAbstracta implements IApareable {

	private PImage bird,nino;
	private int vida;
	private float energia,vel;
	private EspecieAbstracta parejaCerca;
	private PlantaAbstracta plantaCerca;
	private PVector pos;
	private int display;
	private int ciclo;
	private int estadoVeneno, tiempoEnvenenado;
	private boolean tenerHijo, puedeComer;

	private final int LIMITE_APAREO = 100;
	private Random random;

	public BuhoApareable(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.energia = 300;
		this.vel = 3;

		ciclo = 0;
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.bird = app.loadImage("Herb.png");
		this.nino = app.loadImage("Hijo.png");

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));

		estadoVeneno = 0;
		tiempoEnvenenado = 0;

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
	public EspecieAbstracta aparear(IApareable apareable) {
		BuhoHijo kid = new BuhoHijo(ecosistema,nino);
		kid.setX(this.x);
		kid.setY(this.y);
		ecosistema.agregarEspecie(kid);
		return kid;
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(bird, x, y);
		veneno();
	}

	@Override
	public void mover() {
		if (energia > 0) {
			if (energia > LIMITE_APAREO) {
				buscarPareja();
				if (parejaCerca != null) {
					intentarAparear();
				}
			} else {
				buscarPlanta();
				if (plantaCerca != null) {
					alimentar(plantaCerca);
				}
				if (ciclo % 30 == 0) {
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					redireccionar(new PVector(targetX, targetY));
				}
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
			tenerHijo = true;
			puedeComer = true;
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

	private void buscarPareja() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			EspecieAbstracta cerca = iterador.next();
			if ((cerca instanceof IApareable) && !cerca.equals(this)) {
				float d = PApplet.dist(x, y, cerca.getX(), cerca.getY());
				if (d < energia*1.5 && cerca.getEstado() != cerca.MUERTO && !(cerca instanceof BuhoApareable)) {
					encontro = true;
					parejaCerca = cerca;
					redireccionar(new PVector(parejaCerca.getX(), parejaCerca.getY()));
				} else if (d < energia*1.5 && cerca.getEstado() != cerca.MUERTO && (cerca instanceof BuhoApareable)) {
					if (((BuhoApareable) cerca).getEnergia() > LIMITE_APAREO) {
						encontro = true;
						parejaCerca = cerca;
						redireccionar(new PVector(parejaCerca.getX(), parejaCerca.getY()));
					}
				}
			}
		}
		if (!encontro) {
			parejaCerca = null;
		}
	}

	private void intentarAparear() {
		float d = PApplet.dist(x, y, parejaCerca.getX(), parejaCerca.getY());
		if ((d < vida) && tenerHijo && energia > 0) {
			IApareable a = (IApareable) parejaCerca;
			ecosistema.agregarEspecie(aparear(a));
			energia -= 50;
			tenerHijo = false;

		}
	}

	private void alimentar(PlantaAbstracta planta) {
		if (planta != null) {
			float d = PApplet.dist(x, y, planta.getX(), planta.getY());
			if (d < 80 && puedeComer) {
				if ((planta instanceof Venenosa) && estadoVeneno == 0) {
					estado = ENVENENADO;
					estadoVeneno = 1;
				}
				planta.recibirDano(this);
				puedeComer=false;
			}
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

	private void redireccionar(PVector target) {
		PVector location = new PVector(x, y);
		pos = PVector.sub(target, location);
		pos.normalize();
		pos.mult(vel);
	}

	private void calculcarImg(PVector target) {
		float xO = target.x;
		float yO = target.y;

		if (x - xO < 0) {
			// Left
			display = 1;
		}

		if (x - xO >= 0) {
			// Right
			display = 2;
		}

		if (y - yO < 0) {
			// Up
			display = 3;
		}

		if (y - yO >= 0) {
			// Down
			display = 4;
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (vida > 0) {
			vida -= 5;
			return true;
		}
		return false;
	}

	public float getEnergia() {
		return energia;
	}

	public void setEnergia(float energia) {
		this.energia = energia;
	}

}
