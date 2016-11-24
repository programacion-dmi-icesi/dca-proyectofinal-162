package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Carnivoro extends EspecieAbstracta implements ICarnivoro {

	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private boolean mostrar = true;;
	private boolean destruir = false;// booleano para controlar si muere o no el
										// personaje
	private int tiempoInmunidad;// variable para controlar el tiempo de
								// inmunidad
								// inicial en el programa (se evita que se
								// eliminen todos los objetos al iniciar el
								// programa)
	private PImage personaje, envenenado, muerto;
	private int allUCanEat;// variable usada para controlar un tiempo para poder
							// comer o esperar

	PApplet app = Mundo.ObtenerInstancia().getApp();

	public Carnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 3;
		allUCanEat = (int) app.random(100, 200);
		tiempoInmunidad = 100;
		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));

		// inicio el hilo
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			if (estado != MUERTO) {
				mover();
			}
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
					// mostrar=false;
					vida = 0;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

				// de esta forma se detiene el hilo y se remueve el objeto
				// inactivo
				if (vida <= 0) {
					estado = MUERTO;
					// mostrar = false;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

			} catch (Exception e) {

			}
		}
	}

	// metodo para comer otras especies
	@Override
	public void comer(EspecieAbstracta victima) {
		// si la victima no es de mi clase
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {

				try {

					// si el estado de la victima es diferente de MUERTO
					if (victima.getEstado() != MUERTO) {

						/*
						 * instrucciones para comer individuos dependiendo de
						 * sus interfaces se envenena si la victima esta enferma
						 * y si no esta enferma lo envia a estado EXTASIS
						 */

						if (victima instanceof IHerbivoro) {
							if (victima.getEstado() == ENVENENADO) {
								estado = ENVENENADO;
								vida -= 10;
								velocidad = 2;
							} else {
								velocidad += 2;
								vida += 5;
								estado = EXTASIS;
							}
							victima.setEstado(MUERTO);
							allUCanEat = (int) app.random(200, 700);
						}

						if (victima instanceof ICanibal) {
							if (victima.getEstado() == ENVENENADO) {
								estado = ENVENENADO;
								vida -= 10;
								velocidad = 2;
							} else {
								velocidad += 2;
								vida += 5;
								estado = EXTASIS;
							}
							victima.setEstado(MUERTO);
							allUCanEat = (int) app.random(200, 700);
						}

						if (victima instanceof IOmnivoro) {
							if (victima.getEstado() == ENVENENADO) {
								estado = ENVENENADO;
								vida -= 10;
								velocidad = 2;
							} else {
								velocidad += 2;
								vida += 5;
								estado = EXTASIS;
							}
							victima.setEstado(MUERTO);
							allUCanEat = (int) app.random(200, 700);
						}
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
		personaje = app.loadImage("../img/carn.png");
		envenenado = app.loadImage("../img/carn_poison.png");
		muerto = app.loadImage("../img/carn_dead.png");
		if (mostrar) {
			if (estado == NORMAL) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y);
			}

			if (estado == EXTASIS) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y, 70, 70);
			}

			if (estado == ENVENENADO) {
				// app.fill(255, 0, 0);
				// app.text(vida, x + 17, y + 5);
				app.image(envenenado, x, y);
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
		if (ciclo % 100 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
		}

		x += dir.x;
		y += dir.y;

		// decremento la variable para poder volver a comer
		allUCanEat--;

		// si la variable allUCanEat es menor o igual a cero
		// envio mi estado a NORMAL y busco comida
		if (allUCanEat <= 0) {
			estado = NORMAL;
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