package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import processing.core.PApplet;
import processing.core.PConstants;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.SaberBot;
import programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas.Birdbot;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaTikiBots extends EcosistemaAbstracto {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	int tipoPlanta = 0;
	int cont = 0;
	private boolean colocar, puedeColocar;
	
	
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
		
		/*if (app.mousePressed == true){
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
		}*/
		
		if(app.frameCount%30==0){
			puedeColocar=true;
		}

		int camX = Mundo.ObtenerInstancia().getCamX();
		int camY = Mundo.ObtenerInstancia().getCamY();

		if (puedeColocar) {

			if (app.mousePressed == true) {

				if (app.mouseButton == app.LEFT) {
					
					System.out.println("coloca planta buena");
					plantas.add(
							new PlantaMala(app.mouseX - ((app.width) - camX), (int) (app.mouseY - ((app.height) - camY))));
					puedeColocar=false;
					
				} else if (app.mouseButton == app.RIGHT) {
					
					System.out.println("coloca planta mala");
					plantas.add(new PlantaMala(app.mouseX - ((app.width) - camX),
							app.mouseY - ((app.height) - camY)));
					puedeColocar=false;
					
				}
			}
		}
		
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		
		/*SaberBot nueva = new SaberBot(this);
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
		especies.add(cani);*/
		
		if(cont == 0){
			Birdbot ave = new Birdbot(this);
			especies.add(ave);
			cont++;
		}
		
		return especies;
	}


	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
	LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
	
		if(tipoPlanta == 1){
			//System.out.println("planta buena");
			//GoodPlanta buena = new GoodPlanta(this);
			//plantas.add(buena);
			tipoPlanta = 0;
		}
		
		if(tipoPlanta == 2){
			//System.out.println("planta mala");
			//PlantaMala mala = new PlantaMala(this);
			//plantas.add(mala);
			tipoPlanta = 0;
		}
	
		return plantas;
	}




	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		
		// TODO Auto-generated method stub
		
		/*SaberBot nueva = new SaberBot(this);
		especies.add(nueva);*/	
		
		/*if(cont == 0){
		Magusbot mago = new Magusbot(this);
		especies.add(mago);
		}*/
		
		/*Cannibalbot cani = new Cannibalbot(this);
		especies.add(cani);*/
		
		//if(cont == 0){
			Birdbot ave = new Birdbot(this);
			especies.add(ave);
			agregarEspecie(ave);
			//cont++;
		//}
		
		return especies;
	}


	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		//System.out.println("generar plantas");
	
		
		/*PlantaMala mala = new PlantaMala(this);
		plantas.add(mala);
		agregarPlanta(mala);*/
		
		return plantas;
	}
	
	/*public void setColocar(boolean colocar) {
		this.colocar = colocar;
	}*/

}
