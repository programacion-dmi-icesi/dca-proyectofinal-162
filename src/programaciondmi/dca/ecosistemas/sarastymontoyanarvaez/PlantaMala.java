package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {

	private static final int ENVENENADO = 1;
	PApplet app = Mundo.ObtenerInstancia().getApp();
	PImage planta_xs;
	PImage planta_med;
	PImage planta_large;
	private int cantidad = 3;
	private int tiempoInmune = 0;

	private boolean mostrar = true;

	public PlantaMala(EcosistemaAbstracto ecosistema) {
		// TODO Auto-generated constructor stub
	}

	public PlantaMala(int x, int y) {
		super(x, y);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (cantidad > 0) {
			try {
				Thread.sleep(33);
				// System.out.println(tiempoInmune);
				tiempoInmune--;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dibujar() {
		planta_xs = app.loadImage("../img/badplant_xs.png");
		planta_med = app.loadImage("../img/badplant_med.png");
		planta_large = app.loadImage("../img/badplant_large.png");
		if (mostrar) {
			switch (cantidad) {
			case 3:
				app.image(planta_large, x, y);
				break;

			case 2:
				app.image(planta_med, x, y);
				break;

			case 1:
				app.image(planta_xs, x, y);
				break;
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30) {
			if (lastimador instanceof IHerbivoro || lastimador instanceof IOmnivoro) {
				try {
					if (tiempoInmune <= 0) {
						// mostrar = false;
						cantidad -= 1;
						tiempoInmune = 100;
						// System.out.println("comio");
						lastimador.setEstado(ENVENENADO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

}
