package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Shafox extends EspecieAbstracta implements ICarnivoro, IHerbivoro {

	private PApplet app;
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private PImage[] shafoxImg;
	private int index = 0;

	public Shafox(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// SIEMPRE HAGAN ESTO CON EL APP
		this.app = Mundo.ObtenerInstancia().getApp();
		shafoxImg = new PImage[7];
		imagenes();
		this.vida = 20;
		this.velocidad = 4;

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
			if (app.frameCount % 6 == 0) {
				index++;
				if (index >= 3) {
					index = 0;
				}
			}
			System.out.println();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public void comerPlanta(PlantaAbstracta victima) {
		if (victima instanceof PlantaAbstracta) {
			if (victima instanceof PlantaBuena) {
				PlantaBuena plantaTemp = (PlantaBuena) victima;
				if (PApplet.dist(x, y, plantaTemp.getX(), plantaTemp.getY()) <= 50) {
					plantaTemp.recibirDano(this);
					try {
						setEstado(EXTASIS);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (victima instanceof PlantaMala) {
				PlantaMala plantaTemp = (PlantaMala) victima;
				if (PApplet.dist(x, y, plantaTemp.getX(), plantaTemp.getY()) <= 50) {
					plantaTemp.recibirDano(this);
					try {
						setEstado(ENVENENADO);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(shafoxImg[index], x, y, 68, 100);
		

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
		if (PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida / 2)) {
			vida -= 5;
			try {
				lastimador.setEstado(EXTASIS);
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	public void imagenes() {
		
		shafoxImg[0] = app.loadImage("../data/pngs/shafox-walk-01.png");
		shafoxImg[1] = app.loadImage("../data/pngs/shafox-walk-02.png");
		shafoxImg[2] = app.loadImage("../data/pngs/shafox-walk-03.png");

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		if (victima instanceof EspecieAbstracta) {
			if (victima instanceof ICarnivoro || victima instanceof IHerbivoro) {
				if (PApplet.dist(x, y, victima.getX(), victima.getY()) <= 55) {

					try {
						victima.setEstado(ENFERMO);
						setEstado(EXTASIS);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

}
