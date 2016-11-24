package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class Canibal extends EspecieAbstracta implements ICanibal {

	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private int allUCanEat;// variable usada para controlar un tiempo para poder
							// comer o esperar
	private int tiempoInmune;// variable para controlar el tiempo de inmunidad
								// inicial en el programa (se evita que se
								// eliminen todos los objetos al iniciar el
								// programa)
	private boolean mostrar = true;
	private boolean destruir = false;// booleano para controlar si muere o no el
										// personaje
	private PImage personaje, muerto;
	PApplet app = Mundo.ObtenerInstancia().getApp();

	public Canibal(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 5;
		allUCanEat = 100;
		tiempoInmune = 100;
		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));
		estado = NORMAL;

		// inicio el hilo
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
				ciclo++;
				// se decrementa la variable para volver a poderse lastimar
				tiempoInmune--;

				// si destruir es igual a true, se envia la vida a cero para
				// cerrar el hilo
				if (destruir == true) {
					// mostrar = false;
					vida = 0;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

				// si la vida es menor o igual a cero, se envia el estado del
				// objeto a MUERTO para cerrar el hilo
				if (vida <= 0) {
					// mostrar = false;
					estado = MUERTO;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	/*
	 * Si al clase de la victima es de mi misma clase sumo vida en 5
	 */

	@Override
	public void comer(EspecieAbstracta victima) {
		if (victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				try {
					vida += 5;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Metodo encargado de pintar varias imagenes del personaje dependiendo de
	 * su estado
	 */

	@Override
	public void dibujar() {
		personaje = app.loadImage("../img/come.png");
		muerto = app.loadImage("../img/come_dead.png");
		if (mostrar) {
			if (estado == NORMAL || estado == EXTASIS) {
				if (vida <= 30) {
					// app.fill(255, 0, 0);
					// app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y);
				}

				if (vida > 30 && vida <= 50) {
					// app.fill(255, 0, 0);
					// app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y, 80, 80);
				}

				if (vida > 50) {
					// app.fill(255, 0, 0);
					// app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y, 100, 100);
				}
			}

			if (estado == MUERTO) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(muerto, x, y);
			}
		}
	}

	/*
	 * Metodo encargado de mover el personaje por la pantalla
	 */

	@Override
	public void mover() {
		if (ciclo % 70 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
		}

		x += dir.x;
		y += dir.y;

		// decremento de esta variable para poder volver a comer al llegar a
		// cero o estar por debajo
		allUCanEat--;

		if (allUCanEat <= 0) {
			buscarComida();
		}

		// si el estado del personaje es "MUERTO" se envia una variable a true
		// para eliminarlo
		if (estado == MUERTO) {
			destruir = true;
		}

	}

	/*
	 * MEtodo encargado de recibir daño si la distancia es menor a 30 y el
	 * tiempoInmune es menor o igual a cero si mi estado es diferente de muerto
	 * resto 5 en la variable vida
	 */

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30 && tiempoInmune <= 0) {
			if (estado != MUERTO) {
				vida -= 5;
				try {
					lastimador.setEstado(EXTASIS);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}

		return false;
	}

	// metodo encargado de recorrer las especies en busca de comida
	public void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	// metodo encargado del cambio de direccion del personaje al moverse
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}
}
