/**
 * 
 */
package programaciondmi.dca.ejemplos;

import java.util.LinkedList;
import java.util.List;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

/**
 * @author Monk
 *
 */
public class EcosistemaMonk extends EcosistemaAbstracto {

	
	@Override
	public void dibujar() {
//		PApplet app = Mundo.ObtenerInstancia().getApp();
//		app.fill(255,0,0);
//		app.ellipse((int)(Math.random()*app.width), (int)(Math.random()*app.height), 50, 50);
	}


	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}




}
