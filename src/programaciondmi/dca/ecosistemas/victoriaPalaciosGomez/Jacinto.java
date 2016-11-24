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
	private int vida, maxSpeed;
	private PVector location, acceleration, vel;

	public Jacinto(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		imagenes();
		vida = 30;
		acceleration = new PVector (0,0);
		vel = new PVector (0,0);
		maxSpeed = 3;
		location = new PVector (x,y);
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.image(jacinto[index], x, y);
	}

	@Override
	public void mover() {
		PVector newZona = new PVector (app.random(app.width), app.random(app.height));
		PVector acceleration = PVector.sub(newZona, location);
		acceleration.setMag(0.2f);
		vel.add(acceleration);
		vel.limit(maxSpeed);
		location.add(vel);
		if (app.frameCount % 6 == 0) {
			index++;
			if (index > 2) {
				index = 0;
			}
		}
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			if (app.frameCount % 6 == 0) {
				index++;
				if (index > 2) {
					index = 0;
				}
			}
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void imagenes () {
		//pavortuga SANO
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
