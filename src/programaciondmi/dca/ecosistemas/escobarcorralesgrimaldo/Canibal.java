package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class Canibal extends EspecieAbstracta implements ICanibal {

	private PImage[] cani = new PImage[1];

	private int vida, velocidad;
	private PVector dir;
	private int energia;
	private int ciclo;

	public Canibal(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.vida = 20;
		this.velocidad = 5;

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
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		cani[0] = app.loadImage("../data/Canibal.png");
		// app.fill(255,255,0);
		app.image(cani[0], x, y);
		// app.ellipse(x, y, 100, 100);

	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
			// System.out.println("CAMBIO DIRECCION!");
		}

		x += dir.x;
		y += dir.y;
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// Codigo de base
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) < 500) {
			vida -= 5;
			try {
				if (vida == 20) {
					lastimador.setEstado(NORMAL);
				}
				if (vida == 15) {
					lastimador.setEstado(ENVENENADO);
				}
				if (vida == 10) {
					lastimador.setEstado(ENFERMO);
				}
				if (vida == 5) {
					lastimador.setEstado(EXTASIS);
				}
				if (vida == 0) {
					lastimador.setEstado(MUERTO);
				}

			} catch (Exception e) {
				e.printStackTrace();
				//vida -= 5;
			}
			return true;

		}

		return false;
	}

	@Override
	public void comer(EspecieAbstracta victima) {
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

}
