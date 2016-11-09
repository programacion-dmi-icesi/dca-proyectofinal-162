package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PApplet;
import processing.core.PImage;

public class PMala extends PlantaPapu {

	private PApplet app;
	private PImage plantaMala;
	
	public PMala() {

		app = EcosistemaPapus.app;
		plantaMala = CargaDatos.PMala;
		
	}
	
	public void dibujar() {
		
		app.image(plantaMala, x, y);
		
	}
}
