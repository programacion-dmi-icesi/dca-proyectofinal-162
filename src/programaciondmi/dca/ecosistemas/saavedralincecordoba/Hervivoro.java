package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;

public class Hervivoro extends EspecieAbstracta implements IHerbivoro {

	public Hervivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

}
