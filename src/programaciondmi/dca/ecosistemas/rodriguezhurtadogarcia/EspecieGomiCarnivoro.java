package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieGomiCarnivoro extends EspecieAbstracta implements ICarnivoro {
	private int vida;
	private float fuerza;
	private int velocidad, vista, moverX, moverY, mover = 0, direccion = 1;
	private PImage[] carnivoroFrente = new PImage[4];
	private PImage[] carnivoroAtras = new PImage[4];
	private PImage[] carnivoroIzquierda = new PImage[4];
	private PImage[] carnivoroDerecha = new PImage[4];
	private PApplet app;
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public EspecieGomiCarnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;

		int targetX = random.nextInt();
		int targetY = random.nextInt();

		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		carnivoroFrente[0] = app.loadImage("../data/carnivoro/carnivoroFrente/1.png");
		carnivoroFrente[1] = app.loadImage("../data/carnivoro/carnivoroFrente/2.png");
		carnivoroFrente[2] = app.loadImage("../data/carnivoro/carnivoroFrente/3.png");
		carnivoroFrente[3] = app.loadImage("../data/carnivoro/carnivoroFrente/4.png");

		// ATRAS
		carnivoroAtras[0] = app.loadImage("../data/carnivoro/carnivoroAtras/1.png");
		carnivoroAtras[1] = app.loadImage("../data/carnivoro/carnivoroAtras/2.png");
		carnivoroAtras[2] = app.loadImage("../data/carnivoro/carnivoroAtras/3.png");
		carnivoroAtras[3] = app.loadImage("../data/carnivoro/carnivoroAtras/4.png");

		// IZQUIERDA
		carnivoroIzquierda[0] = app.loadImage("../data/carnivoro/carnivoroIzquierda/1.png");
		carnivoroIzquierda[1] = app.loadImage("../data/carnivoro/carnivoroIzquierda/2.png");
		carnivoroIzquierda[2] = app.loadImage("../data/carnivoro/carnivoroIzquierda/3.png");
		carnivoroIzquierda[3] = app.loadImage("../data/carnivoro/carnivoroIzquierda/4.png");

		// DERECHA
		carnivoroDerecha[0] = app.loadImage("../data/carnivoro/carnivoroDerecha/1.png");
		carnivoroDerecha[1] = app.loadImage("../data/carnivoro/carnivoroDerecha/2.png");
		carnivoroDerecha[2] = app.loadImage("../data/carnivoro/carnivoroDerecha/3.png");
		carnivoroDerecha[3] = app.loadImage("../data/carnivoro/carnivoroDerecha/4.png");

		// System.out.println(this);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 5;
			}
		}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();

		if (direccion == 3) {
			app.image(carnivoroIzquierda[vista], x + moverX, y + moverY);
		} else if (direccion == 4) {
			app.image(carnivoroDerecha[vista], x + moverX, y + moverY);
		} else if (direccion == 1) {
			app.image(carnivoroFrente[vista], x + moverX, y + moverY);
		} else if (direccion == 2) {
			app.image(carnivoroAtras[vista], x + moverX, y + moverY);
		}
	}

	@Override
	public void mover() {
		if (energia > 0) {

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

	}

	@Override
	public void run() {
		while (vida > 0) {
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

	private void buscarParejaCercana() {

		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		// System.out.println("Buscando pareja entre " + todas.size() + "
		// especies del mundo");
		ListIterator<EspecieAbstracta> iterador = todas.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			EspecieAbstracta e = iterador.next();
			if ((e instanceof IApareable) && !e.equals(this)) {
				float dist = PApplet.dist(x, y, e.getX(), e.getY());
				// System.out.println("Encontró apareable a " + dist);
				if (dist < energia) {
					// System.out.println("Encontró una pareja cercana");
					encontro = true;
					parejaCercana = e;
					// Cambiar la dirección
				}
			}
		}
		// asegurarse de que la referencia sea null;
		if (!encontro) {
			parejaCercana = null;
			// System.out.println("No encontró una pareja cercana");
		}

	}

	private void intentarAparear() {

	}

	private void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana=" + parejaCercana
				+ ", dir=" + dir + ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo
		return false;
	}

}