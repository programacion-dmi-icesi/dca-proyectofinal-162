package programaciondmi.dca.ecosistemas.lopezmoralesurango;

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
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.HijoBlanco;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieOmni extends EspecieAbstracta implements IOmnivoro, IApareable {
	private PImage Omnivoro;
	private int vida;

	private int ciclo;
	private int energia;
	private PVector pos;
	private PVector dir;
	private PVector cambiarDireccion;
	private EspecieAbstracta parejaCercana;

	private final int LIMITE_APAREO = 100;

	public EspecieOmni(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		Omnivoro = app.loadImage("omni.png");

		this.energia = 250;
		this.vida = 25;

		pos = new PVector(x, y);
		dir = new PVector(0, 0);

		/*
		 * int targetX = random.nextInt(); int targetY = random.nextInt();
		 * cambiarDireccion(new PVector(targetX, targetY));
		 */

		ciclo = 0;

		Thread ho = new Thread(this);
		ho.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 5;
				System.out.println("TRAGO!!!");
			}
		}
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 20;
			}
		}
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		HijoOmni hijoOmni = new HijoOmni(ecosistema);
		hijoOmni.setX(this.x);
		hijoOmni.setY(this.y);
		ecosistema.agregarEspecie(hijoOmni);
		return hijoOmni;
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(Omnivoro, pos.x, pos.y);
	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			cambiarDireccion(new PVector(x, y));
		}
			if (energia > 0) {
				// System.out.println("[id=" + id + ", energia=" + energia +
				// "]");
				// Si tengo buena energía para aparearme
				if (energia > LIMITE_APAREO) {
					buscarParejaCercana();
					// Si hay una pareja cercana la prioridad es reproducirse
					if (parejaCercana != null) {
						intentarAparear();
					}else{
						buscarComida();
						if (ciclo % 30 == 0) {
							cambiarDireccion(new PVector(x,y));
						}
					}
				}
			}
		}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(11);
				ciclo++;
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
					cambiarDireccion(new PVector(e.getX(), e.getY()));

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
		float dist = PApplet.dist(x, y, parejaCercana.getX(), parejaCercana.getY());
		if (dist < vida) {
			IApareable a = (IApareable) parejaCercana;
			ecosistema.agregarEspecie(aparear(a));
			// perder energia
			energia -= 50;
		}
	}

	private void buscarComida() {
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}

	private void cambiarDireccion(PVector objetivo) {
		PVector distanX = PVector.sub(objetivo, pos);
		distanX.y = 0;
		PVector distanY = PVector.sub(objetivo, pos);
		distanY.x = 0;
		float dX = distanX.mag();
		distanX.normalize();
		distanY.normalize();

		PVector direccionX = PVector.sub(distanX, dir);
		dir.add(direccionX);

		if (dX <= 0) {
			PVector direccionY = PVector.sub(distanY, dir);
			dir.add(direccionY);
		}
		pos.add(dir);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
