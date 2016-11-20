package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.LogoAbstracto;
import programaciondmi.dca.ejecucion.Mundo;

public class LogoGomiCabra extends LogoAbstracto {

	private boolean crearB, crearM;
	private PApplet app;

	public LogoGomiCabra(String rutaLogo, EcosistemaAbstracto ecosistema) {
		super(rutaLogo, ecosistema);
		// TODO Auto-generated constructor stub
		app = Mundo.ObtenerInstancia().getApp();
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		EcosistemaGomiCabra eco = (EcosistemaGomiCabra) ecosistema;
		System.out.println("Hizo click el ecosistema" + eco);

		if (app.mouseButton == app.LEFT) {
			crearM = true;
			crearB = false;
		}

		if (app.mouseButton == app.RIGHT) {
			crearB = true;
			crearM = false;
		}

	}

	public boolean getCrearB() {
		return crearB;
	}

	public boolean getCrearM() {
		return crearM;
	}

}
