package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieGomiHerbivoro extends EspecieAbstracta implements IApareable, IHerbivoro {
	private int vida;
	private float fuerza;
	private int velocidad, vista, moverX, moverY, mover = 1, direccion = 2;
	private PImage[] herbivoroFrente = new PImage[4];
	private PImage[] herbivoroAtras = new PImage[4];
	private PImage[] herbivoroIzquierda = new PImage[4];
	private PImage[] herbivoroDerecha = new PImage[4];
	private PApplet app;
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public EspecieGomiHerbivoro(EcosistemaAbstracto ecosistema, int vista) {
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

		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();
		
		//FRENTE
		herbivoroFrente[0] = app.loadImage("../data/herbivoro/herbivoroFrente/1.png");
		herbivoroFrente[1] = app.loadImage("../data/herbivoro/herbivoroFrente/2.png");
		herbivoroFrente[2]= app.loadImage("../data/herbivoro/herbivoroFrente/3.png");
		herbivoroFrente[3] = app.loadImage("../data/herbivoro/herbivoroFrente/4.png");
		
		
		//ATRAS
		herbivoroAtras[0] = app.loadImage("../data/herbivoro/herbivoroAtras/1.png");
		herbivoroAtras[1] = app.loadImage("../data/herbivoro/herbivoroAtras/2.png");
		herbivoroAtras[2] = app.loadImage("../data/herbivoro/herbivoroAtras/3.png");
		herbivoroAtras[3] = app.loadImage("../data/herbivoro/herbivoroAtras/4.png");
		
		//DERECHA
		herbivoroDerecha[0] = app.loadImage("../data/herbivoro/herbivoroDerecha/1.png");
		herbivoroDerecha[1] = app.loadImage("../data/herbivoro/herbivoroDerecha/2.png");
		herbivoroDerecha[2] = app.loadImage("../data/herbivoro/herbivoroDerecha/3.png");
		herbivoroDerecha[3] = app.loadImage("../data/herbivoro/herbivoroDerecha/4.png");

		//IZQUIERDA
		herbivoroIzquierda[0] = app.loadImage("../data/herbivoro/herbivoroIzquierda/1.png");
		herbivoroIzquierda[1] = app.loadImage("../data/herbivoro/herbivoroIzquierda/2.png");
		herbivoroIzquierda[2] = app.loadImage("../data/herbivoro/herbivoroIzquierda/3.png");
		herbivoroIzquierda[3] = app.loadImage("../data/herbivoro/herbivoroIzquierda/4.png");
		

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();

		if (direccion == 3) {
			app.image(herbivoroIzquierda[vista], x + moverX, y + moverY);
		} else if (direccion == 4) {
			app.image(herbivoroDerecha[vista], x + moverX, y + moverY);
		} else if (direccion == 1) {
			app.image(herbivoroFrente[vista], x + moverX, y + moverY);
		} else if (direccion == 2) {
			app.image(herbivoroAtras[vista], x + moverX, y + moverY);
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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub

		if (!victima.getClass().toString().equals(this.getClass().toString())) {

			System.out.println("oie siiiiiiiiiiiiiii");
		}

	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

}