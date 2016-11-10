package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.SaberBot;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.Herbivoro;
import programaciondmi.dca.ejecucion.Mundo;




public class EcosistemaTikiBots extends EcosistemaAbstracto {

	public EcosistemaTikiBots() {
		super();
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

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		SaberBot nueva = new SaberBot(this);
		especies.add(nueva);
		
		nueva = new SaberBot(this);
		especies.add(nueva);
		
		Herbivoro planta = new Herbivoro(this);
		especies.add(planta);
		
		return especies;
	}


	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		return plantas;
	}


	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
		
		SaberBot nueva = new SaberBot(this);
		especies.add(nueva);
		
		Herbivoro planta = new Herbivoro(this);
		especies.add(planta);
		
		return null;
	}


	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}



}
