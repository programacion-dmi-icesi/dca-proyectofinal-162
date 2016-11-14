package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PVector;
import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Hervivoro extends EspecieAbstracta implements IHerbivoro {
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private float fuerza,energia;
	private PImage personaje;
private PApplet app;
	public Hervivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app=Mundo.ObtenerInstancia().getApp();
	}

	@Override
	//SE CREA UN HILO CON LA CONDICION DE QUE MUEVA AL PERSONAJE SOLO SI SU VIDA ES MAYOR A 0
	public void run() {
		// TODO Auto-generated method stub
		while(vida>0){
			mover();
			try{
				
			}catch(Exception e){
				
			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

}
