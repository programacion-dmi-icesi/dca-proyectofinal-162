package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import processing.core.*;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hijo extends EspecieAbstracta{
	private PImage[] hijo = new PImage[4];
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	
	public Hijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		hijo[0] = app.loadImage("../data/vistas/hijo_aba.png");
		hijo[0] = app.loadImage("../data/vistas/hijo_arri.png");
		hijo[0] = app.loadImage("../data/vistas/hijo_izq.png");
		hijo[0] = app.loadImage("../data/vistas/hijo_der.png");
		app.image(hijo[0], x, y);
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
		// TODO Auto-generated method stub
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

