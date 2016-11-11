package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.ejecucion.Mundo;

public class Apareable extends EspecieAbstracta implements IApareable {
private int vida, velocidad, ciclo;
private float fuerza, energia;
private EspecieAbstracta parejaCercana;
private PVector dir;
private Random random;
private PImage original;


public Apareable(EcosistemaAbstracto ecosistema) {
super(ecosistema);
this.random= new Random();
this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
this.y= random.nextInt(Mundo.ObtenerInstancia().getApp().height);
this.vida= 50;
this.fuerza=100;
this.energia= 200;
this.velocidad=2;

int targetX= random.nextInt();
int targetY= random.nextInt();
ciclo=0;
Thread th= new Thread();



}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		while (vida>0) {
			mover();
			
			try {
				Thread.sleep(33);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}

	@Override
	public void dibujar() {
		PApplet app= Mundo.ObtenerInstancia().getApp();
		app.image(original, x, y);
		}

	@Override
	public void mover() {
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
