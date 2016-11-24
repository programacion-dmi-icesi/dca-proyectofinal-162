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
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.LogoEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class Ecosistema extends EcosistemaAbstracto {
private ArrayList<PlantaBuena>buenas;
	public Ecosistema() {
		super();
		
		Mundo ref = Mundo.ObtenerInstancia();
		LogoEjemplo boton= new LogoEjemplo("../data/boton.svg", this);
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
		//Pavortuga
		Pavortuga pavo = new Pavortuga(this);
		especies.add(pavo);	
		//Coconita
		Coconita coco = new Coconita(this);
		especies.add(coco);	
		//Begonia
		Begonia bego = new Begonia(this);
		especies.add(bego);
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
		Pavortuga pavo = new Pavortuga(this);
		especies.add(pavo);		
		Coconita coco = new Coconita(this);
		especies.add(coco);	
		Begonia bego = new Begonia(this);
		especies.add(bego);
		return especies;
	}
	

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
//		if( mousePressed==RIGHT){
//		for (int i = 0; i < buenas.size(); i++) {
//			buenas.add(new PlantaBuena(ecosistema));
////    		if(dist(buenas.get(i).getX(), buenas.get(i).getY(), , y2)){
////    			
////    		}
//		}
//    		}
		return plantas;
	}
	
	
	public void presionar(){
		
	}
	
}
