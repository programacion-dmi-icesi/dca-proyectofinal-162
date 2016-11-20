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

public class EspecieGomiCanibal extends GomiCabra implements ICanibal {

	public EspecieGomiCanibal(EcosistemaGomiCabra ecosistema, int vista) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		this.vista = vista;

		app = Mundo.ObtenerInstancia().getApp();

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/canibal/canibalFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/canibal/canibalAtras/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/canibal/canibalIzquierda/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/canibal/canibalDerecha/4.png");
		maxVida = vida;

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

				System.err.println("se ha muerto!");
			}
		}
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