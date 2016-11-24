package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaBuena extends PlantaAbstracta {
	private static final int EXTASIS = 3;
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int cantidad = 3;
	private int tiempoInmune = 0;
	PImage planta_xs;
	PImage planta_med;
	PImage planta_large;

	private boolean mostrar = true;

	public PlantaBuena(EcosistemaAbstracto ecosistema) {
		// TODO Auto-generated constructor stub

	}

	public PlantaBuena(int x, int y) {
		super(x, y);
		
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
				tiempoInmune--;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dibujar() {
		planta_xs = app.loadImage("../img/goodplant_xs.png");
		planta_med = app.loadImage("../img/goodplant_med.png");
		planta_large = app.loadImage("../img/goodplant_large.png");
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

						// System.out.println("comio");
						lastimador.setEstado(EXTASIS);
						cantidad --;
						tiempoInmune = 100;
						//Mundo.ObtenerInstancia().getPlantas().remove(this);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad=cantidad;
	}
}