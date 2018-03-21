package rpg.game.tutorial;

import java.util.Observable;
import org.newdawn.slick.*;

public class MovingView implements java.util.Observer {
	// properties
	Animation currentAnim, movingLeft, movingRight, movingUp, movingDown;
	Animation[] animList;
	Moving obj;

	Image[] walkUp;
	Image[] walkDown;
	Image[] walkLeft;
	Image[] walkRight;

	public MovingView(Animation a) throws SlickException {
		currentAnim = a;
	}

	public MovingView() throws SlickException {
	}

	public void setDefault(Animation a) {
		currentAnim = a;
	}

	@Override
	public void update(Observable obs, Object arg) {
		// TODO Auto-generated method stub
		}

	}

