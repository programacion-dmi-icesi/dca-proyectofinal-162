package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieTres extends EspecieAbstracta implements IApareable{
	
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	PImage personaje;

	public EspecieTres(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 3;

		int targetX = (int) (Math.random()*500);
		int targetY = (int) (Math.random()*500);
		cambiarDireccion(new PVector(targetX, targetY));
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		personaje=app.loadImage("../img/rep.png");
		app.image(personaje,x, y);
		
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
			//System.out.println("CAMBIO DIRECCION!");
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
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

}
