package programaciondmi.dca.ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaEjemplo extends EcosistemaAbstracto {

	public EcosistemaEjemplo() {
		super();
	}


	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		Iterator<EspecieAbstracta> iteradorEspecies = this.especies.iterator();
		while(iteradorEspecies.hasNext()){
			EspecieAbstracta actual = iteradorEspecies.next();
			actual.dibujar();
			//actual.mover();
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblar() {
		// TODO Auto-generated method stub
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		EspecieBlanca nueva = new EspecieBlanca();
		Thread nt = new Thread(nueva);
		nt.start();
		especies.add(nueva);
		
		return especies;
	}



}
