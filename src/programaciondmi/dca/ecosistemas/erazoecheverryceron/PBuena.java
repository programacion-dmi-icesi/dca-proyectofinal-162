package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PApplet;
import processing.core.PImage;

public class PBuena extends PlantaPapu {

	private PImage plantaBuena;
	
	public PBuena() {
	
		plantaBuena = CargaDatos.PBuena;
		
	}
	
	public void dibujar() {
		
		app.image(plantaBuena, x, y);
		
	}
}
