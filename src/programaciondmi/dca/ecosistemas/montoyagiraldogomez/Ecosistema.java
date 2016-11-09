package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

public class Ecosistema extends EcosistemaAbstracto {

	public Ecosistema() {
		super();
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		
		return null;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
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
