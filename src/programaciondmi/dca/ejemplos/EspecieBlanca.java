package programaciondmi.dca.ejemplos;

import processing.core.PApplet;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.ICarnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class EspecieBlanca extends EspecieAbstracta implements IApareable,
		ICarnivoro {
	int vida;
	int fuerza;
	
	public EspecieBlanca() {
		this.x = 100;
		this.y = 100;
		this.vida = 100;
		this.fuerza = 100;
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
		// TODO Auto-generated method stub
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.fill(255);
		app.ellipse(x, y, vida, vida);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		this.x+= 1;
		this.y+= 1;
	}

	@Override
	public void run() {
		while(vida>0){
			mover();			
		}
	}

}
