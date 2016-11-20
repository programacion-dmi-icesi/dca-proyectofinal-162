package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

/*VISTAS
1=delante
2=atras
3=izquierda
4=derecha
*/

public class EspecieGomiOmnivoro extends GomiCabra implements IOmnivoro {

	// Constantes
	private final int LIMITE_APAREO = 100;

	public EspecieGomiOmnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		this.x = (int) app.random(-app.width, app.width);
		this.y = (int) app.random(-app.height, app.height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;

		app = Mundo.ObtenerInstancia().getApp();

		// OMNIVORO IZQUIERDO

		// PImage[] izquierda = new PImage[11];
		// PImage[] derecha = new PImage[11];

		izquierda[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/4.png");
		// izquierda[4] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/5.png");
		// izquierda[5] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/6.png");
		// izquierda[6] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/7.png");
		// izquierda[7] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/8.png");
		// izquierda[8] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/9.png");
		// izquierda[9] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/10.png");
		// izquierda[10] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroIzquierda/11.png");

		// OMNIVORO IZQUIERDO
		derecha[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/4.png");
		// derecha[4] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/5.png");
		// derecha[5] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/6.png");
		// derecha[6] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/7.png");
		// derecha[7] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/8.png");
		// derecha[8] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/9.png");
		// derecha[9] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/10.png");
		// derecha[10] =
		// app.loadImage("../dataGomiCabra/omnivoro/omnivoroDerecha/11.png");

		// OMNIVORO FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroFrente/4.png");

		// OMNIVORO ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/omnivoro/omnivoroAtras/4.png");
		maxVida = vida;
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub

	}

}