package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PApplet;
import processing.core.PImage;

public class CargaDatos {

	private PApplet app;
	public static PImage PBuena;
	public static PImage PMala;

	public CargaDatos() {
		app = EcosistemaPapus.app;
		PBuena = app.loadImage("../data/plantaBuena.png");
		PMala = app.loadImage("../data/plantaMala.png");

	}
}
