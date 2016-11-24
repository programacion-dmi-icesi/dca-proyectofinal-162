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
	private int allUCanEat;
	private int tiempoInmune;
	private int desvanecer = 100;
	private boolean mostrar = true;
	private boolean destruir = false;
	PImage personaje, muerto;
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
				if (destruir == true) {
					// mostrar = false;
					vida = 0;
					Mundo.ObtenerInstancia().getEspecies().remove(this);
				}

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

	@Override
	public void dibujar() {
		personaje = app.loadImage("../img/come.png");
		muerto = app.loadImage("../img/come_dead.png");
		if (mostrar) {
			if (estado == NORMAL || estado == EXTASIS) {
				if (vida <= 30) {
					//app.fill(255, 0, 0);
					//app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y);
				}

				if (vida > 30 && vida <= 50) {
					//app.fill(255, 0, 0);
					//app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y, 80, 80);
				}

				if (vida > 50) {
					//app.fill(255, 0, 0);
					//app.text(vida, x + 17, y + 5);
					app.image(personaje, x, y, 100, 100);
				}
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
		if (ciclo % 70 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
		}

		x += dir.x;
		y += dir.y;

		allUCanEat--;

		if (allUCanEat <= 0) {
			buscarComida();
		}

		if (estado == MUERTO) {

			destruir = true;
		}

	}

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

	public void buscarComida() {
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
	}
}
