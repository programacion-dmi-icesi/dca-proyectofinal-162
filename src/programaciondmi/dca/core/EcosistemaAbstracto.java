package programaciondmi.dca.core;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import programaciondmi.dca.ejecucion.Mundo;


/**
 * @author David Andrés Manzano Herrera <damh24@gmail.com>
 * 
 */
public abstract class EcosistemaAbstracto implements Observer{
	
	// TODO - Definir la estructura que contendrá a las especies, 2 opciones: mundo -> ecosistemas -> especies // mundo -> especies	
	
	private LinkedList<EspecieAbstracta> especies; //parte de la opcion 1
	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		especies = poblar();
	}
	
	
	/**
	 * <p>Este metodo se encargará de poblar los ecosistemas al inicio.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return 
	 */
	protected abstract LinkedList<EspecieAbstracta> poblar();
	
	public abstract void dibujar();
	
}
