package programaciondmi.dca.core;

public abstract class EspecieAbstracta implements Runnable{ // TODO verificar si se quiere interfaz o clase...
	
	protected int x;
	protected int y;
	public final int NORMAL = 0;
	public final int ENVENENADO = 1;
	public final int ENFERMO = 2;
	public final int EXTASIS = 3;
	public final int MUERTO = 4;
	private int estado;
	 
	public EspecieAbstracta() {
		estado = NORMAL;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setEstado(int estado) throws Exception {
		if(estado == NORMAL || estado == ENFERMO || estado == ENVENENADO || estado == EXTASIS|| estado == MUERTO ){
			this.estado = estado;
		}else{
			System.err.println("Estado no valido... use: NORMAL, ENVENENADO, ENFERMO, MUERTO, EXTASIS");		
			throw new Exception("Ese estado no es valido"); // TODO crear excepcion propia
		}		
	}
	
	public abstract void dibujar();
	public abstract void mover();
	

}
