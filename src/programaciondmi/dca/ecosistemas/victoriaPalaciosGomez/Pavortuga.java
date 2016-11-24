package programaciondmi.dca.ecosistemas.victoriaPalaciosGomez;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Pavortuga extends EspecieAbstracta implements IHerbivoro {
	private PApplet app;
	private int vida;
	private int velocidad;
	private PVector dir;
	private int ciclo;
	private int index = 0;

	private PlantaMala mala;
	
	private PImage [] pavortuga = new PImage [6];
	
	
	
	public Pavortuga(EcosistemaAbstracto ecosistema){
		super(ecosistema);
		this.estado = NORMAL;
		this.app = Mundo.ObtenerInstancia().getApp();
		imagenes();
		this.vida = 20;
		this.velocidad = 8;
		int targetX = (int) (Math.random()*500);
		int targetY = (int) (Math.random()*500);
		cambiarDireccion(new PVector(targetX, targetY));
		
		Thread nt = new Thread(this);
		nt.start();
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (vida > 0) {
			mover();
			if (app.frameCount % 6 == 0) {
				index++;
				if (index > 2) {
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

	
	
	
	@Override
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
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(pavortuga[index], x, y);
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			cambiarDireccion(new PVector(targetX, targetY));
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
	}
	
	private void imagenes () {
		//pavortuga SANO
		pavortuga[0] = app.loadImage("../data/pngs/pavortuga-walk-01.png");
		pavortuga[1] = app.loadImage("../data/pngs/pavortuga-walk-02.png");
		pavortuga[2] = app.loadImage("../data/pngs/pavortuga-walk-03.png");
		//pavortuga enfermo
		pavortuga[3] = app.loadImage("../data/pngs/pavortuga-sick-1.png");
		pavortuga[4] = app.loadImage("../data/pngs/pavortuga-sick-2.png");
		pavortuga[5] = app.loadImage("../data/pngs/pavortuga-sick-3.png");
	}
	
}
