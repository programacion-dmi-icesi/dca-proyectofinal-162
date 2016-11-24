package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.*;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.HijoBlanco;
import programaciondmi.dca.ejecucion.Mundo;

public class Herbivoro extends EspecieAbstracta implements IHerbivoro, IApareable {

	private int vida, velocidad;
	private PVector dir;
	private PImage[] herbi = new PImage[5];

	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float fuerza;
	private float energia;
	private EspecieAbstracta parejaCercana;

	// Constantes


	private int ciclo;

	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;

	private int xP, yP, vX, vY, vXD = 50, vYD = 50;

	public Herbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);

		this.vida = 20;
		this.velocidad = 2;
		this.fuerza = 100;
		this.energia = 250;

		PApplet app = Mundo.ObtenerInstancia().getApp();
		ballX = (int) app.random(0, app.width-400);
		ballY = (int) app.random(0, app.height-300);
		// Imagenes
		// PApplet app = Mundo.ObtenerInstancia().getApp();


		ciclo = 0;

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 5;
			}
		}
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(20);
				ciclo++;
				reproducir();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		herbi[0] = app.loadImage("../data/HerbivoroUno.png");
		herbi[1] = app.loadImage("../data/HerbivoroDos.png");
		herbi[2] = app.loadImage("../data/HerbivoroTres.png");
		herbi[3] = app.loadImage("../data/HerbivoroCuatro.png");
		herbi[4] = app.loadImage("../data/HerbivoroCinco.png");

		if (vida == 20) {
			app.image(herbi[0], ballX, ballY);
		}
		if (vida == 15) {
			app.image(herbi[1], ballX, ballY);
		}
		if (vida == 10) {
			app.image(herbi[2], ballX, ballY);
		}
		if (vida == 5) {
			app.image(herbi[3], ballX, ballY);
		}
		if (vida == 0) {
			app.image(herbi[4], ballX, ballY);
		}

		if(app.mousePressed==true){
			System.out.println(app.mouseX);
			System.out.println(app.mouseY);
		}
	}

	@Override
	public void mover() {
		int vel = 5;
		if (ciclo % vel == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();

			ballX = (float) (ballX + 10.8 * ballXDirection);
			ballY = (float) (ballY + 8.8 * ballYDirection);

			if (ballX > app.width - 400 || ballX < -500) {
				ballXDirection *= -1;
			}
			if (ballY > app.height - 600 || ballY < -500) {
				ballYDirection *= -1;
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(ballX, ballY, lastimador.getX(), lastimador.getY()) < (1)) {
			vida -= 5;
			try {
				if (vida >= 20) {
					lastimador.setEstado(NORMAL);
				}
				if (vida >= 15 && vida < 20) {
					lastimador.setEstado(ENVENENADO);
				}
				if (vida >= 10 && vida < 15) {
					lastimador.setEstado(ENFERMO);
				}
				if (vida >= 5 && vida < 10) {
					lastimador.setEstado(EXTASIS);
				}
				if (vida >= 0 && vida < 5) {
					lastimador.setEstado(MUERTO);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}

		return false;
	}


	public EspecieAbstracta aparear(IApareable apareable) {
		Hijo hijo = new Hijo(ecosistema);
		hijo.setX(this.x);
		hijo.setY(this.y);
		ecosistema.agregarEspecie(hijo);
		return hijo;
	}
	
	public void reproducir(){
		
		//Ayuda de Junior Rodriguez
		PApplet app = Mundo.ObtenerInstancia().getApp();
		synchronized (ecosistema.getEspecies()) {
			for (EspecieAbstracta especie : ecosistema.getEspecies()) {
				if (especie != this && especie instanceof IApareable) {
					IApareable apareable = (IApareable) especie;
					float d = app.dist(especie.getX(), especie.getY(), this.ballX, this.ballY);

					//if (app.frameCount%60==0) {
						if (d < 100) {
							EspecieAbstracta hijo = aparear(apareable);
							ecosistema.getEspecies().add(hijo);

						}

					//}
				}

			}
		}
	}

	
	private void intentarAparear() {

		PApplet app = Mundo.ObtenerInstancia().getApp();

		if (app.dist(ballX, ballY, parejaCercana.getX(), parejaCercana.getY()) < 30) {
			IApareable a = (IApareable) parejaCercana;
			ecosistema.agregarEspecie(aparear(a));
			// perder energia
			energia -= 50;

		}
	}
}
