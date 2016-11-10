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
	private int velocidad, vista;
	private PImage[] canibalFrente = new PImage[4];
	private PApplet app;

	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */

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
		cambiarDireccion(new PVector(targetX, targetY));

		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();
		canibalFrente[0] = app.loadImage("../data/canibalFrente.png");
		canibalFrente[1] = app.loadImage("../data/canibalFrenteE1.png");
		canibalFrente[2] = app.loadImage("../data/canibalFrenteE2.png");
		canibalFrente[3] = app.loadImage("../data/canibalFrenteE3.png");

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

		app.image(canibalFrente[vista], x, y);
	}

	@Override
	public void mover() {
		if (energia > 0) {
			// System.out.println("[id=" + id + ", energia=" + energia + "]");
			// Si tengo buena energía para aparearme
			if (energia > LIMITE_APAREO) {
				buscarParejaCercana();
				// Si hay una pareja cercana la prioridad es reproducirse
				if (parejaCercana != null) {
					intentarAparear();
				}
			} else {
				buscarComida();
				if (ciclo % 30 == 0) {
					// Definir una direccion aleatoria cada 3 segundos
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					cambiarDireccion(new PVector(targetX, targetY));
					System.out.println("CAMBIO DIRECCION!");
				}
			}

			// moverse en la dirección asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}

		if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.dir.x *= -1;
		}
		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.dir.y *= -1;
		}

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

	/**
	 * <p>
	 * Este método busca a una especie apareable dentro del rango permitido por
	 * la fuerza actual.
	 * </p>
	 */
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
					cambiarDireccion(new PVector(parejaCercana.getX(), parejaCercana.getY()));
				}
			}
		}
		// asegurarse de que la referencia sea null;
		if (!encontro) {
			parejaCercana = null;
			// System.out.println("No encontró una pareja cercana");
		}

	}

	/**
	 * <p>
	 * Este método valida que una pareja cercana este a la distancia adecuada y
	 * genera un descendiente en caso de cumplirse la condición
	 * </p>
	 */
	private void intentarAparear() {

	}

	/**
	 * <p>
	 * Este método valida recorre el arreglo de especies del mundo e intenta
	 * comerse a cada una de las especies
	 * </p>
	 */
	private void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);

		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
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