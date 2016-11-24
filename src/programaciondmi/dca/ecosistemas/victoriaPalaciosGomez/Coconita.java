package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Coconita extends EspecieAbstracta implements ICarnivoro {
	
	private PApplet app;
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private PImage[] coconitaF;
	private int index = 0;
	
	public Coconita(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		//SIEMPRE HAGAN ESTO CON EL APP
		this.app = Mundo.ObtenerInstancia().getApp();
		coconitaF = new PImage[7];
		imagenes();
		this.vida = 20;
		this.velocidad = 4;
		
		int targetX = (int) (Math.random()*500);
		int targetY = (int) (Math.random()*500);
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
				if (index > 6) {
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
		app.image(coconitaF[index],x,y,68,100);

	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
			//System.out.println("CAMBIO DIRECCION!");
		}
		
		x+=dir.x;
		y+=dir.y;

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) <= (vida/2)){
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
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}
	
	public void imagenes() {
		
		coconitaF[0] = app.loadImage("../data/presetacionPersonajes-08.png");
		coconitaF[1] = app.loadImage("../data/presetacionPersonajes-09.png");
		coconitaF[2] = app.loadImage("../data/presetacionPersonajes-10.png");
		coconitaF[3] = app.loadImage("../data/presetacionPersonajes-11.png");
		coconitaF[4] = app.loadImage("../data/presetacionPersonajes-12.png");
		coconitaF[5] = app.loadImage("../data/presetacionPersonajes-13.png");
		coconitaF[6] = app.loadImage("../data/presetacionPersonajes-14.png");
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}
	
	
}
