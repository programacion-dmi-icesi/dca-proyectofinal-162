package programaciondmi.dca.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import programaciondmi.dca.core.exceptions.EcosistemaException;


/**
 * @author David Andrés Manzano Herrera <damh24@gmail.com>
 * 
 */

public abstract class EcosistemaAbstracto extends Observable implements Runnable{
	
	protected LinkedList<EspecieAbstracta> especies;
	protected LinkedList<PlantaAbstracta> plantas;
	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		especies = poblarEspecies();
		plantas = poblarPlantas();
		Thread t =  new Thread(this);
		t.start();
	}
	
	/**
	 * Metodo plantilla llamado cada 5 segundos por el ecosistema para la creacion de nuevos individuos,
	 * depende de la sobreescritura de los metodos {@link generarIndividuo():List} y {@link generarPlantas():List}
	 * @throws EcosistemaException 
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
	 * Actualiza la lista de especiepara agregar nuevos.
	 */
	protected abstract List<EspecieAbstracta> generarIndividuos();
	
	/**
	 * Actualiza la lista de plantas para agregar nuevas. 
	 */	
	protected abstract List<PlantaAbstracta> generarPlantas();
		
	public abstract void dibujar();
	
	public void agregarEspecie(EspecieAbstracta e){
		especies.add(e);
		setChanged();
		notifyObservers(e);
		clearChanged();
	}
	
	public void agregarPlanta(PlantaAbstracta p){
		plantas.add(p);
		setChanged();
		notifyObservers(p);
		clearChanged();
	}
	
	public LinkedList<EspecieAbstracta> getEspecies() {
		return this.especies;
	}
	
	public LinkedList<PlantaAbstracta> getPlantas() {
		// TODO Auto-generated method stub
		return null;
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
