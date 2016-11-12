package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.EcosistemaEjemplo;

public class LogoGomiCabra extends LogoAbstracto {
	
	private boolean crearB, crearM;

	public LogoGomiCabra(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub	
		EcosistemaGomiCabra eco = (EcosistemaGomiCabra) ecosistema;
		System.out.println("Hizo click el ecosistema"+eco);
		
	}

}
