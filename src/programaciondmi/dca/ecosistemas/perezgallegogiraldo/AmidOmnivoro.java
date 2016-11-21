package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IOmnivoro;

public class AmidOmnivoro extends EspecieAbstracta implements IOmnivoro{
	
	PApplet app;
	private int vida;
	
	public AmidOmnivoro(EcosistemaAbstracto ecosistema){
		super(ecosistema);
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(vida>0){
			mover();
			try{
				Thread.sleep(33);
			}catch(Exception e){
				
			}
		}		
	}

	@Override
	public void dibujar() {
		app.fill(0,255,255);
		app.ellipse(100, 100, 100, 100);		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}