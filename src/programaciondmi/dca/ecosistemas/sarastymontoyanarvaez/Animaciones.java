package programaciondmi.dca.ecosistemas.sarastymontoyanarvaez;

import processing.core.PApplet;
import processing.core.PImage;

public class Animaciones {
	float posX, posY;
	PApplet app;
	PImage imagen;
	int desvanecer = 255;
	int img;

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

	public void pintar() {
		app.tint(255, 255, 255, desvanecer);
		app.image(imagen, posX, posY);
		app.noTint();
	}

	public void mover() {
		posY--;
		desvanecer -= 5;
	}

	public int getDesvanecer() {
		return desvanecer;
	}

}