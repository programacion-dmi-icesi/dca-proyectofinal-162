package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Omnivoro extends EspecieAbstracta implements IOmnivoro{

	private int vida, velocidad;
	private int ciclo;

	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;

	private int xP, yP, vX, vY, vXD = 50, vYD = 50;
	
	public Omnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		
		this.vida = 50;
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
				Thread.sleep(15);
				ciclo++;
				PApplet app = Mundo.ObtenerInstancia().getApp();

				ballX = (float) (ballX + 10.8 * ballXDirection);
				ballY = (float) (ballY + 8.8 * ballYDirection);

				if (ballX > app.width - 25 || ballX < 25) {
					ballXDirection *= -1;
				}
				if (ballY > app.height - 25 || ballY < 25) {
					ballYDirection *= -1;
				}
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(150, 150, 0);

		app.ellipse(ballX, ballY, 60, 60);
	}

	@Override
	public void mover() {
		if (ciclo % 60 == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();

			ballX = (float) (ballX + 10.8 * ballXDirection);
			ballY = (float) (ballY + 8.8 * ballYDirection);

			if (ballX > app.width - 25 || ballX < 25) {
				ballXDirection *= -1;
			}
			if (ballY > app.height - 25 || ballY < 25) {
				ballYDirection *= -1;
			}

		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

}
