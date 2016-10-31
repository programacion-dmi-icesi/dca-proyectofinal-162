package programaciondmi.dca.core;

/**
 * 
 * Esta es la clase base de las plantas del ecosistema contempla los metodos necesarios para la construccion de cada una
 * 
 * @author Jamoncada
 *
 */

public abstract class PlantaAbstracta implements Runnable{
	
	protected int x;
	protected int y;	
	
	public PlantaAbstracta(){}
	
	public PlantaAbstracta(int x, int y){
		this.x = x ;
		this.y = y ;
	}
		
	public abstract void dibujar();

}
