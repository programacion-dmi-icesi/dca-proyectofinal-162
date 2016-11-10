package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PBuena extends PlantaPapu {

	
	public PBuena() {
	
		image = CargaDatos.PBuena;
		
	}
	
	public void dibujar() {
		
		app.image(image, x, y);
		
	}
}
