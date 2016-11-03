package programaciondmi.dca.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import programaciondmi.dca.core.exceptions.EcosistemaException;


/**
 * @author Damanzano
 * 
 * Esta es la clase base de los ecosistemas contempla los metodos necesarios para la construccion de cada uno.
 * 
 */

public abstract class EcosistemaAbstracto extends Observable implements Runnable{
	
	protected List<EspecieAbstracta> especies;
	protected List<PlantaAbstracta> plantas;
	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		especies = Collections.synchronizedList(poblarEspecies());
		plantas = Collections.synchronizedList(poblarPlantas());
		Thread t =  new Thread(this);
		t.start();
	}
	
	/**
	 * Metodo plantilla llamado cada 5 segundos por el ecosistema para la creacion de nuevos individuos,
	 * depende de la sobreescritura de los metodos generarIndividuo():List y generarPlantas():List
	 * @throws EcosistemaException se lanza si los metodos generarIndividuos() o generarPlantas() no estan implementados correctamente
	 * 
	 */
	
	private final void repoblar() throws EcosistemaException{
		List<EspecieAbstracta> espTemp = generarIndividuos();
		List<PlantaAbstracta> plaTemp = generarPlantas();
		if(espTemp == null || plaTemp ==  null){
			throw new EcosistemaException("el nuevo individuo es nulo, verifique los metodos generarIndividuos() y generarPlantas()");
		}
		especies.addAll(espTemp);
		plantas.addAll(plaTemp);		
	}
	
	/**
	 * el run de la clase est� encargado de llamar al metodo repoblar cada 5 segundos para ir llamando nuevos elementos. 
	 * 
	 */
	
	@Override
	public void run() {
		while(true){			
			try {
				Thread.sleep(5000);
				repoblar();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			} catch (EcosistemaException e) {			
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>Este metodo se encargar�a de poblar los ecosistemas con la poblaci�n inicial de especies.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return {@link LinkedList}
	 */
	protected abstract LinkedList<EspecieAbstracta> poblarEspecies();
	
	/**
	 * <p>Este metodo se encargar�a de poblar los ecosistemas con la poblaci�n inicial de plantas.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return {@link LinkedList}
	 */
	protected abstract LinkedList<PlantaAbstracta> poblarPlantas();
	
	/**
	 * Actualiza la lista de especie para agregar nuevos.
	 * @return lista de los individuos de {@link EspecieAbstracta} o jerarquia a generar
	 */
	protected abstract List<EspecieAbstracta> generarIndividuos();
	
	/**
	 * Actualiza la lista de plantas para agregar nuevas.
	 * @return lista de los individuos de {@link PlantaAbstracta} o jerarquia a generar 
	 */	
	protected abstract List<PlantaAbstracta> generarPlantas();
	
	
	
	/**
	 * Metodo encargado de la visualizacion de los elementos del ecosistema, debe ser sobreescrito por el ecosistema concreto 
	 */
	public abstract void dibujar();
	
	
	/**
	 * Metodo para agregar nuevas especies al ecosistema, notifica a los observadores.  
	 * 
	 * @param e {@link EspecieAbstracta} o culquiera de la jerarquia 
	 */
	public void agregarEspecie(EspecieAbstracta e){
		especies.add(e);
		setChanged();
		notifyObservers(e);
		clearChanged();
	}
	
	/**
	 * Metodo para agregar nuevas plantas al ecosistema, notifica a los observadores.  
	 * 
	 * @param p {@link PlantaAbstracta} o culquiera de la jerarquia 
	 */
	
	public void agregarPlanta(PlantaAbstracta p){
		plantas.add(p);
		setChanged();
		notifyObservers(p);
		clearChanged();
	}
	
	/**
	 * Metodo para obtener las especies que tiene un ecosistema 
	 * 
	 * @return retorna un {@link LinkedList} de {@link EspecieAbstracta} que contiene los elementos que han sido agregados.  
	 */
	
	public List<EspecieAbstracta> getEspecies() {
		return this.especies;
	}
	
	/**
	 * Metodo para obtener las plantas que tiene un ecosistema  
	 * 
	 * @return retorna un {@link LinkedList} de {@link PlantaAbstracta} que contiene los elementos que han sido agregados.  
	 */
	
	public List<PlantaAbstracta> getPlantas() {		
		return plantas;
	}
	
	/*private void comenzar(){
		ListIterator<EspecieAbstracta> iteradorE = especies.listIterator();
		while(iteradorE.hasNext()){
			Thread nt = new Thread(iteradorE.next());
			nt.start();
		}
		
		ListIterator<PlantaAbstracta> iteradorP = plantas.listIterator();
		while(iteradorP.hasNext()){
			Thread nt = new Thread(iteradorP.next());
			nt.start();
		}
	}*/
	
	
}
