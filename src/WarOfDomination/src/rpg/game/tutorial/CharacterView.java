package rpg.game.tutorial;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.*;

public class CharacterView implements java.util.Observer {
	// Properties
	Character ch;
	Animation bucky, up, down, left, right;
	int[] duration = { 200, 200 };

	public CharacterView() throws SlickException {

		Image[] walkUp = { new Image("res/buckysBack.png"), new Image("res/buckysBack.png") };
		Image[] walkDown = { new Image("res/buckysFront.png"), new Image("res/buckysFront.png") };
		Image[] walkLeft = { new Image("res/buckysLeft.png"), new Image("res/buckysLeft.png") };
		Image[] walkRight = { new Image("res/buckysRight.png"), new Image("res/buckysRight.png") };
		up = new Animation(walkUp, duration, false);
		down = new Animation(walkDown, duration, false);
		left = new Animation(walkLeft, duration, false);
		right = new Animation(walkRight, duration, false);
		bucky = down;
	}

	public void draw() throws SlickException {
		this.getMainAnimation().draw(ch.getShiftX(), ch.getShiftY());

	}

	public Animation getMainAnimation() {
		return bucky;
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

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof Character) {
			ch = (Character) obs;
			bucky = getMainAnimation();
			// bucky.draw();
			//System.out.println("BURAYA GÝRDÝ");
		}

	}

}
