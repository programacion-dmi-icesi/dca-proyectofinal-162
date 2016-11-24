package programaciondmi.dca.ecosistemas.lopezmoralesurango;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieHervi extends EspecieAbstracta implements IHerbivoro {
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private int cicloC;
	private int vida;
	private int velocidad;
	private PVector dir;
	private PImage Herviboro, Herviboro1, Herviboro2, Herviboro3, Herviboro4;
	private Random random;

	private float energia;

	public EspecieHervi(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.energia = 800;
		this.vida = 20;
		this.velocidad = 3;
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);

		PApplet app = Mundo.ObtenerInstancia().getApp();
		// se cargan las imagenes del herbivoro sus estados
		Herviboro = app.loadImage("hervi0.png");
		Herviboro1 = app.loadImage("hervi1.png");
		Herviboro2 = app.loadImage("hervi2.png");
		Herviboro3 = app.loadImage("hervi3.png");
		Herviboro4 = app.loadImage("hervi4.png");

		int targetX = (int) (Math.random() * 500);
		int targetY = (int) (Math.random() * 500);
		cambiarDireccion(new PVector(targetX, targetY));

		Thread hb = new Thread(this);
		hb.start();
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
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
							
							if(energia <=1000 && energia>=800){
								setEstado(EXTASIS);
							}
							//Mundo.ObtenerInstancia().getPlantas().remove(victima);
							System.out.println("ME COMI UNA SUPER PLANTAAAAAAAAAAAAAAAAAAA");
							//System.out.println(Mundo.ObtenerInstancia().getPlantas().remove(victima));
							
							
						} //else {

							if (victima.getClass() == PlantaMala.class) {
								System.out.println("ME COMI UNA MALAAAAAAAAAAAAA");
								PlantaMala malita = (PlantaMala) victima;
								velocidad = 1;
								energia -= 5;
								malita.setMostrar(false);
								if(energia <=600 && energia >= 450){
									setEstado(ENVENENADO);
								}else{
									if(energia <=450 && energia >= 200){
										setEstado(ENFERMO);
									}
								}
										if(energia <=200 && energia >= 0){
											
										}
							
								}
								//Mundo.ObtenerInstancia().getPlantas().remove(victima);
								System.out.println(energia +"ENERGIAAAAAAAAAAAA");
								
							//}
						//}
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}

	}
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		switch (estado) {

		case (NORMAL):
			app.image(Herviboro1, x, y);
			break;

		case (ENVENENADO):
			app.image(Herviboro2, x, y);
			break;

		case (ENFERMO):
			app.image(Herviboro3, x, y);
			break;

		case (EXTASIS):
			app.image(Herviboro, x, y);
			break;

		case (MUERTO):
			//app.image(Herviboro3, x, y);
			
			break;

		// System.out.println(dir.x);
		// System.out.println(energia + "ENERGIAAAAAAAAAAAAAAA");
		}
	}

	@Override
	public void mover() {
		if (energia > 0) {
			buscarPlanta();
			if (cicloC % 30 == 0) {
				// Definir una direccion aleatoria cada 3 segundos
				int targetX = (int) random.nextInt();
				int targetY = (int) random.nextInt();
				cambiarDireccion(new PVector(targetX, targetY));

			}

			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}

		if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.dir.x *= -1;
		}
		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.dir.y *= -1;
		}

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep((int) app.random(33, 55));
				cicloC++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void buscarPlanta() {
		List<PlantaAbstracta> todas = Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comerPlanta(todas.get(i));
			// System.out.println("busco planticaaaa");
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
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida / 2)) {
			vida -= 5;
			// try {
			// lastimador.setEstado(EXTASIS);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			return true;
		}
		return false;
	}
}
