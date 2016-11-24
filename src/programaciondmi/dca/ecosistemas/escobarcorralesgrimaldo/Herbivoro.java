package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import processing.core.*;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.HijoBlanco;
import programaciondmi.dca.ejecucion.Mundo;

public class Herbivoro extends EspecieAbstracta implements IHerbivoro, IApareable {

	private int vida, velocidad;
	private PVector dir;
	private PImage[] herbi = new PImage[5];
	private PImage[] herbiVistas = new PImage[4];

	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float fuerza;
	private float energia;
	private EspecieAbstracta parejaCercana;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	private int ciclo;

	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;

	private int xP, yP, vX, vY, vXD = 50, vYD = 50;

	
	public Herbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 20;
		this.velocidad = 2;
		this.fuerza = 100;
		this.energia = 250;

		PApplet app = Mundo.ObtenerInstancia().getApp();
		ballX = (int) app.random(0, app.width);
		ballY = (int) app.random(0, app.height);
		// Imagenes
		// PApplet app = Mundo.ObtenerInstancia().getApp();

		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));

		ciclo = 0;
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(20);
				ciclo++;
				xP += vX;
				yP += vY;

				if ((xP <= 5) || (xP >= 1000)) {
					vX *= -1;
				}
				if ((yP <= 5) || (yP >= 1000)) {
					vY *= -1;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		herbi[0] = app.loadImage("../data/HerbivoroUno.png");
		herbi[1] = app.loadImage("../data/HerbivoroDos.png");
		herbi[2] = app.loadImage("../data/HerbivoroTres.png");
		herbi[3] = app.loadImage("../data/HerbivoroCuatro.png");
		herbi[4] = app.loadImage("../data/HerbivoroCinco.png");
		
		if(vida==20){
			app.image(herbi[0], ballX, ballY);
		}
		if(vida==15){
			app.image(herbi[1], ballX, ballY);
		}
		if(vida==10){
			app.image(herbi[2], ballX, ballY);
		}
		if(vida==5){
			app.image(herbi[3], ballX, ballY);
		}
		if(vida==0){
			app.image(herbi[4], ballX, ballY);
		}

	}

	@Override
	public void mover() {
	   int vel=5;
		if (ciclo % vel == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();
			
			herbiVistas[0]= app.loadImage("../data/vistas/Herbi_aba.png");
			herbiVistas[1]= app.loadImage("../data/vistas/Herbi_arri.png");
			herbiVistas[2]= app.loadImage("../data/vistas/Herbi_izq.png");
			herbiVistas[3]= app.loadImage("../data/vistas/Herbi_der.png");

			ballX = (float) (ballX + 10.8 * ballXDirection);
			ballY = (float) (ballY + 8.8 * ballYDirection);

			if (ballX > app.width - 25 || ballX < 25) {
				ballXDirection *= -1;
			}
			if (ballY > app.height - 25 || ballY < 25) {
				ballYDirection *= -1;
			}

		}
				
		/*	// moverse en la dirección asignada actualmente
			this.x += dir.x;
			this.y += dir.y;
			energia -= 0.01;
		}
		
		if(this.x > Mundo.ObtenerInstancia().getApp().width || this.x < 0){
			this.dir.x*=-1;			
		}
		if(this.y > Mundo.ObtenerInstancia().getApp().height || this.y < 0){
			this.dir.y *= -1;			
		}*/
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		if(PApplet.dist(x, y, lastimador.getX(), lastimador.getY()) < (500)){
			vida -= 5;
			try {
				if(vida>=20){
					lastimador.setEstado(NORMAL);
				}
				if(vida>=15 && vida < 20){
					lastimador.setEstado(ENVENENADO);
				}
				if(vida>=10 && vida < 15){
					lastimador.setEstado(ENFERMO);
				}
				if(vida>=5 && vida < 10){
					lastimador.setEstado(EXTASIS);
				}
				if(vida>=0 && vida < 5){
					lastimador.setEstado(MUERTO);
				
				}
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

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		Hijo hijo = new Hijo(ecosistema);
		hijo.setX(this.x);
		hijo.setY(this.y);
		ecosistema.agregarEspecie(hijo);
		return hijo;
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
				float dist = PApplet.dist(ballX, ballY, e.getX(), e.getY());
				// System.out.println("Encontró apareable a " + dist);
				if (dist < energia) {
					// System.out.println("Encontró una pareja cercana");
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
			// System.out.println("No encontró una pareja cercana");
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
	 * comerse a cada una de las especies
	 * </p>
	 */

}
