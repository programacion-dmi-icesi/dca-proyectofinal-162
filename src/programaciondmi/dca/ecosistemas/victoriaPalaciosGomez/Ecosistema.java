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
	private PApplet app;
	private ArrayList<PlantaBuena> buenas;

	public Ecosistema() {
		super();
		app = Mundo.ObtenerInstancia().getApp();
		Mundo ref = Mundo.ObtenerInstancia();
		Logo boton = new Logo("../data/boton.svg", this);
		System.out.println("elbot:" + boton);
		ref.agregarBoton(boton);
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

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		// Pavortuga
		Pavortuga pavo = new Pavortuga(this);
		especies.add(pavo);
		// Coconita
		Coconita coco = new Coconita(this);
		especies.add(coco);
		// Begonia
		Begonia bego = new Begonia(this);
		especies.add(bego);
		//Shafox
		Shafox shafox = new Shafox(this);
		especies.add(shafox);
		Jacinto jaci = new Jacinto(this);
		especies.add(jaci);
		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		PlantaBuena pB = new PlantaBuena(app, app.random(app.width), app.random(app.height));
		plantas.add(pB);
		PlantaMala pM = new PlantaMala(app, app.random(app.width), app.random(app.height));
		plantas.add(pM);
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
		Shafox shafox = new Shafox(this);
		especies.add(shafox);
		Jacinto jaci = new Jacinto(this);
		especies.add(jaci);
		return especies;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		if (app.mouseX < app.width && app.mouseX > 0 && app.mouseY < app.height && app.mouseY > 0 && app.mousePressed) {
			if (app.mouseButton == app.LEFT) {
				PlantaBuena pB = new PlantaBuena(app, app.mouseX, app.mouseY);
				plantas.add(pB);
				System.out.println("pB");
			} else if (app.mouseButton == app.RIGHT) {
				PlantaMala pM = new PlantaMala(app, app.mouseX, app.mouseY);
				plantas.add(pM);
				System.out.println("pM");
			}
		}
		return plantas;
	}
}
