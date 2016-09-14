package ejecucion;

import java.util.Observable;
import processing.core.PApplet;
import sun.security.jca.GetInstance;

public class Mundo extends Observable {
	public static Mundo ref;
	private PApplet app;
	
	private Mundo(PApplet app){
		this.app = app;
	}
	
	/**
	 * <p>Este método se encargará de construir el único mundo que puede existir en la aplicación.</p>
	 * <p>Si un Objeto de tipo Mundo ha sido creado previamente, 
	 * se retorna una referencia a dicho mundo en lugar de construir uno nuevo 
	 * </p>
	 * <p>Este método es protegido para que sólo pueda ser usado desde la clase ejecutable</p>
	 * 
	 * @param app Un Objeto de tipo PApplet sobre el cual el mundo se dibujara.
	 * @return El objeto Mundo sobre el que se crearan nuevos ecosistemas
	 */
	protected static Mundo construirInstancia(PApplet app){
		if(ref == null){
			ref = new Mundo(app);
		}
		
		return ref;
	}
	
	/**
	 * <p>Este método retorna una referencia al objeto Mundo sobre el que se crearan nuevos ecosistemas.</p>
	 * <p>No debe utilizarse sin antes hacer un llamado al método construirInstancia(PApplet app)</p>
	 * @return Una referencia al objeto Mundo sobre el que se crearan nuevos ecosistemas.
	 */
	public static Mundo ObtenerInstancia(){
		return ref;
	}
	
	/**
	 * Este método se encarga de dibujar lo que ocurre en el mundo.
	 */
	protected void dibujar() {
		app.textMode(PApplet.CENTER);
		app.text("Mi mundo vive", app.width/2, app.height/2);
	}
}
