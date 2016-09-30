package programaciondmi.dca.ejecucion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.reflections.Reflections;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;

public class Mundo implements Observer {
	private static Mundo ref;
	private PApplet app;
	private Set<Class<? extends EcosistemaAbstracto>> clasesEcosistemas;
	private Set<EcosistemaAbstracto> ecosistemas;
	private List<EspecieAbstracta> especies;
	private List<PlantaAbstracta> plantas;
	
	private Mundo(PApplet app){
		this.app = app;
		this.ecosistemas  = new HashSet<EcosistemaAbstracto>();
		this.especies = new LinkedList<EspecieAbstracta>();
		this.plantas = new LinkedList<PlantaAbstracta>();
	}
	
	/**
	 * Este método se encarga de buscar en todos los paquetes de la aplicación
	 * todas las clases que hereden de EcosistemaAbstracto, generar una instancia
	 * de las mismas y almacenar dicha instancia en una colección de tipo conjunto
	 */
	protected void cargarEcosistemas(){
		Reflections reflections = new Reflections("programaciondmi.dca");    
		this.clasesEcosistemas = reflections.getSubTypesOf(EcosistemaAbstracto.class);
		
		for (Class<? extends EcosistemaAbstracto> clase : clasesEcosistemas) {
			try {
				Constructor<?> cons = clase.getConstructor();
				EcosistemaAbstracto ecosistema = (EcosistemaAbstracto) cons.newInstance();
				ecosistemas.add(ecosistema);
				ecosistema.addObserver(this);
				cargarEspecies(ecosistema);
				cargarPlantas(ecosistema);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Este método se encarga de agregar a la colección global de especies
	 * la población inicial de especies de un ecosistema dado por parámetro
	 * @param ecosistema
	 */
	private void cargarEspecies(EcosistemaAbstracto ecosistema){
		if(ecosistema.getEspecies()!=null){
			especies.addAll(ecosistema.getEspecies());
		}
	}
	
	/**
	 * Este método se encarga de agregar a la colección global de plantas
	 * la población inicial de plantas de un ecosistema dado por parámetro
	 * @param ecosistema
	 */
	private void cargarPlantas(EcosistemaAbstracto ecosistema){
		if(ecosistema.getPlantas()!=null){
			plantas.addAll(ecosistema.getPlantas());
		}
	}
	
	/**
	 * <p>Este mï¿½todo se encargarï¿½ de construir el ï¿½nico mundo que puede existir en la aplicaciï¿½n.</p>
	 * <p>Si un Objeto de tipo Mundo ha sido creado previamente, 
	 * se retorna una referencia a dicho mundo en lugar de construir uno nuevo 
	 * </p>
	 * <p>Este mï¿½todo es protegido para que sï¿½lo pueda ser usado desde la clase ejecutable</p>
	 * 
	 * @param app Un Objeto de tipo PApplet sobre el cual el mundo se dibujara.
	 * @return El objeto Mundo sobre el que se crearan nuevos ecosistemas
	 */
	protected static synchronized Mundo construirInstancia(PApplet app){
		if(ref == null){
			ref = new Mundo(app);
		}		
		return ref;
	}
	
	/**
	 * <p>Este mï¿½todo retorna una referencia al objeto Mundo sobre el que se crearan nuevos ecosistemas.</p>
	 * <p>No debe utilizarse sin antes hacer un llamado al mï¿½todo construirInstancia(PApplet app)</p>
	 * @return Una referencia al objeto Mundo sobre el que se crearan nuevos ecosistemas.
	 */
	public static synchronized Mundo ObtenerInstancia(){
		return ref;
	}
	
	/**
	 * Este mï¿½todo se encarga de dibujar lo que ocurre en el mundo.
	 */
	protected void dibujar() {
		//System.out.println("Pintando el mundo");
		/**
		 * TODO Reemplzar esta lÃ­nea por el background seleccionado por los estudiantes
		 */
		app.background(150);
		
		for (EcosistemaAbstracto ecosistema : ecosistemas) {
			ecosistema.dibujar();
		}
	}

	public synchronized PApplet getApp() {
		return app;
	}
	
	public synchronized List<EspecieAbstracta> getEspecies(){
		return especies;
	}
	
	public synchronized List<PlantaAbstracta> getPlantas(){
		return plantas;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(" El ecosistema "+arg0+ " envió una notificaciíon");
		if(arg1 instanceof EspecieAbstracta){
			System.out.println("Se argrego una nueva especie "+arg1);
			EspecieAbstracta nuevaEspecie = (EspecieAbstracta) arg1;
			especies.add(nuevaEspecie);
		}
		
		if(arg1 instanceof PlantaAbstracta){
			System.out.println("Se argrego una nueva especie "+arg1);
			PlantaAbstracta nuevaPlanta = (PlantaAbstracta) arg1;
			plantas.add(nuevaPlanta);
		}
	}
	
	
	
}
