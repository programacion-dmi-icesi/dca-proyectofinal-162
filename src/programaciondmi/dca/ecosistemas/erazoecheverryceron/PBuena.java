package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PBuena extends PlantaPapu {

	
	public PBuena() {
		image = CargaDatos.PBuena;
//		oWith = image.width;
//		oHeight = image.height;
		
		Thread plantita = new Thread(this);
		plantita.start();
	}
	
	public void dibujar() {
		
		app.image(image, x, y);
		
	}

}
