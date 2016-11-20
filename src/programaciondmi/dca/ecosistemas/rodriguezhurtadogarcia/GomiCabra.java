package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public abstract class GomiCabra extends EspecieAbstracta {
	protected int t;
	protected int vida;
	protected int maxVida;
	protected boolean puedeComer = true;
	protected float fuerza;
	protected int velocidad, vista, mover, direccion = 2;
	protected PImage[] frente = new PImage[4];
	protected PImage[] atras = new PImage[4];
	protected PImage[] izquierda = new PImage[4];
	protected PImage[] derecha = new PImage[4];
	protected PApplet app;
	protected float energia;
	protected EspecieAbstracta parejaCercana;
	protected PVector dir;
	protected int ciclo;
	protected boolean muerto;
	// Constantes
	protected final int LIMITE_APAREO = 100;
	protected int maxSalud = 5;
	protected int salud = maxSalud;
	protected Random random;

	public GomiCabra(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width - 2000, app.width + 2000);
		this.y = (int) app.random(-app.height - 2000, app.height + 2000);
	}

	@Override
	public void dibujar() {

		PApplet app = Mundo.ObtenerInstancia().getApp();
		float dolor = app.map(vida, 0, maxVida, 0, 255);
		app.tint(dolor, 255, dolor);
		// app.rect( x + moverX, y + moverY,100,100);
		if (direccion == 3) {
			app.image(izquierda[vista], x, y);
		} else if (direccion == 4) {
			app.image(derecha[vista], x, y);
		} else if (direccion == 1) {
			app.image(frente[vista], x, y);
		} else if (direccion == 2) {
			app.image(atras[vista], x, y);
		}

		app.colorMode(app.RGB);
		app.noTint();
	}

	@Override
	public void mover() {
		// causa el movimiento

		if (app.frameCount % (int) (app.random(60, 100)) == 0) {
			mover = (int) app.random(0, 4);
		}

		if (energia > 0) {
			switch (mover) {
			case 0:
				x++;
				direccion = 4;
				break;
			case 1:
				x--;
				direccion = 3;
				break;
			case 2:
				y++;
				direccion = 1;
				break;
			case 3:
				y--;
				direccion = 2;
			}// termina switch mover
			if (x >= 500) {
				mover = 1;
			}
			if (x <= 0) {
				x = 1;
				mover = 2;
			}
			if (y >= 500) {
				mover = 3;
			}
			if (y <= -1) {
				y = 0;
				mover = 0;
			}
		}

		if (vida < 0)
			muerto = true;
	}

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana=" + parejaCercana
				+ ", dir=" + dir + ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		return false;
	}

	public boolean isMuerto() {
		return muerto;
	}

	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}

}
