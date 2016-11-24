package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Carnivoro extends EspecieAbstracta implements ICarnivoro {

	private int vida, velocidad;
	private int ciclo;
	private Mundo m;

	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;

	private int xP, yP, vX, vY, vXD = 50, vYD = 50;

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
		app.fill(255, 0, 0);

		app.ellipse(ballX, ballY, 30, 30);

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
					
					//boolean esta = Mundo.ObtenerInstancia().getEspecies().remove(lastimador);
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
