package view;


import org.newdawn.slick.*;

import model.HealthBar;

public class CharacterView extends MovingView {
	// Properties
	HealthBar healthbar;

	int[] duration = { 200, 200, 200 };

	public CharacterView(String str) throws SlickException {
		super();
		healthbar = new HealthBar();
		walkUp = new Image[3];
		walkDown = new Image[3];
		walkLeft = new Image[3];
		walkRight = new Image[3];

		for (int i = 1; i < 4; i++) {
			walkUp[i - 1] = new Image("res/" + str + "_up_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkDown[i - 1] = new Image("res/" + str + "_down_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkLeft[i - 1] = new Image("res/" + str + "_left_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkRight[i - 1] = new Image("res/" + str + "_right_" + i + ".png");
		}
		up = new Animation(walkUp, duration, false);
		down = new Animation(walkDown, duration, false);
		left = new Animation(walkLeft, duration, false);
		right = new Animation(walkRight, duration, false);
		animList = new Animation[4];
		animList[0] = up;
		animList[1] = right;
		animList[2] = left;
		animList[3] = down;
		bucky = down;
		for (int i = 0; i < animList.length; i++) {
			animList[i].setAutoUpdate(true);
		}
	}

	public void draw(GameContainer gc, Graphics g) throws SlickException {
		this.getMainAnimation().draw(ch.getShiftX(), ch.getShiftY());
		healthbar.render(gc, g, ch.getHealth(), ch.MAX_HEALTH);
		
	}

	public void setAnimation(String str) {
		if (str == "up")
			bucky = up;
		if (str == "down")
			bucky = down;
		if (str == "right")
			bucky = right;
		if (str == "left")
			bucky = left;
	}
}
