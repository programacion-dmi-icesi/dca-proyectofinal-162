package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;
import processing.core.PImage;

public class Herviboro extends EspecieAbstracta implements IHerbivoro {

	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private PImage personaje, envenenado, muerto;
	private boolean destruir = false;// booleano para controlar si muere o no el
										// personaje

	private int tiempoInmunidad;// variable para controlar el tiempo de
								// inmunidad
								// inicial en el programa (se evita que se
								// eliminen todos los objetos al iniciar el
								// programa)

	PApplet app = Mundo.ObtenerInstancia().getApp();
	private boolean mostrar = true;

	public Herviboro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);

		this.vida = 20;
		this.velocidad = 3;
		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		tiempoInmunidad = 100;
		cambiarDireccion(new PVector(targetX, targetY));

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
				tiempoInmunidad--;

				/*
				 * Instruccion que se ejecuta una vez el objeto esta en estado
				 * MUERTO
				 * 
				 */

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
					// mostrar=false;
					estado = MUERTO;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// Metodo encargado de comer las plantas
	// Si la clase de la victima es diferente a la mia
	// Si la planta es mala paso a estado ENVENENADO y mi velocidad se reduce a
	// 2
	// si la plana es buena paso a estado EXTASIS y aumenta mi velocidad a 5

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				try {
					if (victima.getClass() == PlantaMala.class) {
						estado = ENVENENADO;
						velocidad = 2;

					}
					if (victima.getClass() == PlantaBuena.class) {
						estado = EXTASIS;
						velocidad = 5;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
		personaje = app.loadImage("../img/herv.png");
		envenenado = app.loadImage("../img/herv_poison.png");
		muerto = app.loadImage("../img/herv_dead.png");

		if (mostrar) {
			if (estado == NORMAL) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y);
			}

			if (estado == EXTASIS) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y, 60, 60);
			}

			if (estado == ENVENENADO) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(envenenado, x, y);
			}
			if (estado == MUERTO) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				// app.tint(255, 255, 255, desvanecer);
				app.image(muerto, x, y);
			}
		}
	}

	/*
	 * Metodo encargado de mover el personaje por la pantalla
	 */

	@Override
	public void mover() {
		if (estado != MUERTO) {
			if (ciclo % 70 == 0) {
				// Definir una direccion aleatoria cada 3 segundos
				int targetX = (int) (Math.random() * 500);
				int targetY = (int) (Math.random() * 500);
				cambiarDireccion(new PVector(targetX, targetY));
			}

			x += dir.x;
			y += dir.y;

			// metodo para buscar plantas para comer
			buscarPlantas();
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
	 * resto 5 en la variable vida Si mi estado es NORMAL cambio el estado del
	 * lastimador a EXTASIS Si mi estado es ENVENENADO envio el estado del
	 * lastionmador a ENVENENADO
	 */

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30 && tiempoInmunidad <= 0) {
			if (lastimador.getEstado() == NORMAL) {
				vida -= 5;
				try {
					if (estado == NORMAL) {
						lastimador.setEstado(EXTASIS);
					}

					if (estado == ENVENENADO) {
						lastimador.setEstado(ENVENENADO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;
			}
		}
		return false;
	}

	// metodo encargado del cambio de direccion del personaje al moverse
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	// metodo encargado de recorrer las plantas en busca de comida
	private void buscarPlantas() {
		List<PlantaAbstracta> todas = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comerPlanta(todas.get(i));
		}
	}
}