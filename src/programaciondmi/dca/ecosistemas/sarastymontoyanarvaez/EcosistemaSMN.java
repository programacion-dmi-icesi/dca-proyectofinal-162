package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaSMN extends EcosistemaAbstracto {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	ArrayList<Animaciones> anim;

	public EcosistemaSMN() {
		super();

		/* Instrucciones para agregar el boton del logo
		 * */
		
		Mundo ref = Mundo.ObtenerInstancia();
		LogoSMN bot = new LogoSMN("img/Logo-01.svg", this);
		ref.agregarBoton(bot);

		anim = new ArrayList<Animaciones>();
	}

	@Override
	public void dibujar() {
		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
			
			/*Metodo para remover los objetos que ya estan inactivos
			 * Agrego un objeto de tipo animacion 
			 */

			for (int i = 0; i < especies.size(); i++) {
				if (especies.get(i).getEstado() == 4) {
					// System.out.println(especies.size());
					// System.out.println(especies.get(i).getClass().toString());
					if (especies.get(i).getClass().toString().equals("class programaciondmi.dca.ecosistemas.sarastymontoyanarvaez.Herviboro")) {
						System.out.println("imprime");
						anim.add(new Animaciones(app, especies.get(i).getX(), especies.get(i).getY(), 1));
					}

					if (especies.get(i).getClass().toString()
							.equals("class programaciondmi.dca.ecosistemas.sarastymontoyanarvaez.Carnivoro")) {
						System.out.println("imprime");
						anim.add(new Animaciones(app, especies.get(i).getX(), especies.get(i).getY(), 2));
					}

					
					especies.remove(i);
				}
			}

		}
		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
			while (iteradorPlantas.hasNext()) {
				PlantaAbstracta actual = iteradorPlantas.next();
				actual.dibujar();
			}

			for (int i = 0; i < plantas.size(); i++) {
				// System.out.println(plantas.size());
				/*
				 * PlantaBuena plantaVerde = new PlantaBuena(this);
				 * if(plantaVerde.getCantidad()<=2){
				 * plantas.remove(plantaVerde); }
				 */
			}

		}

		/*metodos de pintar y mover de los objetos de ArrayList de la animacion
		 * 
		 */
		
		for (int i = 0; i < anim.size(); i++) {
			System.out.println(anim.size());
			anim.get(i).pintar();
			anim.get(i).mover();

			if (anim.get(i).getDesvanecer() <= 0) {
				anim.remove(i);
			}
		}

	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		for (int i = 0; i < 10; i++) {
			Herviboro herb = new Herviboro(this);
			especies.add(herb);

			Carnivoro carn = new Carnivoro(this);
			especies.add(carn);

			
		}

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		PlantaMala plantaVerde = new PlantaMala(0, 90);
		plantas.add(plantaVerde);

		PlantaBuena plantaRoja = new PlantaBuena(120, 90);
		plantas.add(plantaRoja);

		return plantas;
	}

	
	/*Este metodo es el que sirve para generar nuevos individuos
	 * (non-Javadoc)
	 * @see programaciondmi.dca.core.EcosistemaAbstracto#generarIndividuos()
	 */
	
	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		Herviboro herb = new Herviboro(this);
		Herviboro herb2 = new Herviboro(this);
		especies.add(herb);
		especies.add(herb2);

		Carnivoro carn = new Carnivoro(this);
		especies.add(carn);


		agregarEspecie(herb);
		agregarEspecie(herb2);
		agregarEspecie(carn);
		

		return especies;
	}
	
	/*Metodo para agregar plantas
	 * Click Derecho apara agregar plantas malas
	 * Click izquierdo para agregar plantas buenas
	 * 
	 * (non-Javadoc)
	 * @see programaciondmi.dca.core.EcosistemaAbstracto#generarPlantas()
	 */

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		if (app.mouseButton == PConstants.LEFT) {
			for (int i = 0; i < 3; i++) {
				PlantaBuena plantaVerde = new PlantaBuena(this);
				// System.out.println(app.mouseX + " " + app.mouseY);
				plantaVerde = new PlantaBuena((int) app.random(app.mouseX - 800, app.mouseX + 400),
						(int) app.random(50, app.height));
				plantas.add(plantaVerde);
				agregarPlanta(plantaVerde);
				// System.out.println("Planta Buena");
			}
		}

		if (app.mouseButton == PConstants.RIGHT) {
			for (int i = 0; i < 3; i++) {
				PlantaMala plantaRoja = new PlantaMala(this);
				plantaRoja = new PlantaMala((int) app.random(app.mouseX - 400, app.mouseX + 400),
						(int) app.random(50, app.height));
				plantas.add(plantaRoja);
				agregarPlanta(plantaRoja);
				// System.out.println("Planta Mala");
			}
		}

		return plantas;
	}

}
