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
		personaje= app.loadImage("personaje.png");
		vida=3;
	}

	@Override
	//SE CREA UN HILO CON LA CONDICION DE QUE MUEVA AL PERSONAJE SOLO SI SU VIDA ES MAYOR A 0
	public void run() {
		// TODO Auto-generated method stub
		while(vida>0){
			mover();
			try{
				Thread.sleep(33);
				ciclo++;
			}catch(Exception e){
				
			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.image(personaje, x, y);
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			//System.out.println("CAMBIO DIRECCION!");
		}
		
		x+=dir.x;
		y+=dir.y;

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
