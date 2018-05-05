package model.weaponmodel;

/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AmmoBox {
	// Constants
	float posX, posY, amount;
	static final int WIDTH = 36;
	static final int HEIGHT = 32;
	Image img;

	// Constructor
	public AmmoBox(float x, float y, int amount) throws SlickException {
		this.posX = x;
		this.posY = y;
		this.amount = amount;
		img = new Image("res/Ammobox.png");
	}

	public void setX(float x) {
		this.posX = x;
	}

	public void setY(float y) {
		this.posY = y;
	}

	public float getX() {
		return this.posX;
	}

	public float getY() {
		return this.posY;
	}

	// Draws the image on given location
	public void draw(Graphics g) {
		g.drawImage(this.img, this.posX - WIDTH / 2, this.posY - HEIGHT / 2);
	}

}
