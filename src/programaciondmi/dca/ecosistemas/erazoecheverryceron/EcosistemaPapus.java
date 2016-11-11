package programaciondmi.dca.ecosistemas.erazoecheverryceron;

import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
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
	private int camX;
	private int camY;
	private LinkedList<PlantaAbstracta> plantasIniciales;
	private LinkedList<PlantaAbstracta> agregarPlantas;

	public EcosistemaPapus() {
		app = Mundo.ObtenerInstancia().getApp();
		datos = new CargaDatos();
		app.imageMode(PConstants.CENTER);
		agregarPlantas = new LinkedList<PlantaAbstracta>();
		poblarPlantas();
		CargaHilosPrimeros();
	}

	private void CargaHilosPrimeros() {
		for (PlantaAbstracta plantaAbstracta : plantasIniciales) {
			Thread plantita = new Thread(plantaAbstracta);
			plantita.start();
		}
	}
	
	 private void CargaHIlosSegundos(){
			for (PlantaAbstracta plantaAbstracta : agregarPlantas) {
				Thread plantita = new Thread(plantaAbstracta);
				plantita.start();
			}


	 }

	@Override
	public void dibujar() {
		for (PlantaAbstracta planta : plantasIniciales) {
			planta.dibujar();
		}
		for (PlantaAbstracta planta : agregarPlantas) {
			planta.dibujar();
		}
		generarPlantas();
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
		if (app.mousePressed) {
			antiCamMov();			
			agregarPlantas.add(new PBuena(app.mouseX-camX,app.mouseY-camY));
		}
		return agregarPlantas;
	}

	private void antiCamMov() {

		if (app.mouseX < app.width / 4) {
			camX--;
		}
		if (app.mouseX > 3 * app.width / 4) {
			camX++;

		}
		if (app.mouseY < app.height / 4) {
			camY--;

		}
		if (app.mouseX < 3 * app.height / 4) {
			camY++;

		}
	}

	private void crearPlantas(){
		if (app.mousePressed) {
			System.out.println("entra");
			agregarPlantas.add(new PBuena(app.mouseX,app.mouseY));
		}
	}
	private void botones() {
		PImage buena = CargaDatos.botonPlantaBuena;
		PImage mala = CargaDatos.botonPlantaMala;
		antiCamMov();
		int desfasesX = 225;
		float desfaceY = app.height / 3;
		int pMalaX = camX - desfasesX;
		float pMalaY = camY + desfaceY;
		int pBuenaX = camX + desfasesX;
		float pBuenaY = camY + desfaceY;
		app.image(buena, pBuenaX, pBuenaY);
		app.image(mala, pMalaX, pMalaY);
		app.line(pBuenaX, pBuenaY, (app.mouseX/2)-camX, (app.mouseY/2)-camY);
	}

	public static boolean validar(float XUno, float YUno, float XDos, float YDos, float distancia) {
		if (PApplet.dist(XUno, YUno, XDos, YDos) <= distancia)
			return true;
		return false;
	}

}