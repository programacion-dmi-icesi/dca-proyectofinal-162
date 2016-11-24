package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class Canibal extends EspecieAbstracta implements ICanibal {

	private PImage[] cani = new PImage[4];

	private int vida, velocidad;
	private PVector dir;
	private int energia;
	private int ciclo;
	
	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;

	private int direccion;

	public Canibal(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 5;

		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));
		
		PApplet app = Mundo.ObtenerInstancia().getApp();
		ballX = (int) app.random(0, app.width);
		ballY = (int) app.random(0, app.height);

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this) {
							if (especie instanceof Carnivoro || especie instanceof Herbivoro || especie instanceof Hijo
									|| especie instanceof Omnivoro) {
								float d = PApplet.dist(especie.getX(), especie.getY(), this.ballX, this.ballY);

								// if (!esperar) {
								if (d < 10) {

									comer(especie);
									especie.setEstado(MUERTO);

									// System.out.println("canibal mata!");
									ecosistema.getEspecies().remove(especie);

									break;
								}

							}

						}
					}
				}
				Thread.sleep(5);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();

		cani[0] = app.loadImage("../data/vistas/cani_aba.png");
		cani[1] = app.loadImage("../data/vistas/cani_arri.png");
		cani[2] = app.loadImage("../data/vistas/cani_izq.png");
		cani[3] = app.loadImage("../data/vistas/cani_der.png");

		app.image(cani[direccion], ballX, ballY);

		if (ballXDirection >= 0) {
			direccion=3;

		}

		if (ballXDirection <= 0) {
			direccion=2;

		}else

		if (ballYDirection >= 0) {
			direccion=0;

		}

		if (ballYDirection <=0) {
			direccion=1;
		}
	}

	@Override
	public void mover() {
		if (ciclo % 10 == 0) {
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
		// Codigo de base
		if (PApplet.dist(ballX, ballY, lastimador.getX(), lastimador.getY()) < 100) {
			vida -= 5;
			try {
				if (vida == 20) {
					lastimador.setEstado(NORMAL);
				}
				if (vida == 15) {
					lastimador.setEstado(ENVENENADO);
				}
				if (vida == 10) {
					lastimador.setEstado(ENFERMO);
				}
				if (vida == 5) {
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
		try {
			victima.setEstado(MUERTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);

	}

}
