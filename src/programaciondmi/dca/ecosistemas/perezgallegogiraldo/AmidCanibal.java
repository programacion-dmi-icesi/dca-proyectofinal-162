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
		
		cargaImagenes();
		Thread nt = new Thread(this);
		nt.start();
	}

	public void cargaImagenes() {

		// FRENTE ENFERMO
		for (int i = 0; i < 12; i++) {
			
				frenteEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Enfermo" + i + ".png");

				frenteSano[i] = app.loadImage("../data/Personajes/P1/P1 Frente/P1 F Sano" + i + ".png");

				espaldaEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Enfermo" + i + ".png");

				espaldaSano[i] = app.loadImage("../data/Personajes/P1/P1 Espalda/P1 E Sano" + i + ".png");

				ladoEnfermo[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Enfermo" + i + ".png");

				ladoSano[i] = app.loadImage("../data/Personajes/P1/P1 Lado/P1 L Sano" + i + ".png");

				transicionFrente[i] = app.loadImage("../data/Personajes/P1/Transiciones/Frente/Transicion P1 F" + i + ".png");

				transicionEspalda[i] = app.loadImage("../data/Personajes/P1/Transiciones/Espalda/Transicion P1 E" + i + ".png");

				transicionLado[i] = app.loadImage("../data/Personajes/P1/Transiciones/Lado/Transicion P1 L" + i + ".png");
		}

		// FRENTE SANO

		// ESPALDA ENFERMO

		// ESPALDA SANO

		// LADO ENFERMO

		// LADO SANO

		// TRANSICIÓN FRENTE

		// TRANSICIÓN ESPALDA

		// TRANSICIÓN LADO
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		app.image(frenteEnfermo[0], 0, 0);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub

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