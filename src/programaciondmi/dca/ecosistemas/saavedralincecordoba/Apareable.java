package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import java.util.List;
import java.util.Random;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Apareable extends EspecieAbstracta implements  IApareable, IOmnivoro{
	private static final int CENTER = 0;
	private static final int CORNER = 0;
	private int vida;
	private float fuerza;
	private int velocidad;
	private PImage [] estados = new PImage[5] ;
	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;
	

	
	
	public Apareable(EcosistemaAbstracto ecosistema) {
		
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.vida = 40;
		this.velocidad = 5;
        estados[0]=app.loadImage("../data/original.png");
        estados[1]=app.loadImage("../data/2.png");
        estados[2]=app.loadImage("../data/3.png");
        estados[3]=app.loadImage("../data/1.png");
        //creo provicionalmente, ahora la cmabio//
		estados[4]=app.loadImage("../data/2.png");
		
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
					if (victima.getClass()==PlantaBuena.class) {
						PlantaBuena b = (PlantaBuena)victima;
						setEstado(EXTASIS);
						velocidad = 10;
						b.setMostrar(false);
						System.out.println("no error");
					//	Mundo.ObtenerInstancia().getPlantas().remove(victima);
					}
					
					
					if (victima.getClass()==PlantaMala.class) {
						setEstado(ENFERMO);
						velocidad = 8;
						//Mundo.ObtenerInstancia().getPlantas().remove(victima);
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
		app.imageMode(CORNER);
       
		
	}
	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
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
		
		buscarComida();
		
		
	}
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
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
					if(victima.getClass()==Canibal.class){
						// camila
					}
					
					if (victima.getClass()== Hervivoro.class) {
						
					}
					
					if (victima.getClass()== Carnivoro.class) {
						
					}
					
					if (victima.getClass()== Apareable.class) {
						
					}
				Mundo.ObtenerInstancia().getEspecies().remove(victima);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	}
	
	public void buscarComida() {
	
		List<EspecieAbstracta> todas= Mundo.ObtenerInstancia().getEspecies();
		List<PlantaAbstracta> plants= Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
	
			
		}
		for (int i = 0; i < plants.size(); i++) {
			comerPlanta(plants.get(i));
	
			
		}

		

	}
}