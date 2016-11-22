package programaciondmi.dca.ecosistemas.perezgallegogiraldo;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.EcosistemaEjemplo;

public class LogoAmid extends LogoAbstracto{

	public LogoAmid(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		EcosistemaAmid eco = (EcosistemaAmid) ecosistema;

	}
}
