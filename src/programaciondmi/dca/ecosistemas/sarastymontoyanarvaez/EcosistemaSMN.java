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
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaSMN extends EcosistemaAbstracto {

	public EcosistemaSMN() {
		super();
		
		Mundo ref = Mundo.ObtenerInstancia();
		//LogoEjemplo boton= new LogoEjemplo("global_data/bot1.svg", this);
		//ref.agregarBoton(boton);
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
		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
			while(iteradorPlantas.hasNext()){
				PlantaAbstracta actual = iteradorPlantas.next();
				actual.dibujar();		
				}
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		
		Herviboro herb = new Herviboro(this);
		especies.add(herb);
		
		Carnivoro carn = new Carnivoro(this);
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
		PlantaMala plantaVerde = new PlantaMala(50,90);
		plantas.add(plantaVerde);

		PlantaBuena plantaRoja = new PlantaBuena(80,90);
		plantas.add(plantaRoja);
		return plantas;
	}


	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		
		Herviboro herb = new Herviboro(this);
		especies.add(herb);
		
		Carnivoro carn = new Carnivoro(this);
		especies.add(carn);
		
		EspecieTres rep = new EspecieTres(this);
		especies.add(rep);
		
		EspecieCuatro come = new EspecieCuatro(this);
		especies.add(come);
		
		return null;
	}


//	@Override
//	protected List<PlantaAbstracta> generarPlantas() {
//		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
//		PlantaMala plantaVerde = new PlantaMala(this);
//		plantas.add(plantaVerde);
//		PlantaBuena plantaRoja = new PlantaBuena(this);
//		plantas.add(plantaRoja);
//		return null;
//	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		PlantaMala plantaVerde = new PlantaMala(this);
		plantas.add(plantaVerde);
		PlantaBuena plantaRoja = new PlantaBuena(this);
		plantas.add(plantaRoja);
		
		agregarPlanta(plantaVerde);
		agregarPlanta(plantaRoja);
		return plantas;
	}
	
	

}
