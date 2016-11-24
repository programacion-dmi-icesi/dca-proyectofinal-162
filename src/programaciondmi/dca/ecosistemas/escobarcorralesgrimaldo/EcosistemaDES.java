package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

public class EcosistemaDES extends EcosistemaAbstracto {
	
	public EcosistemaDES() {
		super();
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		
		Carnivoro car = new Carnivoro(this);
		especies.add(car);
		
		Omnivoro omn = new Omnivoro(this);
		especies.add(omn);

		Herbivoro herb = new Herbivoro(this);
		especies.add(herb);
		
		Canibal can = new Canibal(this);
		especies.add(can);
		
		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		// TODO LLenar las lista de plantas iniciales
		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {

		Herbivoro herb = new Herbivoro(this);
		especies.add(herb);
		
		Canibal can = new Canibal(this);
		especies.add(can);
		
		Carnivoro car = new Carnivoro(this);
		especies.add(car);
		
		Omnivoro omn = new Omnivoro(this);
		especies.add(omn);
		

		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}

	@Override
	public void dibujar() {
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while(iteradorEspecies.hasNext()){
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
		}
	}

}

