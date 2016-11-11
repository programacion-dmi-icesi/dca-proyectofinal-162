package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PMala extends PlantaPapu {

	
	public PMala() {
		image = CargaDatos.PMala;
//		oWith = image.width;
//		oHeight = image.height;
		
	}
	
	public PMala(int x, int y) {
		super(x, y);
		image = CargaDatos.PMala;
//		oWith = image.width;
//		oHeight = image.height;
		
	}
	
	public void dibujar() {
		
		app.image(image, x, y); 
		
	}
	


}
