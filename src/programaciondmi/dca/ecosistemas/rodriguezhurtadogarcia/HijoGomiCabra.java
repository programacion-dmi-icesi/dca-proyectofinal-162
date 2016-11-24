package programaciondmi.dca.ecosistemas.rodriguezhurtadogarcia;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.ejecucion.Mundo;

public class HijoGomiCabra extends GomiCabra {

	public HijoGomiCabra(EcosistemaAbstracto ecosistema, int x, int y) {
		super(ecosistema);
		// TODO Auto-generated constructor stub
		this.random = new Random();
		app = Mundo.ObtenerInstancia().getApp();
		this.x = x;
		this.y = y;
		this.vida = 50;
		this.fuerza = 100;
		this.energia = 250;
		this.velocidad = 2;

		// FRENTE
		frente[0] = app.loadImage("../dataGomiCabra/hijo/hijoFrente/1.png");
		frente[1] = app.loadImage("../dataGomiCabra/hijo/hijoFrente/2.png");
		frente[2] = app.loadImage("../dataGomiCabra/hijo/hijoFrente/3.png");
		frente[3] = app.loadImage("../dataGomiCabra/hijo/hijoFrente/4.png");

		// ATRAS
		atras[0] = app.loadImage("../dataGomiCabra/hijo/hijoAtras/1.png");
		atras[1] = app.loadImage("../dataGomiCabra/hijo/hijoAtras/2.png");
		atras[2] = app.loadImage("../dataGomiCabra/hijo/hijoAtras/3.png");
		atras[3] = app.loadImage("../dataGomiCabra/hijo/hijoAtras/4.png");

		// IZQUIERDA
		izquierda[0] = app.loadImage("../dataGomiCabra/hijo/hijoIzquierda/1.png");
		izquierda[1] = app.loadImage("../dataGomiCabra/hijo/hijoIzquierda/2.png");
		izquierda[2] = app.loadImage("../dataGomiCabra/hijo/hijoIzquierda/3.png");
		izquierda[3] = app.loadImage("../dataGomiCabra/hijo/hijoIzquierda/4.png");

		// DERECHA
		derecha[0] = app.loadImage("../dataGomiCabra/hijo/hijoDerecha/1.png");
		derecha[1] = app.loadImage("../dataGomiCabra/hijo/hijoDerecha/2.png");
		derecha[2] = app.loadImage("../dataGomiCabra/hijo/hijoDerecha/3.png");
		derecha[3] = app.loadImage("../dataGomiCabra/hijo/hijoDerecha/4.png");
		maxVida = vida;
		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (vivo) {
			// solo se mueve por ahí :D
			mover();
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}
