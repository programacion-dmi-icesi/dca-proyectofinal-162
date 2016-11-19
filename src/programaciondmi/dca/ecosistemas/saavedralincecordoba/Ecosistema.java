package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Ecosistema extends EcosistemaAbstracto {
public PApplet app;

	public Ecosistema() {
		super();
		app = Mundo.ObtenerInstancia().getApp();
	}
	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		Hervivoro hervivoro= new Hervivoro(this);
		especies.add(hervivoro);
		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		Hervivoro hervivoro= new Hervivoro(this);
		especies.add(hervivoro);
		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dibujar() {
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
		}
		
	}


}
