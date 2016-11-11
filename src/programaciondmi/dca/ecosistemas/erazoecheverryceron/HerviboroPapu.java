package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IApareable;
import programaciondmi.dca.core.interfaces.IHerbivoro;

public class HerviboroPapu extends EspeciePapu implements IHerbivoro, IApareable {

	public HerviboroPapu(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EspecieAbstracta aparear(IApareable apareable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

}
