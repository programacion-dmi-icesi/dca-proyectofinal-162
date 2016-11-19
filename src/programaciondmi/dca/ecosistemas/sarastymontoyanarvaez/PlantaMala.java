package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class PlantaMala extends PlantaAbstracta {

	private boolean mostrar =true;

	public PlantaMala(EcosistemaAbstracto ecosistema) {
		// TODO Auto-generated constructor stub
	}

	public PlantaMala(int x, int y) {
		super(x, y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		if (mostrar) {
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.fill(255, 0, 0);
			app.ellipse(x+50, y+50, 30, 30);
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 100) {
			mostrar = false;
			return true;
		}
		return false;
	}

}
