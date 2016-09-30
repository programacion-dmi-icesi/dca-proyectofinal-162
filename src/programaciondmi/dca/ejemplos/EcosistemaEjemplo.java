package programaciondmi.dca.ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaEjemplo extends EcosistemaAbstracto {

	public EcosistemaEjemplo() {
		super();
	}


	@Override
	public void dibujar() {
		Iterator<EspecieAbstracta> iteradorEspecies = this.especies.iterator();
		while(iteradorEspecies.hasNext()){
			EspecieAbstracta actual = iteradorEspecies.next();
			actual.dibujar();
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		EspecieBlanca nueva = new EspecieBlanca();
		especies.add(nueva);
		
		nueva = new EspecieBlanca();
		especies.add(nueva);
		
		return especies;
	}


	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}



}
