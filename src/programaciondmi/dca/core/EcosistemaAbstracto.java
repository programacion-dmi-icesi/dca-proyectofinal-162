package programaciondmi.dca.core;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import programaciondmi.dca.ejecucion.Mundo;


/**
 * @author David Andr�s Manzano Herrera <damh24@gmail.com>
 * 
 */
public abstract class EcosistemaAbstracto implements Observer{
	
	// TODO - Definir la estructura que contendr� a las especies, 2 opciones: mundo -> ecosistemas -> especies // mundo -> especies	
	
	protected LinkedList<EspecieAbstracta> especies; //parte de la opcion 1
	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		especies = poblar();
	}	
	
	/**
	 * <p>Este metodo se encargar� de poblar los ecosistemas al inicio.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return 
	 */
	protected abstract LinkedList<EspecieAbstracta> poblar();
	
	public LinkedList<EspecieAbstracta> getEspecies() {
		return especies;
	}
	
	public void agregarIndividuo(EspecieAbstracta e){
		especies.add(e);
	}
	
	public abstract void dibujar();
	
	
	
}
