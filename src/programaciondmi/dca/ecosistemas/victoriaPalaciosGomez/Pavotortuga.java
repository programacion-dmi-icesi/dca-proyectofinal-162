package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Pavotortuga extends EspecieAbstracta implements IHerbivoro {
	private PApplet app;
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private int index = 0;
	
	
	public Pavotortuga(EcosistemaAbstracto ecosistema){
		super(ecosistema);
		this.app = Mundo.ObtenerInstancia().getApp();
		this.vida = 20;
		this.velocidad = 5;
		int targetX = (int) (Math.random()*500);
		int targetY = (int) (Math.random()*500);
		cambiarDireccion(new PVector(targetX, targetY));
		
		Thread nt = new Thread(this);
		nt.start();
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (vida > 0) {
			mover();
			if (app.frameCount % 6 == 0) {
				index++;
				if (index > 6) {
					index = 0;
				}
			}
			System.out.println();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.ellipse(x, y, 20, 20);
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
		}
		
		x+=dir.x;
		y+=dir.y;
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida/2)){
			vida -= 5;
			try {
				lastimador.setEstado(EXTASIS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	
	private void cambiarDireccion(PVector target) {
		
		
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}
}
