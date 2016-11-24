/**
 * 
 */
package programaciondmi.dca.core.interfaces;

import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

/**
 *  Interfaz que define los elementos carnivoros
 */

public interface ICarnivoro {
	/**
	 * Metodo para comer un {@link EspecieAbstracta}
	 * @param victima la {@link EspecieAbstracta} que será comida
	 */
	public void comer(EspecieAbstracta victima);

	void comerPlanta(PlantaAbstracta victima);
}
