package view;

/**
 * @author Ayberk
 *
 */
import java.util.Observable;
import org.newdawn.slick.*;

import model.personmodel.Moving;

public class MovingView implements java.util.Observer {
	// Animations of character
	Animation left, right, up, down;
	Animation[] animList;
	public Moving ch;

	// Image arrays of character
	Image[] walkUp;
	Image[] walkDown;
	Image[] walkLeft;
	Image[] walkRight;

	// variable
	Animation bucky;

	// constructor
	public MovingView(Animation a) throws SlickException {
		bucky = a;
	}

	// constructor dummy
	public MovingView() throws SlickException {
	}

	// Sets default animation as down
	public void setDefault(Animation a) {
		bucky = a;
	}

	public Animation getMainAnimation() {
		return bucky;
	}

	@Override
	// Updates character animation according to direction when the direction
	// property changes.
	public void update(Observable obs, Object arg) {
		if (obs instanceof Moving) {
			ch = (Moving) obs;
			bucky = getMainAnimation();
		}

	}

}
