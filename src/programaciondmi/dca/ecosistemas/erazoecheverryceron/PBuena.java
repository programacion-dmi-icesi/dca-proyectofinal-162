package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PApplet;
import processing.core.PImage;

public class PBuena extends PlantaPapu {

	private PApplet app;
	private PImage plantaBuena;
	
	public PBuena() {
	
		app = EcosistemaPapus.app;
		plantaBuena = CargaDatos.PBuena;
		
	}
	
	public void pintar() {
		
		app.image(plantaBuena, x, y);
		
	}
}
