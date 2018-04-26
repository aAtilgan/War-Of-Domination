package view;
/**
 * @author Ayberk
 *
 */
import java.util.Observable;
import org.newdawn.slick.*;

import model.Moving;

public class MovingView implements java.util.Observer {
	// properties
	Animation bucky, left, right, up, down;
	Animation[] animList;
	public Moving ch;

	Image[] walkUp;
	Image[] walkDown;
	Image[] walkLeft;
	Image[] walkRight;

	public MovingView(Animation a) throws SlickException {
		bucky = a;
	}

	public MovingView() throws SlickException {
	}

	public void setDefault(Animation a) {
		bucky = a;
	}

	public Animation getMainAnimation() {
		return bucky;
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof Moving) {
			ch = (Moving) obs;
			bucky = getMainAnimation();
			// bucky.draw();
			 //System.out.println("BURAYA GÝRDÝ: " + ch.getCharacter_id());
		}

	}

}
