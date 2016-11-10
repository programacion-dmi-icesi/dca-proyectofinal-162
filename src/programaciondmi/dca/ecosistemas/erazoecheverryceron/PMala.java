package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import processing.core.PImage;

public class PMala extends PlantaPapu {

	private PImage plantaMala;
	
	public PMala() {

		plantaMala = CargaDatos.PMala;
		
	}
	
	public void dibujar() {
		
		app.image(plantaMala, x, y); 
		
	}
}
