package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Carnivoro extends EspecieAbstracta implements ICarnivoro {

	private int vida, velocidad;
	private int ciclo;
	private Mundo m;

	
	private int dir,mover;


	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;
	private PImage transparente;
	private int xP, yP, vX, vY, vXD = 50, vYD = 50;

	private PImage carni[] = new PImage[4];

	public Carnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 50;

		PApplet app = Mundo.ObtenerInstancia().getApp();
		ballX = (int) app.random(0, app.width);
		ballY = (int) app.random(0, app.height);

		xP = (int) app.random(0, app.width);
		yP = (int) app.random(0, app.height);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(1);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();

		carni[0] = app.loadImage("../data/vistas/car_aba.png");
		carni[1] = app.loadImage("../data/vistas/car_arri.png");
		carni[2] = app.loadImage("../data/vistas/car_izq.png");
		carni[3] = app.loadImage("../data/vistas/car_der.png");

		
		app.image(carni[dir], ballX, ballY);
		
		
		// direciones -----------------------------------

		if (ballXDirection >= 0) {
			dir=3;

		}else

		if (ballXDirection <= 0) {
			dir=2;

		}else

		if (ballYDirection >= 0) {
			dir=0;

		}else

		if (ballYDirection <=0) {
			dir=1;
		}
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();

			ballX = (float) (ballX + 10.8 * ballXDirection);
			ballY = (float) (ballY + 8.8 * ballYDirection);

			if (ballX > app.width - 25 || ballX < -100) {
				ballXDirection *= -1;
			}
			if (ballY > app.height - 25 || ballY < -100) {
				ballYDirection *= -1;
			}

		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) < 500) {
			vida -= 5;
			try {
				if (vida <= 50 && vida > 30) {
					lastimador.setEstado(NORMAL);
				}
				if (vida <= 30 && vida > 20) {
					lastimador.setEstado(ENVENENADO);
				}
				if (vida <= 20 && vida > 10) {
					lastimador.setEstado(ENFERMO);
				}
				if (vida <= 10 && vida > 0) {
					lastimador.setEstado(EXTASIS);
				}
				if (vida == 0) {
					lastimador.setEstado(MUERTO);

				}

			} catch (Exception e) {
				e.printStackTrace();
				// vida -= 5;
			}
			return true;

		}

		return false;
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		if (!todas.equals(this)) {
			for (int i = 0; i < todas.size(); i++) {
				comer(todas.get(i));

				System.out.println("Comio");
			}
		}
	}

}
