package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.ejecucion.Ejecutable;
import programaciondmi.dca.ejecucion.Mundo;

public class EcosistemaPapus extends EcosistemaAbstracto {
	// Para utilizar el app de forma rapida en cualquier clase y que no haya
	// sido llamado se debe escribir app = EcosistemaPapu.app;
	public static PApplet app;
	private CargaDatos datos;
	private LinkedList<PlantaAbstracta> plantasIniciales;

	public EcosistemaPapus() {
		app = Mundo.ObtenerInstancia().getApp();
		datos = new CargaDatos();
		poblarPlantas();
	}

	@Override
	public void dibujar() {
		for (PlantaAbstracta planta : plantasIniciales) {
			planta.dibujar();
		}
		
		botones();
	}

	@Override
	protected LinkedList<EspecieAbstracta> poblarEspecies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LinkedList<PlantaAbstracta> poblarPlantas() {
		plantasIniciales = new LinkedList<PlantaAbstracta>();
		for (int i = 0; i < 3; i++) {
				plantasIniciales.add(new PMala());
				plantasIniciales.add(new PBuena());
			
		}
		return plantasIniciales;
	}

	@Override
	protected List<EspecieAbstracta> generarIndividuos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<PlantaAbstracta> generarPlantas() {
		// TODO Auto-generated met hod stub
		return null;
	}
	
	private void botones() {
		PImage buena = CargaDatos.botonPlantaBuena;
		PImage mala = CargaDatos.botonPlantaMala;

		app.image(buena, 0, 0);
		app.image(mala, -150, 0);

	}

	public static boolean validar(float XUno, float YUno, float XDos, float YDos, float distancia) {
		if (PApplet.dist(XUno, YUno, XDos, YDos) <= distancia)
			return true;
		return false;
	}

}
