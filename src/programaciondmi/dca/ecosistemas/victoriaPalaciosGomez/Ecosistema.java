package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

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

public class Ecosistema extends EcosistemaAbstracto {

	public Ecosistema() {
		super();
		Mundo ref = Mundo.ObtenerInstancia();
		LogoEjemplo boton= new LogoEjemplo("global_data/bot1.svg", this);
		System.out.println("elbot:"+boton);
		ref.agregarBoton(boton);
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
		EspecieBlanca nueva = new EspecieBlanca(this);
		especies.add(nueva);		
		nueva = new EspecieBlanca(this);
		especies.add(nueva);		
		Coconita coco = new Coconita(this);
		especies.add(coco);		
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
		EspecieBlanca nueva = new EspecieBlanca(this);
		especies.add(nueva);		
		Coconita coco = new Coconita(this);
		especies.add(coco);		
		return especies;
	}
	

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		return plantas;
	}
	
}
