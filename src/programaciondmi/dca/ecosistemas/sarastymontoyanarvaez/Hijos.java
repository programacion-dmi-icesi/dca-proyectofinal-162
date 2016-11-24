package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class Hijos extends EspecieAbstracta {

	PApplet app = Mundo.ObtenerInstancia().getApp();
	private PImage bby1, bby2, bby3, bby4, bby5;
	private int imagenAleatoria;// variable para controlar un hijo con aspecto
								// diferente de manera aleatoria

	public Hijos(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
		imagenAleatoria = (int) app.random(1, 5);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	// metodo encargado de pintar las imagenes de las crias de los personajes
	// apareables
	// aleatoriamente se pintan de diferentes maneras
	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		bby1 = app.loadImage("../img/bby1.png");
		bby2 = app.loadImage("../img/bby2.png");
		bby3 = app.loadImage("../img/bby3.png");
		bby4 = app.loadImage("../img/bby4.png");
		bby5 = app.loadImage("../img/bby5.png");

		switch (imagenAleatoria) {
		case 1:
			app.image(bby1, x, y);
			break;

		case 2:
			app.image(bby2, x, y);
			break;

		case 3:
			app.image(bby3, x, y);
			break;

		case 4:
			app.image(bby4, x, y);
			break;

		case 5:
			app.image(bby5, x, y);
			break;
		}

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