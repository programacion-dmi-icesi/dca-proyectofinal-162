package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PBuena extends PlantaPapu {

	
	public PBuena() {
		image = CargaDatos.PBuena;
//		oWith = image.width;
//		oHeight = image.height;
		
	}
	
	public PBuena(int x, int y) {
		super(x, y);
		image = CargaDatos.PBuena;
//		oWith = image.width;
//		oHeight = image.height;
		
	}
	
	public void dibujar() {
		
		app.image(image, x, y);
		
	}

}
