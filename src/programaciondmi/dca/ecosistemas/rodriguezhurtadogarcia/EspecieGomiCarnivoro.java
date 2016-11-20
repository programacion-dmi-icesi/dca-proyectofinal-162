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

public class EspecieGomiCarnivoro extends GomiCabra implements ICarnivoro {

	public EspecieGomiCarnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		ciclo = 0;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroAtras/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroIzquierda/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/carnivoro/carnivoroDerecha/4.png");
		maxVida = vida;

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
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);

				vista++;

				if (vista == 3) {
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
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo
		return false;
	}

}