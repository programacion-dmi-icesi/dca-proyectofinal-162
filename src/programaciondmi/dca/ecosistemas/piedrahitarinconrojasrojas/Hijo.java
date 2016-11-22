package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hijo extends EspecieAbstracta {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	
	public Hijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.fill(255,0,0);
		app.ellipse(x, y, 10, 10);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
