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
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class BuhoDepredador extends EspecieAbstracta implements ICarnivoro {

	private int vida;
	private float energia, vel;
	private PlantaAbstracta plantaCerca;
	private EspecieAbstracta comestible;
	private PVector pos;
	private int ciclo;
	private int estadoVeneno, tiempoEnvenenado;
	private boolean comerPlanta, puedeAtacar;
	private boolean encontro, encontroPlanta;

	private PImage[][] vuelo;
	private PImage[][] mascarasHeridas;
	private PImage[] mascarasNormales;
	private PImage[] enfermedad;
	private PImage[] muerte;
	private int frame, frameEnfermo, frameMuerte, sumaEnfermo, direccion, estadoHerido, estadoVenenoso,
			resistenciaAtaques, resistenciaVeneno;
	private float incremento, amplitud, seno, cambio;
	private boolean enfermo, herido, muerto;

	private String[] vistas;
	private String[] estados;

	private final int LIMITE_COMER = 100;
	private Random random;

	public BuhoDepredador(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 70;
		this.energia = 200;
		this.vel = (float) 1.8;

		ciclo = 0;

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));

		cargarGrafica();

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (true) {
			sumarFrames();
			frameEnfermeda();
			frameMuerte();
			vuelo();
			mover();
			veneno();
			if (frameMuerte >= 11) {
				Mundo.ObtenerInstancia().getEspecies().remove(this);
				this.ecosistema.getEspecies().remove(this);
			}
			try {
				Thread.sleep(10);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			float d = PApplet.dist(x, y, victima.getX(), victima.getY());
			if (d < 15 && puedeAtacar) {
				energia += 10;
				victima.recibirDano(this);
				puedeAtacar = false;
				encontro = false;
			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		pintarSombra();
		if (frameMuerte < 5 && vida > 0) {
			if (direccion == 0) {
				if (herido) {
					app.image(mascarasHeridas[direccion][estadoHerido], x, y + seno);
				} else {
					app.image(mascarasNormales[direccion], x, y + seno);
				}
			}
			app.image(vuelo[direccion][frame], x, y + seno);

			if (enfermo) {
				app.image(enfermedad[frameEnfermo], x, y + seno);
			}
			if (direccion != 0) {

				if (herido) {
					app.image(mascarasHeridas[direccion][estadoHerido], x, y + seno);
				} else {
					app.image(mascarasNormales[direccion], x, y + seno);
				}
			}
		}
		app.image(muerte[frameMuerte], x, y + seno);

		app.fill(150, 150, 0);
		app.rectMode(3);
		app.rect(x, y - 40, vida, 10);
	}

	@Override
	public void mover() {
		if (energia > 0 && vida > 0) {
			if (energia > LIMITE_COMER) {
				buscarComida();
				if (comestible != null) {
					comer(comestible);
				}

				if (!encontro) {
					if (ciclo % 90 == 0) {
						int targetX = random.nextInt();
						int targetY = random.nextInt();
						redireccionar(new PVector(targetX, targetY));
						calcularImg(new PVector(targetX, targetY));
					}
				}

			} else {
				buscarPlanta();
				if (plantaCerca != null) {
					alimentar(plantaCerca);
				}
				if (!encontroPlanta) {
					if (ciclo % 90 == 0) {
						int targetX = random.nextInt();
						int targetY = random.nextInt();
						redireccionar(new PVector(targetX, targetY));
						calcularImg(new PVector(targetX, targetY));
					}
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

		if (this.x > Mundo.ObtenerInstancia().getApp().width) {
			direccion = 1;
		}

		if (this.x < 0) {
			direccion = 3;
		}

		if (this.y > Mundo.ObtenerInstancia().getApp().height) {
			direccion = 0;
		}

		if (this.y < 0) {
			direccion = 2;
		}

		if (energia <= 0) {
			vida -= 5;
		}

		if (vida <= 0) {
			estado = MUERTO;
		}

		if (ciclo % 300 == 0) {
			comerPlanta = true;
			puedeAtacar = true;
		}
	}

	/**
	 * Metodo para buscar un organismo distinto al de los de su ecosistema para
	 * ser comido
	 */
	private void buscarComida() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterador = all.listIterator();
		while (!encontro && iterador.hasNext()) {
			EspecieAbstracta com = iterador.next();
			if (!com.equals(this)) {
				if (com instanceof BuhoApareable || com instanceof BuhoCanibal || com instanceof BuhoHijo
						|| com instanceof MurHerbivoro) {
					float d = PApplet.dist(x, y, com.getX(), com.getY());
					if (d < energia * 2 && puedeAtacar && com.recibirDano(this)) {
						comestible = com;
						encontro = true;
					}
				}
			}
		}
		if (encontro) {
			redireccionar(new PVector(comestible.getX(), comestible.getY()));
			calcularImg(new PVector(comestible.getX(), comestible.getY()));
		}

		if (!encontro) {
			comestible = null;
		}
	}

	/**
	 * Metodo para buscar una planta que se encuentre cerca y que la energia del
	 * organismo determine el rango
	 */
	private void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();
		while (!encontroPlanta && iterador.hasNext()) {
			PlantaAbstracta p = iterador.next();
			float d = PApplet.dist(x, y, p.getX(), p.getY());
			if (d < energia * 2 && comerPlanta && p.recibirDano(this)) {
				plantaCerca = p;
				encontroPlanta = true;
			}
		}

		if (encontroPlanta) {
			redireccionar(new PVector(plantaCerca.getX(), plantaCerca.getY()));
			calcularImg(new PVector(plantaCerca.getX(), plantaCerca.getY()));
		}

		if (!encontroPlanta) {
			plantaCerca = null;
		}
	}

	/**
	 * Metodo para alimentarse de la planta más cerca
	 * 
	 * @param planta
	 */
	private void alimentar(PlantaAbstracta planta) {
		if (planta != null && comerPlanta) {
			float d = PApplet.dist(x, y, planta.getX(), planta.getY());
			if (d < 15) {
				if ((planta instanceof Venenosa) && estadoVeneno == 0) {
					estado = ENVENENADO;
					estadoVeneno = 1;
					energia += 10;
					vida -= 5;
					vel -= 0.01;
					enfermo = true;
				} else if ((planta instanceof Hojas)) {
					estado = EXTASIS;
					energia += 20;
					vida += 2;
					vel += 0.02;
					estadoVeneno = 0;
					enfermo = false;
				}
				if (planta.recibirDano(this)) {
					planta.recibirDano(this);
				} else {
					Mundo.ObtenerInstancia().getPlantas().remove(planta);
					this.ecosistema.getPlantas().remove(planta);
				}
				comerPlanta = false;
				encontroPlanta = false;
			}
		}
	}

	/**
	 * Metodo para demostrar tanto visualmente como en datos, el estado de
	 * Veneno de el personaje
	 */
	private void veneno() {
		switch (estadoVeneno) {
		case 1:
			if (tiempoEnvenenado % 60 == 0 && vida > 0) {
				vida--;
			}
			break;
		case 2:
			if (tiempoEnvenenado % 60 == 0 && vida > 0) {
				vida -= 2;
			}
			break;
		case 3:
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

	private void cargarGrafica() {

		vistas = new String[4];
		estados = new String[3];

		vistas[0] = "posterior";
		vistas[1] = "izquierda";
		vistas[2] = "frontal";
		vistas[3] = "derecha";

		estados[0] = "normal";
		estados[1] = "herido";
		estados[2] = "enfermo";

		this.vuelo = new PImage[vistas.length][30];
		this.mascarasNormales = new PImage[vistas.length];
		this.mascarasHeridas = new PImage[vistas.length][3];
		this.enfermedad = new PImage[30];
		this.muerte = new PImage[12];
		this.cambio = 5;
		this.enfermo = false;
		this.herido = false;
		this.muerto = false;
		this.estadoHerido = 2;
		this.estadoVeneno = 29;
		this.frameEnfermo = 0;
		this.sumaEnfermo = 1;
		this.direccion = 2;

		PApplet app = Mundo.ObtenerInstancia().getApp();

		for (int j = 0; j < vuelo.length; j++) {
			for (int k = 0; k < vuelo[j].length; k++) {
				String especie = "depredador";
				String vista = vistas[j];
				vuelo[j][k] = app.loadImage("propheticData/" + especie + "/" + especie + "_" + vista + "/" + especie
						+ "_" + vista + "_" + k + ".png");
			}
		}

		for (int j = 0; j < mascarasHeridas.length; j++) {
			for (int k = 0; k < mascarasHeridas[j].length; k++) {
				String especie = "depredador";
				String vista = vistas[j];
				mascarasHeridas[j][k] = app
						.loadImage("propheticData/" + especie + "/mascaras/mascara_" + vista + "_herido_" + k + ".png");
			}
		}

		for (int i = 0; i < mascarasNormales.length; i++) {
			String especie = "depredador";
			String vista = vistas[i];
			mascarasNormales[i] = app
					.loadImage("propheticData/" + especie + "/mascaras/mascara_" + vista + "_normal.png");
		}

		for (int i = 0; i < enfermedad.length; i++) {
			if (i < muerte.length) {
				muerte[i] = app.loadImage("propheticData/muerte/muerte_" + i + ".png");
			}
			enfermedad[i] = app.loadImage("propheticData/enfermedad/enfermedad_" + i + ".png");
		}
	}

	private void sumarFrames() {
		if (ciclo % cambio == 0) {
			frame++;
			if (frame > 29 - 1) {
				frame = 0;
			}
		}
	}

	private void frameEnfermeda() {
		if (ciclo % cambio == 0) {
			frameEnfermo += sumaEnfermo;
			if (frameEnfermo >= estadoVeneno) {
				sumaEnfermo = -sumaEnfermo;
			} else if (frameEnfermo == 0) {
				sumaEnfermo = -sumaEnfermo;
			}
		}

	}

	private void frameMuerte() {
		if (vida <= 0) {
			if (ciclo % cambio == 0) {
				frameMuerte++;
				if (frameMuerte >= muerte.length) {
					frameMuerte = muerte.length - 1;
					muerto = true;
				}
			}
		}

	}

	private void vuelo() {
		this.incremento += 0.045;
		this.amplitud = 15;
		this.seno = (PApplet.sin(incremento) * amplitud) * -1;
	}

	private void pintarSombra() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(0, 50);
		app.noStroke();
		app.ellipse(x, y + 70, 40 + seno, 10);

	}

	private void calcularImg(PVector target) {

		PVector location = new PVector(x, y);
		float disX = target.x - location.x;
		float disY = target.y - location.y;

		float teta = PApplet.atan2(disY, disX);

		teta *= 180 / PApplet.PI;

		if (teta < 0)
			teta = 360 + teta;

		int d = (int) teta;

		if (d <= 45 && d >= 0 && d <= 360 && d >= 315) {
			direccion = 3; // Derecha
		}

		if (d <= 315 && d > 225) {
			direccion = 0; // Frontal
		}

		if (d <= 225 && d > 135) {
			direccion = 1; // Izquierda
		}

		if (d <= 135 && d > 45) {
			direccion = 2; // Posterior
		}
	}

	/**
	 * Metodo para direccionar el organismo a una posicion especifica
	 * 
	 * @param target
	 */
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
