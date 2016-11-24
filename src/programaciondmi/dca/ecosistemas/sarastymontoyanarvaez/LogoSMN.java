package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PConstants;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;
import programaciondmi.dca.ecosistemas.sarmientomanzanomoncada.EcosistemaEjemplo;
import programaciondmi.dca.ejecucion.Mundo;

public class LogoSMN extends LogoAbstracto {
	PApplet app = Mundo.ObtenerInstancia().getApp();

	public LogoSMN(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
	}

	// metodo encargado de reconocer el click sobre el logo
	// al hacer click se activa el metodo generarPlantas del ecosistemaSMN
	@Override
	public void click() {
		// TODO Auto-generated method stub
		EcosistemaSMN eco = (EcosistemaSMN) ecosistema;
		eco.generarPlantas();
	}

}
