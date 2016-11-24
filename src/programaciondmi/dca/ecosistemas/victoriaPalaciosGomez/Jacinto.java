package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Jacinto extends EspecieAbstracta {
	private PApplet app;
	private PImage[] jacinto = new PImage [6];
	private int index;
	private int vida, maxSpeed,ciclo,velocidad;
	private PVector location, acceleration, vel;
	private PVector dir;
	public static final int NORMAL = 0;
	public static final int ENVENENADO = 1;
	public static final int ENFERMO = 2;
	public static final int EXTASIS = 3;
	public static final int MUERTO = 4;
	protected int estado;

	public Jacinto(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.estado = NORMAL;

		this.app = Mundo.ObtenerInstancia().getApp();
		imagenes();
		vida = 30;
		velocidad = 2;
		acceleration = new PVector (0,0);
		vel = new PVector (0,0);
		maxSpeed = 3;
		location = new PVector (x,y);
		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));
	}
	
	public int getEstado() {
		return estado;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.image(jacinto[index], x, y);
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
			 System.out.println("CAMBIO DIRECCION!");
		}

		x += dir.x;
		y += dir.y;
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}
	@Override
	public void run() {
		while (vida > 0) {
			mover();
			if (app.frameCount % 6 == 0) {
				index++;
				if (index >= 2) {
					index = 0;
				}
			}
			try {
				
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void imagenes () {
		//jacinto SANO
		
		jacinto[0] = app.loadImage("../data/pngs/jacinto-walk-1.png");
		jacinto[1] = app.loadImage("../data/pngs/jacinto-walk-2.png");
		jacinto[2] = app.loadImage("../data/pngs/jacinto-walk-3.png");
		//pavortuga enfermo
		jacinto[3] = app.loadImage("../data/pngs/jacinto-sick-1.png");
		jacinto[4] = app.loadImage("../data/pngs/jacinto-sick-2.png");
		jacinto[5] = app.loadImage("../data/pngs/jacinto-sick-3.png");
		
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo.
		return false;
	}

}
