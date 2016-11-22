package programaciondmi.dca.core.interfaces;

import programaciondmi.dca.core.EspecieAbstracta;

/**
 *  Interfaz que define los elementos canibales
 */

public interface ICanibal {
	
	/**
	 * Metodo para comer una {@link EspecieAbstracta}, debe verificar que sea de la misma especie del llamador.
	 * @param victima la especie que será comida (misma especie)
	 */
	public void comer(EspecieAbstracta victima);

}
