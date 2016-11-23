package programaciondmi.dca.ecosistemas.saavedralincecordoba;

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
		// LogoEjemplo boton= new LogoEjemplo("global_data/bot1.svg", this);
		// ref.agregarBoton(boton);
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

		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlantas = plantas.iterator();
			while (iteradorPlantas.hasNext()) {
				PlantaAbstracta actual = iteradorPlantas.next();
				actual.dibujar();
			}
		}
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		// Apareable apareable = new Apareable(this);
		// especies.add(apareable);

		// SE GENERA EL CANIBAL
		// Canibal canibal = new Canibal(this);
		// especies.add(canibal);
		// SE GENERA EL CARNIVORO
		// Carnivoro carnivoro = new Carnivoro(this);
		// especies.add(carnivoro);
		Hervivoro hervivoro = new Hervivoro(this);
		especies.add(hervivoro);

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		// SE AGREGA LA BUENA
		PlantaBuena pb = new PlantaBuena(50, 90);
		plantas.add(pb);
		System.out.println(plantas);
		// SE AGREGA LA MALA
		PlantaMala pm = new PlantaMala(100, 100);
		plantas.add(pm);

		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		List<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();
		// SE GENERA EL APAREABLE
		//// especies.add(apareable);
		// agregarEspecie(apareable);

		// SE GENERA EL CANIBAL
		Canibal canibal = new Canibal(this);
		especies.add(canibal);
		agregarEspecie(canibal);

		// SE GENERA EL CARNIVORO
		Carnivoro carnivoro = new Carnivoro(this);
		especies.add(carnivoro);
		agregarEspecie(carnivoro);
		// SE GENERA EL HERVIVORO
		Hervivoro hervivoro = new Hervivoro(this);
		especies.add(hervivoro);
		agregarEspecie(hervivoro);

		return especies;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		// SE AGREGA LA PLANTA BUENA
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		PlantaBuena pb = new PlantaBuena(50, 90);
		plantas.add(pb);
		System.out.println(plantas);
		agregarPlanta(pb);
		// SE AGREGA LA MALA
		PlantaMala pm = new PlantaMala(100, 100);
		plantas.add(pm);
		agregarPlanta(pm);

		return plantas;

	}

}
