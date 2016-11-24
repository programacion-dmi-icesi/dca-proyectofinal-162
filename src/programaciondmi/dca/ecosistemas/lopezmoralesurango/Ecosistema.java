package programaciondmi.dca.ecosistemas.lopezmoralesurango;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;
public class Ecosistema extends EcosistemaAbstracto{
	
	public Ecosistema() {
		super();
		PApplet app= Mundo.ObtenerInstancia().getApp();
	}

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
				PlantaAbstracta actualp = iteradorPlantas.next();
				actualp.dibujar();
				
			}
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		// TODO Auto-generated method stub
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		EspecieHervi nueva = new EspecieHervi(this);
		especies.add(nueva);
		
		nueva = new EspecieHervi(this);
		especies.add(nueva);
		
		EspecieCarni nuevacarne = new EspecieCarni(this);
		especies.add(nuevacarne);
		
		EspecieOmni nuevaomni = new EspecieOmni(this);
		especies.add(nuevaomni);
		
		EspecieCani nuevacan = new EspecieCani(this);
		especies.add(nuevacan);
		
		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		PApplet app= Mundo.ObtenerInstancia().getApp();
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		
		for (int i = 0; i < 35; i++) {
			plantas.add(new PlantaBuena((int) app.random(-1000,1000), (int) app.random(-500,2000)));
			plantas.add(new PlantaMala((int) app.random(-1000,1000), (int) app.random(-500,2000)));

		}
		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
		EspecieHervi nueva = new EspecieHervi(this);
		especies.add(nueva);
		
		EspecieCarni nuevacarne = new EspecieCarni(this);
		especies.add(nuevacarne);
		
		EspecieOmni nuevaomni = new EspecieOmni(this);
		especies.add(nuevaomni);
		
		EspecieCani nuevacan = new EspecieCani(this);
		especies.add(nuevacan);
		
		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		PlantaBuena plantbuen = new PlantaBuena(50,90);
		plantas.add(plantbuen);
		
		PlantaMala plantmala = new PlantaMala(80,60);
		plantas.add(plantmala);
		// TODO Auto-generated method stub
		return null;
	}
}
