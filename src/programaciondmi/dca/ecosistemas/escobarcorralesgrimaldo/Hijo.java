package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import processing.core.*;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hijo extends EspecieAbstracta {
	private PImage[] hijo = new PImage[4];
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;

	private int ballXDirection = 1, ballYDirection = -1;


	
	private int direccion;
	
	public Hijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
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
	public void dibujar() {
		
		
		PApplet app = Mundo.ObtenerInstancia().getApp();
		hijo[0] = app.loadImage("../data/vistas/hijo_aba.png");
		hijo[1] = app.loadImage("../data/vistas/hijo_arri.png");
		hijo[2] = app.loadImage("../data/vistas/hijo_izq.png");
		hijo[3] = app.loadImage("../data/vistas/hijo_der.png");
		
		
		
		app.image(hijo[0], x, y);
		
		
		
	}

	@Override
	public void mover() {
		if (ciclo % 60 == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();

			x = (int) (x + 10.8 * ballXDirection);
			y = (int) (y + 8.8 * ballYDirection);

			if (x > app.width - 25 || x < 25) {
				ballXDirection *= -1;
			}
			if (y > app.height - 25 || y < 25) {
				ballYDirection *= -1;
			}

		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) < 100) {
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

}
