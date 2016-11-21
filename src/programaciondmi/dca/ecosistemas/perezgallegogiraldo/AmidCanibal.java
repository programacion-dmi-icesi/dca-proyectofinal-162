package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import java.util.Random;

import processing.core.PImage;
import processing.core.PVector;
import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.ICanibal;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidCanibal extends EspecieAbstracta implements ICanibal {

	private PImage[] frenteEnfermo = new PImage[12];
	private PImage[] frenteSano = new PImage[12];
	private PImage[] espaldaEnfermo = new PImage[12];
	private PImage[] espaldaSano = new PImage[12];
	private PImage[] ladoEnfermo = new PImage[12];
	private PImage[] ladoSano = new PImage[12];
	private PImage[] transicionFrente = new PImage[12];
	private PImage[] transicionEspalda = new PImage[12];
	private PImage[] transicionLado = new PImage[12];

	private PApplet app;

	private int vida;
	private float fuerza;
	private int velocidad;
	/*
	 * Se utiliza para definfir cuando el individuo puede realizar acciones:
	 * moverse, aparearse, etc
	 */
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private PVector pos;
	private int ciclo;

	// Constantes
	private final int LIMITE_APAREO = 100;
	private Random random;

	public AmidCanibal(EcosistemaAbstracto ecosistema) {

		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();

		this.random = new Random();
		this.x = random.nextInt(Mundo.ObtenerInstancia().getApp().width);
		this.y = random.nextInt(Mundo.ObtenerInstancia().getApp().height);
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;
		
		dir= new PVector(0,0);
		pos = new PVector(0,0);
		
		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {
			
				frenteEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Enfermo" + i + ".png");			// FRENTE ENFERMO

				frenteSano[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Sano" + i + ".png"); 		// FRENTE SANO

				espaldaEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Enfermo" + i + ".png");		// ESPALDA ENFERMO

				espaldaSano[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Sano" + i + ".png");		// ESPALDA SANO

				ladoEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Enfermo" + i + ".png");		// LADO ENFERMO

				ladoSano[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Sano" + i + ".png");		// LADO SANO

				transicionFrente[i] = app.loadImage("../data/Personajes/P1/Transiciones/Frente/Transicion P1 F" + i + ".png");		// TRANSICIÓN FRENTE

				transicionEspalda[i] = app.loadImage("../data/Personajes/P1/Transiciones/Espalda/Transicion P1 E" + i + ".png");		// TRANSICIÓN ESPALDA

				transicionLado[i] = app.loadImage("../data/Personajes/P1/Transiciones/Lado/Transicion P1 L" + i + ".png");		// TRANSICIÓN LADO
		}

	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.ellipse(pos.x, pos.y, 50, 50);
		app.image(frenteEnfermo[0], pos.x-50, pos.y-50);
	}

	@Override
	public void mover() {
		dir = PVector.random2D();
		dir.mult(3);
		pos.add(dir);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
			} catch (Exception e) {

			}
		}

	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}