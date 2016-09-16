package programaciondmi.dca.core;

import java.util.Observable;
import java.util.Observer;

import programaciondmi.dca.ejecucion.Mundo;


/**
 * @author David Andrés Manzano Herrera <damh24@gmail.com>
 * 
 */
public abstract class EcosistemaAbstracto implements Observer{

	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		
	}
	
	public abstract void dibujar();
	
}
