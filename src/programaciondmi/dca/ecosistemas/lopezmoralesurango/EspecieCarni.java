package programaciondmi.dca.ecosistemas.lopezmoralesurango;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieCarni extends EspecieAbstracta implements ICarnivoro{
	PApplet app = Mundo.ObtenerInstancia().getApp();
	private PImage Carnivoro;
	private int vida;
	private int velocidad;
	
	private float energia;
	private PVector dir;
	private int ciclo;
	
	private Random random;

	public EspecieCarni(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		Carnivoro=app.loadImage("carni.png");
		this.vida=20;
		this.energia=250;
		this.velocidad=2;
		
		int targetX = random.nextInt();
		int targetY = random.nextInt();
		cambiarDireccion(new PVector(targetX, targetY));
		
		ciclo=0;
		
		Thread hcr = new Thread(this);
		hcr.start();
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
	public void comer(EspecieAbstracta victima) {
		if (!victima.getClass().toString().equals(this.getClass().toString())) {
			if(victima.recibirDano(this)){
				energia+=5;
			}
		}	
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.image(Carnivoro, x+100, y+200);
		
	}

	@Override
	public void mover() {
		if (energia > 0) {
					buscarComida();
					if (ciclo % 30 == 0) {
					// Definir una direccion aleatoria cada 3 segundos
					int targetX = random.nextInt();
					int targetY = random.nextInt();
					cambiarDireccion(new PVector(targetX, targetY));
//					System.out.println("CAMBIO DIRECCION!");
					
				}
				
			// moverse en la direcciÃ³n asignada actualmente
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
	
	public void buscarComida(){
		List<EspecieAbstracta> todas = Mundo.ObtenerInstancia().getEspecies();
		for (int i = 0; i < todas.size(); i++) {
			comer(todas.get(i));
		}
	}
	
	public void cambiarDireccion(PVector target){
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}