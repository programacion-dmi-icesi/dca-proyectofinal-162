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
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

/*VISTAS
1=delante
2=atras
3=izquierda
4=derecha
*/

public class EspecieGomiOmnivoro extends EspecieAbstracta implements IOmnivoro {
	private int vida;
	private float fuerza;
	private int velocidad, vista, moverX, moverY, mover = 0, direccion = 1;
	private PImage omnivoroFrente;
	private PApplet app;
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;
	private PImage[] omnivoroIzquierda = new PImage[11];
	private PImage[] omnivoroDerecha = new PImage[11];

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public EspecieGomiOmnivoro(EcosistemaAbstracto ecosistema) {
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

		app = Mundo.ObtenerInstancia().getApp();

		// CARGO IMAGENES
		omnivoroFrente = app.loadImage("../data/omnivoro/omnivoroFrente.png");

		// OMNIVORO IZQUIERDO
		omnivoroIzquierda[0] = app.loadImage("../data/omnivoro/omnivoroIzquierda/1.png");
		omnivoroIzquierda[1] = app.loadImage("../data/omnivoro/omnivoroIzquierda/2.png");
		omnivoroIzquierda[2] = app.loadImage("../data/omnivoro/omnivoroIzquierda/3.png");
		omnivoroIzquierda[3] = app.loadImage("../data/omnivoro/omnivoroIzquierda/4.png");
		omnivoroIzquierda[4] = app.loadImage("../data/omnivoro/omnivoroIzquierda/5.png");
		omnivoroIzquierda[5] = app.loadImage("../data/omnivoro/omnivoroIzquierda/6.png");
		omnivoroIzquierda[6] = app.loadImage("../data/omnivoro/omnivoroIzquierda/7.png");
		omnivoroIzquierda[7] = app.loadImage("../data/omnivoro/omnivoroIzquierda/8.png");
		omnivoroIzquierda[8] = app.loadImage("../data/omnivoro/omnivoroIzquierda/9.png");
		omnivoroIzquierda[9] = app.loadImage("../data/omnivoro/omnivoroIzquierda/10.png");
		omnivoroIzquierda[10] = app.loadImage("../data/omnivoro/omnivoroIzquierda/11.png");

		// OMNIVORO IZQUIERDO
		omnivoroDerecha[0] = app.loadImage("../data/omnivoro/omnivoroDerecha/1.png");
		omnivoroDerecha[1] = app.loadImage("../data/omnivoro/omnivoroDerecha/2.png");
		omnivoroDerecha[2] = app.loadImage("../data/omnivoro/omnivoroDerecha/3.png");
		omnivoroDerecha[3] = app.loadImage("../data/omnivoro/omnivoroDerecha/4.png");
		omnivoroDerecha[4] = app.loadImage("../data/omnivoro/omnivoroDerecha/5.png");
		omnivoroDerecha[5] = app.loadImage("../data/omnivoro/omnivoroDerecha/6.png");
		omnivoroDerecha[6] = app.loadImage("../data/omnivoro/omnivoroDerecha/7.png");
		omnivoroDerecha[7] = app.loadImage("../data/omnivoro/omnivoroDerecha/8.png");
		omnivoroDerecha[8] = app.loadImage("../data/omnivoro/omnivoroDerecha/9.png");
		omnivoroDerecha[9] = app.loadImage("../data/omnivoro/omnivoroDerecha/10.png");
		omnivoroDerecha[10] = app.loadImage("../data/omnivoro/omnivoroDerecha/11.png");

		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {

		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 5;
			}
		}
	}

	@Override
	public void dibujar() {

		PApplet app = Mundo.ObtenerInstancia().getApp();

		if (direccion == 3) {
			app.image(omnivoroIzquierda[vista], x + moverX, y + moverY);
		} else if (direccion == 4) {
			app.image(omnivoroDerecha[vista], x + moverX, y + moverY);
		} else {
			app.image(omnivoroFrente, x + moverX, y + moverY);
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

				if (vista == 10) {
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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub

	}

}