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
private ArrayList<PlantaBuena>buenas;
private static PApplet app;
	public Ecosistema() {
		super();
		
		Mundo ref = Mundo.ObtenerInstancia();
		Logo boton= new Logo("../data/boton.svg", this);
		System.out.println("elbot:"+boton);
		ref.agregarBoton(boton);
		app = Mundo.ObtenerInstancia().getApp();
	}


	@Override
	public void dibujar() {
		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorEspecies = plantas.iterator();
			while(iteradorEspecies.hasNext()){
				PlantaAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
		}
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
		PlantaBuena plantaTemp = new PlantaBuena(app, 200, 200);
		plantas.add(plantaTemp);
		plantaTemp = new PlantaBuena(app, 400, 400);
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
		if( app.mouseButton == app.RIGHT){
			PlantaMala plantaTemp = new PlantaMala(app, app.mouseX, app.mouseY);
			plantas.add(plantaTemp);
		}else{
			PlantaBuena plantaTemp = new PlantaBuena(app, app.mouseX, app.mouseY);
			plantas.add(plantaTemp);
		}
	
 		return plantas;
		
	}
	
	
	public void presionar(){
		System.out.println("Clic");
	}
	
}
