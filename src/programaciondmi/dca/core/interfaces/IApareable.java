package programaciondmi.dca.core.interfaces;

import programaciondmi.dca.core.EspecieAbstracta;

/**
 *  Interfaz que define los elementos apareables
 */

public interface IApareable {
	
	/**
	 * Metodo para aparear especie
	 * @param apareable el apareable con el que se va reproducir la especie
	 * @return retorna el nuevo individuo de {@link EspecieAbstracta}
	 */
	
	public EspecieAbstracta aparear(IApareable apareable);

}
