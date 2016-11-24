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
	private PImage Omnivoro, Omnivoro1, Omnivoro2, Omnivoro3, Omnivoro4;
	private int vida;
	private Random random;
	private int ciclo;
	private int energia;
	private int velocidad;
	private PVector pos;
	private PVector dir;
	private PVector cambiarDireccion;
	private EspecieAbstracta parejaCercana;

	private final int LIMITE_APAREO = 100;

	public EspecieOmni(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		Omnivoro = app.loadImage("omni0.png");
		Omnivoro1 = app.loadImage("omni1.png");
		Omnivoro2 = app.loadImage("omni2.png");
		Omnivoro3 = app.loadImage("omni3.png");
		Omnivoro4 = app.loadImage("omni4.png");

		//this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		//this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.energia = 250;
		this.vida = 25;
		this.velocidad = 3;

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
		/*
		 * if
		 * (!victima.getClass().toString().equals(this.getClass().toString())) {
		 * if (victima.recibirDano(this)) { energia += 20; } }
		 */

		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (PApplet.dist(x, y, victima.getX(), victima.getY()) <= 100) {
				// System.out.println(x +" " y +" " victima.getX() +" "
				// victima.getY());

				if (victima.recibirDano(this)) {
					try {
						if (victima.getClass() == PlantaBuena.class) {
							PlantaBuena buena = (PlantaBuena) victima;
							velocidad = 10;
							energia = 1000;
							buena.setMostrar(false);

							if (energia <= 1000 && energia >= 800) {
								setEstado(EXTASIS);
							}
							// Mundo.ObtenerInstancia().getPlantas().remove(victima);
							//System.out.println("ME COMI UNA SUPER PLANTAAAAAAAAAAAAAAAAAAA");
							// System.out.println(Mundo.ObtenerInstancia().getPlantas().remove(victima));

						} // else {

						if (victima.getClass() == PlantaMala.class) {
							//System.out.println("ME COMI UNA MALAAAAAAAAAAAAA");
							PlantaMala malita = (PlantaMala) victima;
							velocidad = 1;
							energia -= 5;
							malita.setMostrar(false);
							if (energia <= 600 && energia >= 450) {
								setEstado(ENVENENADO);
							} else {
								if (energia <= 450 && energia >= 200) {
									setEstado(ENFERMO);
								}
							}
							if (energia <= 200 && energia >= 0) {

							}

						}
						// Mundo.ObtenerInstancia().getPlantas().remove(victima);
						System.out.println(energia + "ENERGIAAAAAAAAAAAA");

						// }
						// }
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
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

		switch (estado) {

		case (NORMAL):
			app.image(Omnivoro, pos.x, pos.y);
			break;

		case (ENVENENADO):
			app.image(Omnivoro2, pos.x, pos.y);
			break;

		case (ENFERMO):
			app.image(Omnivoro3, pos.x, pos.y);
			break;

		case (EXTASIS):
			app.image(Omnivoro4, pos.x, pos.y);
			break;

		case (MUERTO):
			// app.image(Herviboro3, x, y);

			break;

		// System.out.println(dir.x);
		// System.out.println(energia + "ENERGIAAAAAAAAAAAAAAA");
		}
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
				} else {
					buscarComida();
					if (ciclo % 30 == 0) {
						cambiarDireccion(new PVector(x, y));
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
