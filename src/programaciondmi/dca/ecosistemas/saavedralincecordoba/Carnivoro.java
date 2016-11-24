package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Carnivoro extends EspecieAbstracta implements ICarnivoro {

	private static final int CENTER = 0;
	private static final int CORNER = 0;
	private int vida;
	private float fuerza;
	private int velocidad;
	
	private PImage[] estados = new PImage[12];
	private PImage[] derecha = new PImage[12];
	private PImage[] izquierda = new PImage[12];
	private PImage[] atras = new PImage[12];

	
	// VALORES PARA MANEJAR LOS ARREGLOS DE IMAGENES
	private int nDerecha, nIzquierda, nAtras;
	/*
	 * SE UTILIZA PARA DEFINIR CUANDO EL INDIVIDUO PUEDE EALIZAR ACCIONES
	 * MOVERSE, COMER, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// CONSTANTES
	private Random random;

	public Carnivoro(EcosistemaAbstracto ecosistema) {

		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.vida = 100;
		this.velocidad = 8;
		// MOVIMIENTO HACIA EL FRENTE
		estados[0] = app.loadImage("../data/Magma_Frente/Magma_Frente_0.png");
		estados[1] = app.loadImage("../data/Magma_Frente/Magma_Frente_1.png");
		estados[2] = app.loadImage("../data/Magma_Frente/Magma_Frente_2.png");
		estados[3] = app.loadImage("../data/Magma_Frente/Magma_Frente_3.png");
		estados[4] = app.loadImage("../data/Magma_Frente/Magma_Frente_4.png");
		estados[5] = app.loadImage("../data/Magma_Frente/Magma_Frente_5.png");
		estados[6] = app.loadImage("../data/Magma_Frente/Magma_Frente_6.png");
		estados[7] = app.loadImage("../data/Magma_Frente/Magma_Frente_7.png");
		estados[8] = app.loadImage("../data/Magma_Frente/Magma_Frente_8.png");
		estados[9] = app.loadImage("../data/Magma_Frente/Magma_Frente_9.png");
		estados[10] = app.loadImage("../data/Magma_Frente/Magma_Frente_10.png");
		estados[11] = app.loadImage("../data/Magma_Frente/Magma_Frente_11.png");

		// MOVIMIENTO HACIA LA DERECHA
		derecha[0] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_0.png");
		derecha[1] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_1.png");
		derecha[2] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_2.png");
		derecha[3] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_3.png");
		derecha[4] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_4.png");
		derecha[5] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_5.png");
		derecha[6] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_6.png");
		derecha[7] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_7.png");
		derecha[8] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_8.png");
		derecha[9] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_9.png");
		derecha[10] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_10.png");
		derecha[11] = app.loadImage("../data/Magma_Derecho/Magma_Derecho_11.png");

		// MOVIMIENTO HACIA LA IZQUIER../data/Magma_Derecho/D
		izquierda[0] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_0.png");
		izquierda[1] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_1.png");
		izquierda[2] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_2.png");
		izquierda[3] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_3.png");
		izquierda[4] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_4.png");
		izquierda[5] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_5.png");
		izquierda[6] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_6.png");
		izquierda[7] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_7.png");
		izquierda[8] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_8.png");
		izquierda[9] = app.loadImage("../data/Magma_Izquierda/Magma_Izquierda_9.png");
		izquierda[10] = app.loadImage("./data/Magma_Izquierda/Magma_Izquierda_10.png");
		izquierda[11] = app.loadImage("./data/Magma_Izquierda/Magma_Izquierda_11.png");

		// MOVIMIENTO HACIA ATRAS
		atras[0] = app.loadImage("../data/Magma_Atras/Magma_Atras_0.png");
		atras[1] = app.loadImage("../data/Magma_Atras/Magma_Atras_1.png");
		atras[2] = app.loadImage("../data/Magma_Atras/Magma_Atras_2.png");
		atras[3] = app.loadImage("../data/Magma_Atras/Magma_Atras_3.png");
		atras[4] = app.loadImage("../data/Magma_Atras/Magma_Atras_4.png");
		atras[5] = app.loadImage("../data/Magma_Atras/Magma_Atras_5.png");
		atras[6] = app.loadImage("../data/Magma_Atras/Magma_Atras_6.png");
		atras[7] = app.loadImage("../data/Magma_Atras/Magma_Atras_7.png");
		atras[8] = app.loadImage("../data/Magma_Atras/Magma_Atras_8.png");
		atras[9] = app.loadImage("../data/Magma_Atras/Magma_Atras_9.png");
		atras[10] = app.loadImage("../data/Magma_Atras/Magma_Atras_10.png");
		atras[11] = app.loadImage("../data/Magma_Atras/Magma_Atras_11.png");

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

				try {
					System.out.println("entreo");
					if (victima.getClass() == PlantaBuena.class) {
						PlantaBuena b = (PlantaBuena) victima;
						setEstado(EXTASIS);
						velocidad = 10;
						b.setMostrar(false);
						System.out.println("no error");
						// Mundo.ObtenerInstancia().getPlantas().remove(victima);
					}

					if (victima.getClass() == PlantaMala.class) {
						setEstado(ENFERMO);
						velocidad = 8;
						// Mundo.ObtenerInstancia().getPlantas().remove(victima);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(CENTER);
		app.image(estados[estado], x, y);
		estado++;
		if(estado>=12){
			estado=0;
			estado++;
		}
		app.imageMode(CORNER);
		

	}

	@Override
	public void mover() {

		if (ciclo % 30 == 0) {
			// DEFINIR UNA DIRECCION ALEATORIA CADA 3 SEGUNDOS segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
			cicloImagenes(targetX, targetY);
			// System.out.println("CAMBIO DIRECCION!");
			
		}
		
		x += dir.x;
		y += dir.y;

		buscarComida();

	}
	
	private void cicloImagenes(int targetX, int targetY){
		// MOVIMIENTO HACIA LA IZQUIERDA
		if(targetX<0){
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(izquierda[estado], x, y);
			estado++;
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);

		}
		//MOVIMIENTO HACIA LA DERECHA
		if(targetX>0){
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(derecha[estado], x, y);
			estado++;
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
		}
		//MOVIMIENTO HACIA ARRIBA
		if(targetY<0){
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(atras[estado], x, y);
			estado++;
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
			
		}
		//MOVIMIENTO HACIA ABAJO
		if(targetY>0){
			PApplet app = Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(estados[estado], x, y);
			estado++;
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
		}
	}

	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		// System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		if (victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {

				try {
					if (victima.getClass() == Canibal.class) {
						Canibal c = (Canibal) victima;
						vida -= 2;
						System.out.println("Canibal Recibe Daño");
						if (vida == 0) {
							Mundo.ObtenerInstancia().getEspecies().remove(victima.getClass());
							System.out.println("Canibal Muere");
						}
					}

					if (victima.getClass() == Hervivoro.class) {
						Hervivoro h = (Hervivoro) victima;
						if(Mundo.ObtenerInstancia().getApp().dist(x,y,h.getX(),h.getY())<50){
						vida-=2;
						System.out.println("Hervivoro Recibe Daño");
						}
						if(vida==0){
							Mundo.ObtenerInstancia().getEspecies().remove(victima.getClass());
							System.out.println("Hervivoro Muere");
						}
					}

					if (victima.getClass() == Apareable.class) {
						Apareable a = (Apareable) victima;
						vida-=2;
						System.out.println("Apareable Recibe Daño");
						if(vida==0){
							Mundo.ObtenerInstancia().getEspecies().remove(victima.getClass());
						}
					}
					//Mundo.ObtenerInstancia().getEspecies().remove(victima);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

	}

	public void buscarComida() {

		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		List<PlantaAbstracta> plants = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));

		}
		for (int i = 0; i < plants.size(); i++) {
			comerPlanta(plants.get(i));

		}
		System.out.println("funciona");

	}
}
