package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class HijoGomiCabra extends EspecieAbstracta  implements Runnable{

	private int vista, moverX, moverY, mover = 3, direccion = 4;
	private PImage[] hijoFrente = new PImage[4];
	private PImage[] hijoAtras = new PImage[4];
	private PImage[] hijoIzquierda = new PImage[4];
	private PImage[] hijoDerecha = new PImage[4];
	private PApplet app;
	private Random random;

	public HijoGomiCabra(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		hijoFrente[0] = app.loadImage("../data/hijo/hijoFrente/1.png");
		hijoFrente[1] = app.loadImage("../data/hijo/hijoFrente/2.png");
		hijoFrente[2] = app.loadImage("../data/hijo/hijoFrente/3.png");
		hijoFrente[3] = app.loadImage("../data/hijo/hijoFrente/4.png");

		// ATRAS
		hijoAtras[0] = app.loadImage("../data/hijo/hijoAtras/1.png");
		hijoAtras[1] = app.loadImage("../data/hijo/hijoAtras/2.png");
		hijoAtras[2] = app.loadImage("../data/hijo/hijoAtras/3.png");
		hijoAtras[3] = app.loadImage("../data/hijo/hijoAtras/4.png");

		// IZQUIERDA
		hijoIzquierda[0] = app.loadImage("../data/hijo/hijoIzquierda/1.png");
		hijoIzquierda[1] = app.loadImage("../data/hijo/hijoIzquierda/2.png");
		hijoIzquierda[2] = app.loadImage("../data/hijo/hijoIzquierda/3.png");
		hijoIzquierda[3] = app.loadImage("../data/hijo/hijoIzquierda/4.png");

		// DERECHA
		hijoDerecha[0] = app.loadImage("../data/hijo/hijoDerecha/1.png");
		hijoDerecha[1] = app.loadImage("../data/hijo/hijoDerecha/2.png");
		hijoDerecha[2] = app.loadImage("../data/hijo/hijoDerecha/3.png");
		hijoDerecha[3] = app.loadImage("../data/hijo/hijoDerecha/4.png");
		
		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			mover();
			try {
				Thread.sleep(33);

				vista++;

				if (vista == 3) {
					vista = 0;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		if (direccion == 3) {
			app.image(hijoIzquierda[vista], x + moverX, y + moverY);
		} else if (direccion == 4) {
			app.image(hijoDerecha[vista], x + moverX, y + moverY);
		} else if (direccion == 1) {
			app.image(hijoFrente[vista], x + moverX, y + moverY);
		} else if (direccion == 2) {
			app.image(hijoAtras[vista], x + moverX, y + moverY);
		}

	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		switch (mover) {

		case 0:
			moverX++;
			direccion = 4;
			break;
		case 1:
			moverX--;
			direccion = 3;
			break;
		case 2:
			moverY++;
			direccion = 1;
			break;
		case 3:
			moverY--;
			direccion = 2;
		}// termina switch mover

		if (moverX >= 500) {
			mover = 1;
		}

		if (moverX <= 0) {
			moverX = 1;
			mover = 2;
		}

		if (moverY >= 500) {
			mover = 3;
		}

		if (moverY <= -1) {
			moverY = 0;
			mover = 0;
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
