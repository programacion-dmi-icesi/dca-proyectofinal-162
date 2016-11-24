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
	private boolean destruir = false;
	private int tiempoInmunidad;
	EcosistemaSMN ref;
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
				tiempoInmunidad--;
				
				/*Instruccion pque se ejecuta una vez el objeto esta en estado MUERTO
				 * 
				 */
				
				if (destruir == true) {
					// mostrar = false;
					vida = 0;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				//Mundo.ObtenerInstancia().getPlantas().remove(victima);
				try {
					if (victima.getClass() == PlantaMala.class) {
						setEstado(ENVENENADO);
						velocidad = 2;
						
					}
					if (victima.getClass() == PlantaBuena.class) {
						setEstado(EXTASIS);
						velocidad = 5;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void dibujar() {
		personaje = app.loadImage("../img/herv.png");
		envenenado = app.loadImage("../img/herv_poison.png");
		muerto = app.loadImage("../img/herv_dead.png");

		if (mostrar) {
			if (estado == NORMAL) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y);
			}

			if (estado == EXTASIS) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(personaje, x, y, 60, 60);
			}

			if (estado == ENVENENADO) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				app.image(envenenado, x, y);
			}
			if (estado == MUERTO) {
				//app.fill(255, 0, 0);
				//app.text(vida, x + 17, y + 5);
				// app.tint(255, 255, 255, desvanecer);
				app.image(muerto, x, y);
			}
		}
	}

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

			buscarPlantas();
		}

		if (estado == MUERTO) {
			destruir = true;
		}
	}

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

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	private void buscarPlantas() {
		List<PlantaAbstracta> todas = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comerPlanta(todas.get(i));
		}
	}
}