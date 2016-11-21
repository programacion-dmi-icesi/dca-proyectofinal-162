package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class AmidHerbivoro extends EspecieAbstracta implements IHerbivoro, IApareable {
	PApplet app;
	private int vida;

	private PImage[] frenteEnfermo = new PImage[12];
	private PImage[] frenteSano = new PImage[12];
	private PImage[] espaldaEnfermo = new PImage[12];
	private PImage[] espaldaSano = new PImage[12];
	private PImage[] ladoEnfermo = new PImage[12];
	private PImage[] ladoSano = new PImage[12];
	private PImage[] transicionFrente = new PImage[12];
	private PImage[] transicionEspalda = new PImage[12];
	private PImage[] transicionLado = new PImage[12];

	public AmidHerbivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		app = Mundo.ObtenerInstancia().getApp();
		cargaImagenes();

	}

	public void cargaImagenes() {

		for (int i = 0; i < 12; i++) {

			frenteEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Frente/P4 F Enfermo" + i + ".png"); // FRENTE
																											// ENFERMO

			frenteSano[i] = app.loadImage("../data/Personajes/P4/P4 Frente/P4 F Sano" + i + ".png"); // FRENTE
																										// SANO

			espaldaEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Espalda/P4 E Enfermo" + i + ".png"); // ESPALDA
																												// ENFERMO

			espaldaSano[i] = app.loadImage("../data/Personajes/P4/P4 Espalda/P4 E Sano" + i + ".png"); // ESPALDA
																										// SANO

			ladoEnfermo[i] = app.loadImage("../data/Personajes/P4/P4 Lado/P4 L Enfermo" + i + ".png"); // LADO
																										// ENFERMO

			ladoSano[i] = app.loadImage("../data/Personajes/P4/P4 Lado/P4 L Sano" + i + ".png"); // LADO
																									// SANO
			/*
			transicionFrente[i] = app.loadImage("../data/Personajes/P4/Transiciones/Frente/Transicion P4 F" + i + ".png"); // TRANSICIÓN FRENTE

			transicionEspalda[i] = app.loadImage("../data/Personajes/P4/Transiciones/Espalda/Transicion P4 E" + i + ".png"); // TRANSICIÓN ESPALDA

			transicionLado[i] = app.loadImage("../data/Personajes/P4/Transiciones/Lado/Transicion P4 L" + i + ".png"); // TRANSICIÓN LADO
			*/
		}

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		app.fill(0, 255, 255);
		app.ellipse(100, 100, 100, 100);
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

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub

	}

}