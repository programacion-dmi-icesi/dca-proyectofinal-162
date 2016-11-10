package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PMala extends PlantaPapu {

	
	public PMala() {

		image = CargaDatos.PMala;
		
	}
	
	public void dibujar() {
		
		app.image(image, x, y); 
		
	}
}
