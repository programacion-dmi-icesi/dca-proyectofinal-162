package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class HijoBlanco extends EspecieAbstracta {

	public HijoBlanco(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(0);
		app.ellipse(x, y, 10, 10);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo.
		return false;
	}

}
