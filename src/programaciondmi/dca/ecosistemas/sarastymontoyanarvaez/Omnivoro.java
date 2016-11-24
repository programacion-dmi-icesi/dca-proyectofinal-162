package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.HijoBlanco;
import programaciondmi.dca.ejecucion.Mundo;

public class Omnivoro extends EspecieAbstracta implements IApareable, IOmnivoro {

	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private boolean mostrar = true;
	private boolean destruir = false;
	private int allUCanEat;
	private int temporadaApareo;
	private int tiempoInmune;
	PImage personaje, envenenado, muerto;
	PApplet app = Mundo.ObtenerInstancia().getApp();

	public Omnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 3;
		allUCanEat = (int) app.random(100, 200);
		temporadaApareo = 200;
		tiempoInmune = 100;
		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));

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
				tiempoInmune--;
				// System.out.println(estado);
				if (destruir == true) {
					// mostrar=false;
					vida = 0;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

				// de esta forma se detiene el hilo y se remueve el objeto
				// inactivo
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

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dibujar() {
		personaje = app.loadImage("../img/rep.png");
		envenenado = app.loadImage("../img/rep_poison.png");
		muerto = app.loadImage("../img/rep_dead.png");

		if (mostrar) {
			if (estado == NORMAL) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y);
			}

			if (estado == EXTASIS) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y, 70, 70);
			}

			if (estado == ENVENENADO) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(envenenado, x, y);
			}

			if (estado == MUERTO) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(muerto, x, y);
			}
		}
	}

	@Override
	public void mover() {

		if (temporadaApareo > 0) {
			if (ciclo % 70 == 0) {
				// Definir una direccion aleatoria cada 3 segundos
				int targetX = (int) (Math.random() * 500);
				int targetY = (int) (Math.random() * 500);
				cambiarDireccion(new PVector(targetX, targetY));
			}

			x += dir.x;
			y += dir.y;

			buscarPlantas();
			buscarComida();
		} else {
			buscarPareja();
		}

		allUCanEat--;

		if (allUCanEat <= 0) {
			estado = NORMAL;
			temporadaApareo--;
		}

		if (estado == MUERTO) {
			destruir = true;
		}

	}

	private void buscarPlantas() {
		List<PlantaAbstracta> todas = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comerPlanta(todas.get(i));
		}
	}

	public void buscarPareja() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			if (PApplet.dist(x, y, todas.get(i).getX(), todas.get(i).getY()) < 500) {
				if (todas.get(i).getClass().toString().equals(this.getClass().toString())) {
					if (x < todas.get(i).getX()) {
						x += 5;
					}

					if (x > todas.get(i).getX()) {
						x -= 5;
					}

					if (y < todas.get(i).getY()) {
						y += 5;
					}

					if (y > todas.get(i).getY()) {
						y -= 5;
					}

					if (PApplet.dist(x, y, todas.get(i).getX(), todas.get(i).getY()) < 5) {
						Hijos hijo = new Hijos(ecosistema);
						Hijos hijo2 = new Hijos(ecosistema);
						hijo.setX(x);
						hijo.setY(y);
						hijo2.setX(x + 18);
						hijo2.setY(y + 5);
						ecosistema.agregarEspecie(hijo);
						ecosistema.agregarEspecie(hijo2);
						temporadaApareo = 1000;
						// int targetX = (int) app.random(10, 100);
						// int targetY = (int) app.random(10, 100);
						// cambiarDireccion(new PVector(targetX, targetY));
					}
				}
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30 && tiempoInmune <= 0) {
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

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				try {

					if (victima.getEstado() != MUERTO) {

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

						if (victima instanceof ICarnivoro) {
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
					e.printStackTrace();
				}
			}
		}
	}

	public void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				Mundo.ObtenerInstancia().getPlantas().remove(victima);
				try {
					if (victima.getClass() == PlantaMala.class) {
						setEstado(ENVENENADO);
						velocidad = 2;
						vida -= 10;
					}
					if (victima.getClass() == PlantaBuena.class) {
						setEstado(EXTASIS);
						velocidad = 8;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}