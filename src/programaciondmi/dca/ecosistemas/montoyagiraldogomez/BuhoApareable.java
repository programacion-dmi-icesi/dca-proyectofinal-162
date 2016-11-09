package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

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
import programaciondmi.dca.ejecucion.Mundo;

public class BuhoApareable extends EspecieAbstracta implements IApareable {

	private PImage bird;
	private int vida, vel;
	private float fuerza, energia;
	private EspecieAbstracta parejaCerca;
	private PlantaAbstracta plantaCerca;
	private PVector pos;
	private int ciclo;

	private final int LIMITE_APAREO = 100;
	private Random random;

	public BuhoApareable(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.vel = 2;
		this.ciclo = 0;

		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.bird = app.loadImage("Herb.png");

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		redireccionar(new PVector(targetX, targetY));

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

			}
		}

	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		BuhoHijo kid = new BuhoHijo(ecosistema);
		kid.setX(this.x);
		kid.setY(this.y);
		ecosistema.agregarEspecie(kid);
		return kid;
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(bird, x, y);

	}

	@Override
	public void mover() {
		if (energia > 0) {
			if (energia > LIMITE_APAREO) {
				buscarPareja();
				if (parejaCerca != null) {
					intentarAparear();
				}
			} else {
				buscarPlanta();
				if (ciclo % 30 == 0) {
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					redireccionar(new PVector(targetX, targetY));
				}
			}
			this.x += pos.x;
			this.y += pos.y;
			energia -= 0.01;
		}

		if (this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0) {
			this.pos.x *= -1;
		}

		if (this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0) {
			this.pos.y *= -1;
		}

	}

	private void buscarPareja() {
		List<EspecieAbstracta> all = Mundo.ObtenerInstancia().getEspecies();
		ListIterator<EspecieAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		while (!encontro && iterador.hasNext()) {
			EspecieAbstracta cerca = iterador.next();
			if ((cerca instanceof IApareable) && !cerca.equals(this)) {
				float d = PApplet.dist(x, y, cerca.getX(), cerca.getY());
				if (d < energia) {
					encontro = true;
					parejaCerca = cerca;

				}
			}
		}
		if (!encontro) {
			parejaCerca = null;
		}
	}

	private void intentarAparear() {
		float d = PApplet.dist(x, y, parejaCerca.getX(), parejaCerca.getY());
		if (d < vida) {
			IApareable a = (IApareable) parejaCerca;
			ecosistema.agregarEspecie(aparear(a));
			energia -= 50;
		}
	}

	private void alimentar(PlantaAbstracta planta) {
		if(planta != null){
			
		}
	}

	private void buscarPlanta() {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		while(!encontro && iterador.hasNext()){
			PlantaAbstracta p = iterador.next();
			//float d = PApplet.dist(x, y, p.getX(), p.getY());
//			if(d<energia){
//				encontro = true;
//				plantaCerca = p;
//				//redireccionar(new PVector(plantaCerca.getX(),plantaCerca.getY()));
//			}
		}
		
		if(!encontro){
			plantaCerca = null;
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
		// TODO Auto-generated method stub
		return false;
	}

}
