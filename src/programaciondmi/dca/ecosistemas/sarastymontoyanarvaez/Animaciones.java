package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;

public class Animaciones {
	float posX, posY;
	PApplet app;
	PImage imagen;
	int desvanecer = 255;
	int img;

	// clase encargada de controlar las animaciones que se muestran al morir los
	// personajes, dependiendo del entero que se le envia en la variable img
	// se despliega un objeto con una imaegn diferente
	// 4 tipos de imagenes para los cuatro personajes
	public Animaciones(PApplet app, float posX, float posY, int img) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.img = img;

		if (img == 1) {
			imagen = app.loadImage("../img/herv_dead.png");
		}
		if (img == 2) {
			imagen = app.loadImage("../img/carn_dead.png");
		}
		if (img == 3) {
			imagen = app.loadImage("../img/rep_dead.png");
		}
		if (img == 4) {
			imagen = app.loadImage("../img/come_dead.png");
		}
	}

	// metodo encargado de pintar la imagen en el lienzo
	public void pintar() {
		//la variable desvanecer controla la opacidad del objeto
		//con esta variable se crea el efecto de desaparecer
		app.tint(255, 255, 255, desvanecer);
		app.image(imagen, posX, posY);
		app.noTint();
	}

	// se mueve la imagen hacia arriba y se resta en la variable desvanecer
	public void mover() {
		posY--;
		desvanecer -= 5;
	}

	public int getDesvanecer() {
		return desvanecer;
	}

}