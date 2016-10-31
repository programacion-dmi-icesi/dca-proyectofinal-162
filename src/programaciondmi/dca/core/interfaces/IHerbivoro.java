/**
 * 
 */
package programaciondmi.dca.core.interfaces;


import programaciondmi.dca.core.PlantaAbstracta;

/**
 *  Interfaz que define los elementos herbivoros
 */

public interface IHerbivoro{
	
	/**
	 * Metodo para comer plantas de la jerarquia de {@link PlantaAbstracta}
	 * @param victima la planta que sera comida debe ser {@link PlantaAbstracta}
	 */
	public void comerPlanta(PlantaAbstracta victima);
}
