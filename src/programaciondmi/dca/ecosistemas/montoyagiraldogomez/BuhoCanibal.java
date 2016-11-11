package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class BuhoCanibal extends EspecieAbstracta implements ICanibal {

	private PImage bird;
	private int vel, vida;
	private PVector pos;
	private float fuerza;

	private float energia;
	private int ciclo;
	private EspecieAbstracta comestible;
	private boolean encontro;

	private Random random;

	public BuhoCanibal(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.bird = app.loadImage("Canibal.png");

		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 75;
		this.fuerza = 80;
		this.energia = 250;
		this.vel = 1;

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));
		
		ciclo=0;
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if (victima.recibirDano(this)) {
				energia += 10;
			}
		}

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
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(3);
		app.image(bird, x, y);

	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub

	}
	
	private void buscarComida(){
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < all.size(); i++) {
			EspecieAbstracta com = all.get(i);
			if(com instanceof BuhoApareable || com instanceof BuhoCanibal || com instanceof BuhoHijo){
				float d = PApplet.dist(x, y, com.getX(), com.getY());
				if(d<energia*2){
					encontro = true;
					comestible = com;
					redireccionar(new PVector(comestible.getX(),comestible.getY()));
				}
			}
		}
	}

	private void redireccionar(PVector target) {
		PVector location = new PVector(x, y);
		pos = PVector.sub(target, location);
		pos.normalize();
		pos.mult(vel);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if (vida > 0) {
			vida -= 5;
			return true;
		}
		return false;
	}

}
