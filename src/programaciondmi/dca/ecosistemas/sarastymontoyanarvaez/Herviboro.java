package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;
import processing.core.PImage;

public class Herviboro extends EspecieAbstracta implements IHerbivoro, IApareable {

	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private PImage personaje;
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private boolean mostrar = true;

	public Herviboro(EcosistemaAbstracto ecosistema) {

		super(ecosistema);

		this.vida = 20;
		this.velocidad = 3;
		personaje = app.loadImage("../img/herv.png");
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
				// TODO: handle exception
			}
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

	@Override
	public void dibujar() {

		if (mostrar) {
			app.imageMode(PConstants.CENTER);
			app.image(personaje, x, y);
			app.imageMode(PConstants.CORNER);
		}
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

		buscarPlantas();

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= 30) {
			vida -= 5;
			try {
				lastimador.setEstado(EXTASIS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mostrar = false;
			return true;
		}

		return false;
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	private void buscarPlantas() {
		List<PlantaAbstracta> todas = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			System.out.println(todas.size());
			comerPlanta(todas.get(i));
		}
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

}
