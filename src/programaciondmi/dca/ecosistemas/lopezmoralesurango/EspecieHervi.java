package programaciondmi.dca.ecosistemas.lopezmoralesurango;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieHervi extends EspecieAbstracta implements IHerbivoro{
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int cicloC;
	private int vida;
	private int velocidad;
	private PVector dir;
	private PImage Herviboro;
	
	public EspecieHervi(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida=10;
		this.velocidad = 7;
		PApplet app = Mundo.ObtenerInstancia().getApp();
		Herviboro=app.loadImage("hervi.png");
		
		int targetX = (int) (Math.random()*200);
		int targetY = (int) (Math.random()*200);
		cambiarDireccion(new PVector(targetX, targetY));
		
		Thread hb = new Thread(this);
		hb.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep((int) app.random(33,55));
				cicloC++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(Herviboro, x, y);
	}

	@Override
	public void mover() {
		if (cicloC % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 200);
			//System.out.println("CAMBIO DIRECCION!");
		}
		
		if (cicloC % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetY = (int) (Math.random() * 200);
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
	
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

}
