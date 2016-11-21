package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;

public class AmidHijo extends EspecieAbstracta implements IApareable, ICarnivoro{
	
	PApplet app;
	private int vida;
	
	public AmidHijo(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
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
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
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