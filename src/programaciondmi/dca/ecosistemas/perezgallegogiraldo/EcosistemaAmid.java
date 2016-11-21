package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.EspecieAzul;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.EspecieBlanca;

public class EcosistemaAmid extends EcosistemaAbstracto {
	
	public EcosistemaAmid() {
		super();
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		AmidCanibal nueva = new AmidCanibal(this);
		especies.add(nueva);
		
		nueva = new AmidCanibal(this);
		especies.add(nueva);
		
		AmidCarnivoro azul = new AmidCarnivoro(this);
		especies.add(azul);
		
		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		AmidCanibal nueva = new AmidCanibal(this);
		especies.add(nueva);
		
		AmidCarnivoro azul = new AmidCarnivoro(this);
		especies.add(azul);
		
		return especies;
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