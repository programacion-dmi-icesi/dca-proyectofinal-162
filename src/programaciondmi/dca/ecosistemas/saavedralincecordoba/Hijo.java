package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hijo extends EspecieAbstracta {
	//private PImage son;

	public Hijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app= Mundo.ObtenerInstancia().getApp();
		//son= app.loadImage("../data/son.png");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		PApplet app= Mundo.ObtenerInstancia().getApp();
		//app.image(son,0,0);
		
		app.fill(0);
		app.ellipse(x, y, 100, 100);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
