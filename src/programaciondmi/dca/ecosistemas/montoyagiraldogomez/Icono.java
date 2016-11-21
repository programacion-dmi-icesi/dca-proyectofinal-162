package programaciondmi.dca.ecosistemas.montoyagiraldogomez;

import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;

public class Icono extends LogoAbstracto {

	public Icono(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		if (ecosistema instanceof EcosistemaProphetics) {
			EcosistemaProphetics eco = (EcosistemaProphetics) ecosistema;
			eco.setColocar(true);
		}
	}
}
