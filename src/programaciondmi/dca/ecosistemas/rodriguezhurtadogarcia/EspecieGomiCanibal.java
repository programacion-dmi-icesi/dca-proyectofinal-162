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

public class EspecieGomiCanibal extends EspecieAbstracta implements ICanibal {
	private int vida;
	private float fuerza;
	private int velocidad, vista, moverX, moverY, mover = 3, direccion = 4;
	private PImage[] canibalFrente = new PImage[4];
	private PImage[] canibalAtras = new PImage[4];
	private PImage[] canibalIzquierda = new PImage[4];
	private PImage[] canibalDerecha = new PImage[4];
	private PApplet app;
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public EspecieGomiCanibal(EcosistemaGomiCabra ecosistema, int vista) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		this.vista = vista;

		int targetX = random.nextInt();
		int targetY = random.nextInt();

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		canibalFrente[0] = app.loadImage("../data/canibal/canibalFrente/1.png");
		canibalFrente[1] = app.loadImage("../data/canibal/canibalFrente/2.png");
		canibalFrente[2] = app.loadImage("../data/canibal/canibalFrente/3.png");
		canibalFrente[3] = app.loadImage("../data/canibal/canibalFrente/4.png");

		// ATRAS
		canibalAtras[0] = app.loadImage("../data/canibal/canibalAtras/1.png");
		canibalAtras[1] = app.loadImage("../data/canibal/canibalAtras/2.png");
		canibalAtras[2] = app.loadImage("../data/canibal/canibalAtras/3.png");
		canibalAtras[3] = app.loadImage("../data/canibal/canibalAtras/4.png");

		// IZQUIERDA
		canibalIzquierda[0] = app.loadImage("../data/canibal/canibalIzquierda/1.png");
		canibalIzquierda[1] = app.loadImage("../data/canibal/canibalIzquierda/2.png");
		canibalIzquierda[2] = app.loadImage("../data/canibal/canibalIzquierda/3.png");
		canibalIzquierda[3] = app.loadImage("../data/canibal/canibalIzquierda/4.png");

		// DERECHA
		canibalDerecha[0] = app.loadImage("../data/canibal/canibalDerecha/1.png");
		canibalDerecha[1] = app.loadImage("../data/canibal/canibalDerecha/2.png");
		canibalDerecha[2] = app.loadImage("../data/canibal/canibalDerecha/3.png");
		canibalDerecha[3] = app.loadImage("../data/canibal/canibalDerecha/4.png");

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
			app.image(canibalIzquierda[vista], x + moverX, y + moverY);
		} else if (direccion == 4) {
			app.image(canibalDerecha[vista], x + moverX, y + moverY);
		} else if (direccion == 1) {
			app.image(canibalFrente[vista], x + moverX, y + moverY);
		} else if (direccion == 2) {
			app.image(canibalAtras[vista], x + moverX, y + moverY);
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

				if (vista == 4) {
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