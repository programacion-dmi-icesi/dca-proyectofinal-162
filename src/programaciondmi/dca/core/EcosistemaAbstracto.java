package programaciondmi.dca.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;


import programaciondmi.dca.ejecucion.Mundo;


/**
 * @author David Andrï¿½s Manzano Herrera <damh24@gmail.com>
 * 
 */
public abstract class EcosistemaAbstracto extends Observable{
	
	protected LinkedList<EspecieAbstracta> especies;
	protected LinkedList<PlantaAbstracta> plantas;
	
	public EcosistemaAbstracto() {
		// TODO Auto-generated constructor stub
		especies = poblarEspecies();
		plantas = poblarPlantas();
	}	
	
	/**
	 * <p>Este metodo se encargaría de poblar los ecosistemas con la población inicial de especies.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return {@link LinkedList}
	 */
	protected abstract LinkedList<EspecieAbstracta> poblarEspecies();
	
	/**
	 * <p>Este metodo se encargaría de poblar los ecosistemas con la población inicial de plantas.</p>
	 * <p>Es llamado por el constructor por defecto, debe ser sobre escrito al crear la clase ecosistema concreta</p>
	 * @return {@link LinkedList}
	 */
	protected abstract LinkedList<PlantaAbstracta> poblarPlantas();
	
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
