package programaciondmi.dca.ejemplos;

import java.util.LinkedList;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaEjemplo extends EcosistemaAbstracto {

	public EcosistemaEjemplo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.ellipse(10, 10, 50, 50);
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblar() {
		// TODO Auto-generated method stub
		return null;
	}



}
