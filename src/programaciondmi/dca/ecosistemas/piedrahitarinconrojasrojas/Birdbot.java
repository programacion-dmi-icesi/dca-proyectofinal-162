package programaciondmi.dca.ecosistemas.piedrahitarinconrojasrojas;

import java.util.Iterator; 
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Birdbot extends EspecieAbstracta implements IApareable, IHerbivoro {
	private int vida, ciclo, contador;
	private float velocidad;
	private float fuerza, energia, defensa;
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private EspecieAbstracta parejaCercana;
	private PlantaAbstracta plantaCerca;
	private PVector dir;
	PImage[] pjBirdbot = new PImage[16];
	PImage poison, ivel, ienergy, iforce, idefense;
	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;
	
	// animacion
	
	private boolean animonbuena =false;
	private boolean animonmala =false;
	public Birdbot(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 100;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		this.defensa = 100;

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));
		contador= 8;
		ciclo = 0;
		
		PApplet app = Mundo.ObtenerInstancia().getApp();
		// birdbot back
		pjBirdbot[0] = app.loadImage("DataTikiBots/Birdbot/bA01.png");
		pjBirdbot[1] = app.loadImage("DataTikiBots/Birdbot/bA02.png");
		pjBirdbot[2] = app.loadImage("DataTikiBots/Birdbot/bA03.png");
		pjBirdbot[3] = app.loadImage("DataTikiBots/Birdbot/bA04.png");
		pjBirdbot[4] = app.loadImage("DataTikiBots/Birdbot/bA05.png");
		// birdbot derecha
		pjBirdbot[5] = app.loadImage("DataTikiBots/Birdbot/bD01.png");
		pjBirdbot[6] = app.loadImage("DataTikiBots/Birdbot/bD02.png");
		pjBirdbot[7] = app.loadImage("DataTikiBots/Birdbot/bD03.png");
		// birdbot Front
		pjBirdbot[8] = app.loadImage("DataTikiBots/Birdbot/bF01.png");
		pjBirdbot[9] = app.loadImage("DataTikiBots/Birdbot/bF02.png");
		pjBirdbot[10] = app.loadImage("DataTikiBots/Birdbot/bF03.png");
		pjBirdbot[11] = app.loadImage("DataTikiBots/Birdbot/bF04.png");
		pjBirdbot[12] = app.loadImage("DataTikiBots/Birdbot/bF05.png");
		// birdbot left
		pjBirdbot[13] = app.loadImage("DataTikiBots/Birdbot/bZ01.png");
		pjBirdbot[14] = app.loadImage("DataTikiBots/Birdbot/bZ02.png");
		pjBirdbot[15] = app.loadImage("DataTikiBots/Birdbot/bZ03.png");
	
		
		poison = app.loadImage("DataTikiBots/iconos/envenenado.png");
		ivel = app.loadImage("DataTikiBots/iconos/iconoVelocidad.png");
		ienergy = app.loadImage("DataTikiBots/iconos/iconoEnergia.png");
		iforce = app.loadImage("DataTikiBots/iconos/iconoFuerza.png");
		idefense = app.loadImage("DataTikiBots/iconos/iconoDefensa.png");
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		Hijo hijo = new Hijo(ecosistema);
		hijo.setX(this.x);
		hijo.setY(this.y);
		ecosistema.agregarEspecie(hijo);
		
		return hijo;
		//return null;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
			
		// animaciones encima de la barra superior (iconos)
		
		if (animonbuena==true) {
			app.fill(0);
			app.text("+20", x,y-70);
			app.text("+20", x+30,y-70);
			app.text("+0.05", x+60,y-70);
			app.image(iforce, x, y-60, 30,30);
			app.image(idefense, x+30, y-60, 30,30);
			app.image(ivel, x+60, y-60, 30,30);
		
		}
		if (animonmala==true) {
			app.fill(0);
			app.text("-20", x,y-70);
			app.text("-20", x+30,y-70);
			app.text("-0.01", x+60,y-70);
			app.image(iforce, x, y-60, 30,30);
			app.image(idefense, x+30, y-60, 30,30);
			app.image(ivel, x+60, y-60, 30,30);
		
		}
		
		
		app.noStroke();
		switch (vida) {
		
		case 100:
		
			app.fill(0,255,0);
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			app.rect(x+45, y-20, 20, 10);
			app.rect(x+65, y-20, 20, 10);
			app.rect(x+85, y-20, 20, 10);
		break;
		case 80:
			app.fill(255,255,0);
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			app.rect(x+45, y-20, 20, 10);
			app.rect(x+65, y-20, 20, 10);
			break;
		case 60:
			app.fill(255,0,0);
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			app.rect(x+45, y-20, 20, 10);
			break;
		case 40:
			app.fill(255,0,255);
			app.rect(x+05, y-20, 20, 10);
			app.rect(x+25, y-20, 20, 10);
			break;
		case 20:
			app.fill(255,0,255);
			app.rect(x+05, y-20, 20, 10);
			break;
		case 0:
			
			break;
		}
		
		
		if (getEstado() == NORMAL){
			
			app.noTint();
		}
		if(getEstado() == ENFERMO){
			
			app.tint(0,255,0);
		}
		
		if(getEstado() == ENVENENADO){
		
			app.tint(156,28,247);
		}
		
		if(getEstado() == EXTASIS){
			app.tint(28,45,247);
		}
		
		if(getEstado() == MUERTO){
			app.image(poison, x+25, y-60, 50,50);
			app.tint(63,63,63);
			/*Mundo.ObtenerInstancia().getEspecies().remove(this);
			this.ecosistema.getEspecies().remove(this);*/
		}
		
		app.image(pjBirdbot[contador], x, y, 100,100);
		app.tint(255,255,255);	
		
		
	}

	@Override
	public void mover() {
		if (energia > 0) {
			//System.out.println("[id=" + id + ", energia=" + energia + "]");
			
				// Si tengo buena energía para aparearme
				if (energia > LIMITE_APAREO) {
					buscarParejaCercana();
					// Si hay una pareja cercana la prioridad es reproducirse
					if (parejaCercana != null) {
						intentarAparear();
					}
					
				} else {
					buscarComida();
					
					if(plantaCerca != null){
						comerPlanta(plantaCerca);
					}
					
					if (ciclo % 30 == 0 && plantaCerca == null) {
						int targetX = random.nextInt();
						int targetY = random.nextInt();
						cambiarDireccion(new PVector(targetX, targetY));
					}
				}
				
			// moverse en la dirección asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
			
		}
		
		if(this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0){
			this.dir.x*=-1;			
		}
		if(this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0){
			this.dir.y *= -1;			
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

	/**
	 * 
	 * Este método busca a una especie apareable dentro del rango permitido por
	 * la fuerza actual.
	 * 
	 */
	private void buscarParejaCercana() {
		synchronized (this.ecosistema.getEspecies()) {
			List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
			ListIterator<EspecieAbstracta> iterador = todas.listIterator();
			boolean encontro = false;
			while (!encontro && iterador.hasNext()) {
				EspecieAbstracta e = iterador.next();
				if ((e instanceof IApareable) && !e.equals(this)) {
					float dist = PApplet.dist(x, y, e.getX(), e.getY());
	
					if (dist < energia) {
	
						encontro = true;
						parejaCercana = e;
						
						// Cambiar la dirección
						cambiarDireccion(new PVector(parejaCercana.getX(), parejaCercana.getY()));
					}
				}
			}
			// asegurarse de que la referencia sea null;
			if (!encontro) {
				parejaCercana = null;
				//System.out.println("No encontró una pareja cercana");
			}
		}
	}

	/**
	 * <p>
	 * Este método valida que una pareja cercana este a la distancia adecuada y
	 * genera un descendiente en caso de cumplirse la condición
	 * </p>
	 */
	private void intentarAparear() {

		float dist = PApplet.dist(x, y, parejaCercana.getX(), parejaCercana.getY());
		if (dist < vida) {
			IApareable a = (IApareable) parejaCercana;
			ecosistema.agregarEspecie(aparear(a));
			// perder energia
			energia -= 50;
		}

	}
	
	/**
	 * <p>
	 * Este método valida recorre el arreglo de especies del mundo e intenta
	 * comerse a las plantas de TikiBots
	 * </p>
	 */
	private void buscarComida() {
		synchronized (this.ecosistema.getPlantas()) {
		List<PlantaAbstracta> all = Mundo.ObtenerInstancia().getPlantas();
		ListIterator<PlantaAbstracta> iterador = all.listIterator();
		boolean encontro = false;
		//System.out.println(all.size());
		
		while (iterador.hasNext()) {
			PlantaAbstracta planta = iterador.next();
			if (planta instanceof PlantaMala) {
				PlantaMala pmala =(PlantaMala) planta;
				float distancia = app.dist( x, y, pmala.getX(), pmala.getY());
				if (distancia < energia) {
					encontro = true;
					plantaCerca = planta;
					cambiarDireccion(new PVector(pmala.getX(), pmala.getY()));
				
				}	
			} else if (planta instanceof PlantaBuena) {
				PlantaBuena pbuena =(PlantaBuena) planta;
				float distancia = app.dist( x, y, pbuena.getX(), pbuena.getY());
				if (distancia < energia) {
					encontro = true;
					plantaCerca = planta;
					cambiarDireccion(new PVector(pbuena.getX(), pbuena.getY()));
				
				}	
			}
		
		}
		
		
		
		if (!encontro) {
			plantaCerca = null;
		}
		}
	}
	
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}

	@Override
	public String toString() {
		return "EspecieBlanca [id=" + id + ", vida=" + vida + ", fuerza=" + fuerza + ", parejaCercana="
				+ ", dir=" + dir + ", x=" + x + ", y=" + y + ", estado=" + estado + "]";
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO implementar metodo

		return false;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
	// TODO Auto-generated method stub
		if(victima.recibirDano(this)){
			try {
				if(victima.getClass() == PlantaMala.class){
					PlantaMala m = (PlantaMala) victima;
					fuerza -=20;
					defensa -=20;
					vida -=20;
					velocidad -=0.01;
					
					animonmala=true;
					animonbuena=false;
					
					switch (vida) {
						case 100:
							setEstado(NORMAL);
							
							break;
						case 80:
							setEstado(ENFERMO);
							break;
						case 60:
							setEstado(EXTASIS);
							break;
						case 40:
							setEstado(MUERTO);
							break;
					}
				}
				
				if(victima.getClass() == PlantaBuena.class){
					PlantaBuena m = (PlantaBuena) victima;
				
					
					animonbuena =true;
					animonmala=false;
					fuerza +=20;
					defensa +=20;
					velocidad += 0.05;
					
					if (vida<=80){
					vida +=20;
					}	
					
					switch (vida) {
					case 100:
						setEstado(NORMAL);
						
						break;
					case 80:
						setEstado(ENFERMO);
						break;
					case 60:
						setEstado(EXTASIS);
						break;
					case 40:
						setEstado(MUERTO);
						break;
				}
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mundo.ObtenerInstancia().getPlantas().remove(victima);
			this.ecosistema.getPlantas().remove(victima);
			energia+=5;
		}
	}
}
