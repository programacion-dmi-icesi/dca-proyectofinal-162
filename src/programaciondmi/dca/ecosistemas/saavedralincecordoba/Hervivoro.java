package programaciondmi.dca.ecosistemas.saavedralincecordoba;

import processing.core.PVector;

import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import programaciondmi.dca.core.EcosistemaAbstracto;
import programaciondmi.dca.core.EspecieAbstracta;
import programaciondmi.dca.core.PlantaAbstracta;
import programaciondmi.dca.core.interfaces.IHerbivoro;
import programaciondmi.dca.ejecucion.Mundo;

public class Hervivoro extends EspecieAbstracta implements IHerbivoro {
	private static final int CENTER = 0;
	private static final int CORNER = 0;
	private int vida;
	private float fuerza;
	private int velocidad;
	private PImage[] estados = new PImage[48];
	private float energia;
	private EspecieAbstracta parejaCercana;
	private PVector dir;
	private int ciclo;
	private final int LIMITE_APAREO = 100;
	private Random random;
	private int prueba=0;

	public Hervivoro(EcosistemaAbstracto ecosistema) {
		super(ecosistema);
		PApplet app = Mundo.ObtenerInstancia().getApp();
		this.vida = 60;
		this.velocidad = 5;
		estados[0] = app.loadImage("../data/frente_0.png");
		estados[1] = app.loadImage("../data/frente_1.png");
		estados[2] = app.loadImage("../data/frente_2.png");
		estados[3] = app.loadImage("../data/frente_3.png");
		estados[4] = app.loadImage("../data/frente_4.png");
		estados[5] = app.loadImage("../data/frente_5.png");
		estados[6] = app.loadImage("../data/frente_6.png");
		estados[7] = app.loadImage("../data/frente_7.png");
		estados[8] = app.loadImage("../data/frente_8.png");
		estados[9] = app.loadImage("../data/frente_9.png");
		estados[10] = app.loadImage("../data/frente_10.png");
		estados[11] = app.loadImage("../data/frente_11.png");
		estados[12] = app.loadImage("../data/frente_12.png");
		estados[13] = app.loadImage("../data/l2#]_0.png");
		estados[14] = app.loadImage("../data/l2#]_1.png");
		estados[15] = app.loadImage("../data/l2#]_2.png");
		estados[16] = app.loadImage("../data/l2#]_3.png");
		estados[17] = app.loadImage("../data/l2#]_4.png");
		estados[18] = app.loadImage("../data/l2#]_5.png");
		estados[19] = app.loadImage("../data/l2#]_6.png");
		estados[20] = app.loadImage("../data/l2#]_7.png");
		estados[21] = app.loadImage("../data/l2#]_8.png");
		estados[22] = app.loadImage("../data/l2#]_9.png");
		estados[23] = app.loadImage("../data/l2#]_10.png");
		estados[24] = app.loadImage("../data/l2#]_11.png");
		estados[25] = app.loadImage("../data/l2#]_12.png");
		estados[26] = app.loadImage("../data/l1_0.png");
		estados[27] = app.loadImage("../data/l1_1.png");
		estados[28] = app.loadImage("../data/l1_2.png");
		estados[29] = app.loadImage("../data/l1_3.png");
		estados[30] = app.loadImage("../data/l1_4.png");
		estados[31] = app.loadImage("../data/l1_5.png");
		estados[32] = app.loadImage("../data/l1_6.png");
		estados[33] = app.loadImage("../data/l1_7.png");
		estados[34] = app.loadImage("../data/l1_8.png");
		estados[35] = app.loadImage("../data/l1_9.png");
		estados[36] = app.loadImage("../data/l1_10.png");
		estados[37] = app.loadImage("../data/l1_11.png");
		estados[38] = app.loadImage("../data/l1_12.png");
		Thread nt = new Thread(this);
		nt.start();

	}

	@Override
	public void run() {
		while (vida > 0) {
			mover();
			try {
				Thread.sleep(33);
				ciclo++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public void comerPlanta(PlantaAbstracta victima) {
		if(victima.recibirDano(this)){
			try {
				
				if(victima.getClass() == PlantaMala.class){
					PlantaMala m = (PlantaMala) victima;
				setEstado(ENFERMO);
				velocidad=5;
				}
				
				if(victima.getClass() == PlantaBuena.class){
					PlantaBuena b = (PlantaBuena) victima;
					setEstado(EXTASIS);
					velocidad=10;
				}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void dibujar() {
		PApplet app = Mundo.ObtenerInstancia().getApp();
		app.imageMode(CENTER);
		app.image(estados[estado], x, y);
		app.imageMode(CORNER);

	}

	@Override
	public void mover() {
		if (ciclo % 30 == 0) {
			// Definir una direccion aleatoria cada 3 segundos
			int targetX = (int) (Math.random() * 500);
			int targetY = (int) (Math.random() * 500);
			
			cambiarDireccion(new PVector(targetX, targetY));
			cambioImagen(targetX, targetY);
			//System.out.println("CAMBIO DIRECCION!");
		}
		
		x+=dir.x;
		y+=dir.y;
		
		buscarComida();

	}
	//cambio de imagenes del personaje para simular movimiento 
	public void cambioImagen(int targetX,int targetY){
		//ABAJO
		if(targetY>0){
			PApplet app= Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(estados[0], x, y);
			estado++;
			System.out.println(estado);
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
		}
		if(targetY<0){
			PApplet app= Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(estados[estado], x, y);
			estado++;
			System.out.println(estado);
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
		}
//		if(targetX>0){
//			PApplet app= Mundo.ObtenerInstancia().getApp();
//			app.imageMode(CENTER);
//			app.image(estados[26], x, y);
//			estado=26;
//			estado++;
//			System.out.println(estado);
//			if(estado>=38){
//				estado=26;
//				estado++;
//			}
//			app.imageMode(CORNER);
//		}
		if(targetX<0){
			PApplet app= Mundo.ObtenerInstancia().getApp();
			app.imageMode(CENTER);
			app.image(estados[estado], x, y);
			estado++;
			System.out.println(estado);
			if(estado>=12){
				estado=0;
				estado++;
			}
			app.imageMode(CORNER);
		}

		
	}
	private void cambiarDireccion(PVector target) {
		PVector location = new PVector(x, y);
		dir = PVector.sub(target, location);
		dir.normalize();
		dir.mult(velocidad);
		//System.out.println("[id=" + id + ", direcion=" + dir + "]");
	}
	
	public void buscarComida() {
		List<PlantaAbstracta> plants= Mundo.ObtenerInstancia().getPlantas();
		for (int i = 0; i < plants.size(); i++) {
			comerPlanta(plants.get(i));
	
			
		}
		

	}

	@Override
	public boolean recibirDano(EspecieAbstracta lastimador) {
		// TODO Auto-generated method stub
		return false;
	}

}

