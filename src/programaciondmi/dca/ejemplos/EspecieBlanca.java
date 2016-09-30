package programaciondmi.dca.ejemplos;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieBlanca extends EspecieAbstracta implements IApareable, ICarnivoro {
	private int vida;
	private float fuerza;
	private EspecieAbstracta parejaCercana;
	private PVector dir;

	public EspecieBlanca(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		Random random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 250;
		System.out.println(this);
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {

		return new HijoBlanco(ecosistema);
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(255);
		app.ellipse(x, y, vida, vida);
	}

	@Override
	public void mover() {
		if (fuerza > 0) {
			
			// Si hay una pareja cercana la prioridad es reproducirse
			if (parejaCercana != null) {
				dir = new PVector(parejaCercana.getX()-x, parejaCercana.getY()-y);
			} else {
				// ir hacia el centro
				dir = new PVector(250-x,250-y);
			}
			
			dir.normalize();
			this.x += dir.x * fuerza * 0.005;
			this.y += dir.y * fuerza * 0.005;
			fuerza-=0.01;
		}
		
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();

			buscarParejaCercana();
			try{
				Thread.sleep(100);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * <p>
	 * Este método busca a una especie apareable dentro del rango permitido por
	 * la fuerza actual. las misma
	 * </p>
	 */
	private void buscarParejaCercana() {
		if (fuerza > 0) {
			
			List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
			System.out.println("Buscando pareja entre "+todas.size()+" especies del mundo");
			ListIterator<EspecieAbstracta> iterador = todas.listIterator();
			boolean encontro = false;
			while (!encontro && iterador.hasNext()) {
				EspecieAbstracta e = iterador.next();
				if ((e instanceof IApareable) && !e.equals(this)) {
					float dist = PApplet.dist(x, y, e.getX(), e.getY());
					System.out.println("encontré apareable a "+dist);
					if (dist < fuerza) {
						System.out.println("Encontré una pareja cercana");
						encontro = true;
						parejaCercana = e;
					}
				}
			}
			System.out.println("No encontró una pareja cercana");
		}else{
			System.out.println("Toy sin fuerza para buscar pareja");
		}

	}

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana=" + parejaCercana + ", dir="
				+ dir + ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	
}
