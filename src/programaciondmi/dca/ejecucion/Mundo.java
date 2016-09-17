package programaciondmi.dca.ejecucion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import org.reflections.Reflections;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;

public class Mundo extends Observable {
	private static Mundo ref;
	private PApplet app;
	private Set<Class<? extends EcosistemaAbstracto>> clasesEcosistemas;
	private Set<EcosistemaAbstracto> ecosistemas;
	
	private Mundo(PApplet app){
		this.app = app;
		this.ecosistemas  = new HashSet<EcosistemaAbstracto>();
		Reflections reflections = new Reflections("programaciondmi.dca");    
		clasesEcosistemas = reflections.getSubTypesOf(EcosistemaAbstracto.class);
		
		for (Class<? extends EcosistemaAbstracto> clase : clasesEcosistemas) {
			try {
				Constructor<?> cons = clase.getConstructor();
				EcosistemaAbstracto ecosistema = (EcosistemaAbstracto) cons.newInstance();
				ecosistemas.add(ecosistema);
				this.addObserver(ecosistema);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				e.printStackTrace();
			}
		}
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
		app.rectMode(PApplet.CENTER);
		app.text("Mi mundo vive", app.width/2, app.height/2);
		
		for (EcosistemaAbstracto ecosistema : ecosistemas) {
			ecosistema.dibujar();
		}
	}

	public PApplet getApp() {
		return app;
	}
	
}
