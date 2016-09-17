/**
 * 
 */
package programaciondmi.dca.ejemplos;

import java.util.LinkedList;
import java.util.Observable;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

/**
 * @author Monk
 *
 */
public class EcosistemaMonk extends EcosistemaAbstracto {

	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(""+this.getClass()+" : update : ");
	}

	
	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(255,0,0);
		app.ellipse((int)(Math.random()*app.width), (int)(Math.random()*app.height), 50, 50);
	}


	@Override
	protected LinkedList<EspecieAbstracta> poblar() {
		// TODO Auto-generated method stub
		return null;
	}




}
