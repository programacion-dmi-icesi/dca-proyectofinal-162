package programaciondmi.dca.ecosistemas.lopezmoralesurango;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
public class Ecosistema extends EcosistemaAbstracto{
	
	public Ecosistema() {
		super();
	}

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
		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();
		
		PlantaBuena plantbuen = new PlantaBuena();
		plantas.add(plantbuen);
		
		PlantaMala plantmala = new PlantaMala();
		plantas.add(plantmala);
		// TODO LLenar las lista de plantas iniciales
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
		PlantaBuena plantbuen = new PlantaBuena();
		plantas.add(plantbuen);
		
		PlantaMala plantmala = new PlantaMala();
		plantas.add(plantmala);
		// TODO Auto-generated method stub
		return null;
	}
}
