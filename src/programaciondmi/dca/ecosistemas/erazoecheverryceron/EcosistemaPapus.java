package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Ejecutable;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaPapus extends EcosistemaAbstracto {
// Para utilizar el app de forma rapida en cualquier clase y que no haya sido llamado se debe escribir app = EcosistemaPapu.app;
	public static PApplet app;
	private Mundo mundo;
	private CargaDatos datos;
	public EcosistemaPapus() {
		super();
		mundo = Mundo.ObtenerInstancia();
		app = mundo.getApp();
		datos = new CargaDatos();
	}

	@Override
	public void dibujar() {
		app.ellipse(50, 50, 50, 50);
		
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		// TODO Auto-generated method stub
		return null;
	}

}
