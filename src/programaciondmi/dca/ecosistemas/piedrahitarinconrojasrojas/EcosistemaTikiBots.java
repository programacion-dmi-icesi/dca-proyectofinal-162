package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.SaberBot;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.Birdbot;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaTikiBots extends EcosistemaAbstracto {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	int tipoPlanta = (int) app.random(1,2);
	
	
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
		
		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
			while(iteradorPlantas.hasNext()){
				//System.out.println("aja.. dibuja");
				PlantaAbstracta actual = iteradorPlantas.next();
				actual.dibujar();
			}
		}
		
		if (app.mousePressed == true){
			if (app.mouseButton==app.LEFT){
				// clic derecho agrega plantas buenas
				tipoPlanta = 1;
				poblarPlantas();
				
			}
			
			if(app.mouseButton==app.RIGHT){
				// clic derecho agrega plantas malas
				tipoPlanta = 2;
				poblarPlantas();
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
		
		Magusbot mago = new Magusbot(this);
		especies.add(mago);
		
		mago = new Magusbot(this);
		especies.add(mago);
		
		Cannibalbot cani = new Cannibalbot(this);
		especies.add(cani);
		
		cani = new Cannibalbot(this);
		especies.add(cani);
		
		Birdbot ave = new Birdbot(this);
		especies.add(ave);
		
		return especies;
	}


	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
	LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
	
		if(tipoPlanta == 1){
		//	System.out.println("planta buena");
			PlantaBuena buena = new PlantaBuena(this);
			plantas.add(buena);
			tipoPlanta = 0;
		}
		
		if(tipoPlanta == 2){
			//System.out.println("planta mala");
			PlantaMala mala = new PlantaMala(this);
			plantas.add(mala);
			tipoPlanta = 0;
		}
		
		
	
		return plantas;
	}




	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		// TODO Auto-generated method stub
		
		SaberBot nueva = new SaberBot(this);
		especies.add(nueva);	

		Magusbot mago = new Magusbot(this);
		especies.add(mago);
		
		Cannibalbot cani = new Cannibalbot(this);
		especies.add(cani);
		
		Birdbot ave = new Birdbot(this);
		especies.add(ave);
		
		return especies;
	}


	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		//System.out.println("generar plantas");
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		
		
			

		PlantaBuena buena = new PlantaBuena(this);
		plantas.add(buena);
		PlantaMala mala = new PlantaMala(this);
		plantas.add(mala);
		
		return plantas;
	}

	


}
