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

	private PImage bird, nino;
	private int vida;
	private float energia, vel;
	private EspecieAbstracta parejaCerca;
	private PlantaAbstracta plantaCerca;
	private PVector pos;
	private int display;
	private int ciclo;
	private int estadoVeneno, tiempoEnvenenado;
	private boolean tenerHijo, comerPlanta;
	private boolean encontro;

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

	private final int LIMITE_APAREO = 100;
	private Random random;

	public BuhoApareable(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.energia = 300;
		this.vel = 1;

		ciclo = 0;
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.bird = app.loadImage("propheticData/Herb.png");
		this.nino = app.loadImage("propheticData/Hijo.png");

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));

		estadoVeneno = 0;
		tiempoEnvenenado = 0;

		cargarGrafica();

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			sumarFrames();
			frameEnfermeda();
			frameMuerte();
			vuelo();
			mover();
			veneno();
			try {
				Thread.sleep(10);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		BuhoHijo kid = new BuhoHijo(ecosistema, nino);
		kid.setX(this.x);
		kid.setY(this.y);
		ecosistema.agregarEspecie(kid);
		return kid;
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		pintarSombra();
		if (frameMuerte < 5) {
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
	}

	@Override
	public void mover() {
		if (energia > 0) {
			if (energia > LIMITE_APAREO && tenerHijo) {
				buscarPareja();
				if (parejaCerca != null) {
					intentarAparear();
				}
			} else {
				buscarPlanta();
				if (plantaCerca != null) {
					alimentar(plantaCerca);
				}
				if (ciclo % 90 == 0) {
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					redireccionar(new PVector(targetX, targetY));
					calcularImg(new PVector(targetX, targetY));
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
		if (ciclo % 600 == 0) {
			tenerHijo = true;
			comerPlanta = true;
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

	/**
	 * Metodo par buscar otro apareable cercano con el que puede copular Mide si
	 * el otro apareable también posee la suficiente energía para copular
	 */
	private void buscarPareja() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterador = all.listIterator();
		while (!encontro && tenerHijo && iterador.hasNext()) {
			EspecieAbstracta cerca = iterador.next();
			if ((cerca instanceof IApareable) && !cerca.equals(this)) {
				float d = PApplet.dist(x, y, cerca.getX(), cerca.getY());
				if (d < energia * 1.5 && cerca.getEstado() != cerca.MUERTO) {
					parejaCerca = cerca;
					encontro = true;
				}
			}
		}
		if (encontro) {
			redireccionar(new PVector(parejaCerca.getX(), parejaCerca.getY()));
			calcularImg(new PVector(parejaCerca.getX(), parejaCerca.getY()));
		}

		if (!encontro) {
			parejaCerca = null;
		}
	}

	/**
	 * Creación de nuevo hijo cuando la distancia entre los apareables sea menor
	 * a su vida actual
	 */
	private void intentarAparear() {
		float d = PApplet.dist(x, y, parejaCerca.getX(), parejaCerca.getY());
		if ((d < vida) && energia > 0) {
			IApareable a = (IApareable) parejaCerca;
			ecosistema.agregarEspecie(aparear(a));
			energia -= 50;
			tenerHijo = false;
			encontro = false;

		}
	}

	/**
	 * Metodo para alimentarse de las plantas a las que se acerque
	 * 
	 * @param planta
	 */
	private void alimentar(PlantaAbstracta planta) {
		if (planta != null) {
			float d = PApplet.dist(x, y, planta.getX(), planta.getY());
			if (d < 80 && comerPlanta) {
				if ((planta instanceof Venenosa) && estadoVeneno == 0) {
					estado = ENVENENADO;
					estadoVeneno = 1;
				} else if ((planta instanceof Hojas)) {
					energia += 20;
					estado = EXTASIS;
					estadoVeneno = 0;
					comerPlanta = false;
				} else {
					energia += 20;
					comerPlanta = false;
				}
				planta.recibirDano(this);
				comerPlanta = false;
			}
		}
	}

	/**
	 * Metodo para rastrear plantas cercanas e ir a la posicion de estas
	 */
	private void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			PlantaAbstracta p = iterador.next();
			float d = PApplet.dist(x, y, p.getX(), p.getY());
			if (d < energia * 2 && comerPlanta) {
				encontro = true;
				plantaCerca = p;
				redireccionar(new PVector(plantaCerca.getX(), plantaCerca.getY()));
				calcularImg(new PVector(plantaCerca.getX(), plantaCerca.getY()));
				comerPlanta = false;
			}
		}

		if (!encontro) {
			plantaCerca = null;
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

	/**
	 * Metodo para calcular la perspectiva en la que se debe colocar al
	 * personaje
	 * 
	 * @param target
	 */
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

	public void buscarCria() {

	}

	public void divisarPredador() {

	}

	public void huir() {

	}

	public void defenderCria() {

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
				String especie = "apareable";
				String vista = vistas[j];
				vuelo[j][k] = app.loadImage("propheticData/" + especie + "/" + especie + "_" + vista + "/" + especie
						+ "_" + vista + "_" + k + ".png");
			}
		}

		for (int j = 0; j < mascarasHeridas.length; j++) {
			for (int k = 0; k < mascarasHeridas[j].length; k++) {
				String especie = "apareable";
				String vista = vistas[j];
				mascarasHeridas[j][k] = app
						.loadImage("propheticData/" + especie + "/mascaras/mascara_" + vista + "_herido_" + k + ".png");
			}
		}

		for (int i = 0; i < mascarasNormales.length; i++) {
			String especie = "apareable";
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