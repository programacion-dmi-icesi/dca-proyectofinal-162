package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaSMN extends EcosistemaAbstracto {

	public EcosistemaSMN() {
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
		
		EspecieUno herb = new EspecieUno(this);
		especies.add(herb);
		
		EspecieDos carn = new EspecieDos(this);
		especies.add(carn);
		
		EspecieTres rep = new EspecieTres(this);
		especies.add(rep);
		
		EspecieCuatro come = new EspecieCuatro(this);
		especies.add(come);
		
		
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
		// TODO Auto-generated method stub
		
		EspecieUno azul = new EspecieUno(this);
		especies.add(azul);
		
		EspecieDos carn = new EspecieDos(this);
		especies.add(carn);
		
		EspecieTres rep = new EspecieTres(this);
		especies.add(rep);
		
		EspecieCuatro come = new EspecieCuatro(this);
		especies.add(come);
		
		return null;
	}


	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return null;
	}



}
