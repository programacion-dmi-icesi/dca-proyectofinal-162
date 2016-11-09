package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PApplet;
import processing.core.PImage;

public class PMala extends PlantaPapu {

	private PApplet app;
	private PImage plantaMala;
	
	public PMala() {

		
		plantaMala = CargaDatos.PMala;
		
	}
	
	public void pintar() {
		app.image(plantaMala, 50, 50);
		
	}
}
