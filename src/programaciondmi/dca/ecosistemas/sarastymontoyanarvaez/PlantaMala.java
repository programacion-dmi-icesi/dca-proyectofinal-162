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
	private int cantidad = 3;// controla las veces que puede ser comida la
								// planta
	private int tiempoInmune = 0;// tiempo de inmunidad para no ser comida de
									// una sola vez la planta
	PImage planta_xs;
	PImage planta_med;
	PImage planta_large;

	private boolean mostrar = true;

	public PlantaMala(EcosistemaAbstracto ecosistema) {
		// TODO Auto-generated constructor stub
	}

	public PlantaMala(int x, int y) {
		super(x, y);

		// inicio el hilo
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (cantidad >= 1) {
			try {
				Thread.sleep(33);
				// System.out.println(tiempoInmune);

				// tiampo para poder volverse a lastimar
				tiempoInmune--;

				// si la cantidad de lastimadas es menor o iguala cero
				// elimino el hilo
				if (cantidad <= 0) {
					Mundo.ObtenerInstancia().getPlantas().remove(this);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// metodo encargado de pintar la planta de diferentes maneras
	// la planta se pinta dependiendo de la cantidad de lastimadas que ha
	// recibido
	// se controla con la variable cantidad
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

	// metodo encargado de hacer daño al objeto de planta
	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// si la distancia es menor o iguala 30
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30) {
			// si el alstimador es de tipo herviboro o de tipo omnivoro
			if (lastimador instanceof IHerbivoro || lastimador instanceof IOmnivoro) {
				try {
					// si el tiempo de inmunidad es menor o igual a cero
					if (tiempoInmune <= 0) {
						// resto en la variable cantidad para cambiar de imagen
						// y saber que ya he sido golpeado una, dos o ams veces
						cantidad -= 1;
						// envio la variable inmunidad a 100 para controlar un
						// tiempo en el que nos e haga daño a la planta
						tiempoInmune = 100;
						// envio el objeto que me ataco a estado ENVENENADO
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
