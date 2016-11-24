package programaciondmi.dca.core;

import java.util.UUID;


/**
 * 
 * Esta es la clase base de las especies del ecosistema contempla los metodos necesarios para la construccion de cada una * 
 * 
 * @author Jamoncada
 *
 */

public abstract class EspecieAbstracta implements Runnable{
	
	protected UUID id;
	protected EcosistemaAbstracto ecosistema;
	protected int x;
	protected int y;
	public static final int NORMAL = 0;
	public static final int ENVENENADO = 1;
	public static final int ENFERMO = 2;
	public static final int EXTASIS = 3;
	public static final int MUERTO = 4;
	protected int estado;
	 
	public EspecieAbstracta(EcosistemaAbstracto ecosistema) {
		this.id = UUID.randomUUID();
		this.ecosistema = ecosistema;
		this.estado = NORMAL;
	}
	
	public int getEstado() {
		return estado;
	}
	
	/**
	 * 
	 * Metodo encargado de determinar el estado de una especie concreta, los estados posibles son:
	 * 
	 * NORMAL, ENFERMO, ENVENENADO, EXTASIS y MUERTO 
	 * 
	 * @param estado es un entero que debe corresponder a las constantes estaticas disponibles en la clase.
	 * @throws Exception Se lanza cuando no se esta usando un numero de entero valido. 
	 */
	
	public void setEstado(int estado) throws Exception {
		if(estado == NORMAL || estado == ENFERMO || estado == ENVENENADO || estado == EXTASIS|| estado == MUERTO ){
			this.estado = estado;
		}else{
			System.err.println("Estado no valido... use: NORMAL, ENVENENADO, ENFERMO, MUERTO, EXTASIS");		
			throw new Exception("Ese estado no es valido, use uno basado en las constantes estaticas de la clase");
		}		
	}
	
	public abstract void dibujar();
	public abstract void mover();
	
	/**
	 * Método encargadado de recibir el daño ocasionado por el ataque de otra especie abstracta
	 * el método deberá validar su posicion respecto a la otra especie para saber si se lastima o no.
	 * 
	 * @param lastimador quien hace daño a la especie actual
	 * @return true si pudo hacer daño y false si no puedo dañar a la especie actual 
	 */
	public abstract boolean recibirDano(EspecieAbstracta lastimador);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	
}