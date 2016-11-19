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
		ciclo=0;
		energia=200;
		this.velocidad=20;
		Thread nt = new Thread(this);
		nt.start();
		int targetX = (int) (Math.random()*500);
		int targetY = (int) (Math.random()*500);
	}

	//se crea un hila que solo corre cuando la vida del personaje es mayor a 0 de lo contratio se detiene
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
		// metodo pintar del personaje, dandole la imagen y sus posiciones en el X y Y
		app.image(personaje, x, y);
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
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
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

}
