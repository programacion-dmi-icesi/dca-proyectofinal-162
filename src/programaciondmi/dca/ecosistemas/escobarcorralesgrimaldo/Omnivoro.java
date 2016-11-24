package programaciondmi.dca.ecosistemas.escobarcorralesgrimaldo;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IOmnivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Omnivoro extends EspecieAbstracta implements IOmnivoro{

	private int vida, velocidad;
	private int ciclo;

	private float ballX = 50, ballY = 50;
	private int ballXDirection = 1, ballYDirection = -1;
	
	private int direccion;
	
	private PImage omni [] = new PImage [4];

	private int xP, yP, vX, vY, vXD = 50, vYD = 50;
	
	public Omnivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		
		this.vida = 50;
		PApplet app = Mundo.ObtenerInstancia().getApp();
		ballX = (int) app.random(0, app.width);
		ballY = (int) app.random(0, app.height);
		
		Thread nt = new Thread(this);
		nt.start();
	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				synchronized (ecosistema.getEspecies()) {
					for (EspecieAbstracta especie : ecosistema.getEspecies()) {
						if (especie != this) {
							if (especie instanceof Carnivoro || especie instanceof Herbivoro
									 || especie instanceof Hijo || especie instanceof Canibal) {
								float d = PApplet.dist(especie.getX(), especie.getY(), this.ballX, this.ballY);
								// if (!esperar) {
								if (d < 70) {
									comer(especie);
									especie.setEstado(MUERTO);

									System.out.println("omnivoro mata!");
									ecosistema.getEspecies().remove(especie);

									break;
								}
								// }

							}

						}
					}
				}
				Thread.sleep(15);
				ciclo++;
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
	
		omni[0]= app.loadImage("../data/vistas/omni_aba.png");
		omni[1]= app.loadImage("../data/vistas/omni_arri.png");
		omni[2]= app.loadImage("../data/vistas/omni_izq.png");
		omni[3]= app.loadImage("../data/vistas/omni_der.png");
		
		app.image(omni[direccion], ballX, ballY);
		
		if (ballXDirection >= 0) {
			direccion=3;

		}

		if (ballXDirection <= 0) {
			direccion=2;

		}else

		if (ballYDirection >= 0) {
			direccion=0;

		}

		if (ballYDirection <=0) {
			direccion=1;
		}
	}
	
	@Override
	public void mover() {
		if (ciclo % 60 == 0) {
			PApplet app = Mundo.ObtenerInstancia().getApp();

			ballX = (float) (ballX + 10.8 * ballXDirection);
			ballY = (float) (ballY + 8.8 * ballYDirection);

			if (ballX > app.width - 25 || ballX < 25) {
				ballXDirection *= -1;
			}
			if (ballY > app.height - 25 || ballY < 25) {
				ballYDirection *= -1;
			}

		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void comer(EspecieAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		// TODO Auto-generated method stub
		
	}

}
