package programaciondmi.dca.ecosistemas.erazoecheverryceron;

public class PMala extends PlantaPapu {

	
	public PMala() {
		image = CargaDatos.PMala;
//		oWith = image.width;
//		oHeight = image.height;
		
		Thread plantita = new Thread(this);
		plantita.start();
	}
	
	public void dibujar() {
		
		app.image(image, x, y); 
		
	}
	


}
