package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

public class EcosistemaProphetics extends EcosistemaAbstracto {

	public EcosistemaProphetics() {
		super();
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		LinkedList<EspecieAbstracta> especies = new LinkedList<EspecieAbstracta>();

		BuhoApareable nueva = new BuhoApareable(this);
		especies.add(nueva);

		nueva = new BuhoApareable(this);
		especies.add(nueva);

		return especies;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {

		LinkedList<PlantaAbstracta> plantas = new LinkedList<PlantaAbstracta>();

		Venenosa nueva = new Venenosa();
		plantas.add(nueva);

		return plantas;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {

		BuhoApareable nueva = new BuhoApareable(this);
		especies.add(nueva);

		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {

		Venenosa nueva = new Venenosa();
		plantas.add(nueva);
		return null;
	}

	@Override
	public void dibujar() {

		synchronized (plantas) {
			Iterator<PlantaAbstracta> iteradorPlanta = plantas.iterator();
			while (iteradorPlanta.hasNext() && !plantas.isEmpty()) {
				PlantaAbstracta actual = iteradorPlanta.next();
				actual.dibujar();
				if (actual instanceof Venenosa) {
					if (((Venenosa) actual).getRecursos() <= 0) {
//						plantas.remove(actual);
					}
				}
			}
		}

		synchronized (especies) {
			Iterator<EspecieAbstracta> iteradorEspecies = especies.iterator();
			while (iteradorEspecies.hasNext()) {
				EspecieAbstracta actual = iteradorEspecies.next();
				actual.dibujar();
			}
		}
	}

}
