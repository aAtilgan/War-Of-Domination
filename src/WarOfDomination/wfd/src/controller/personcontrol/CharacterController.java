package controller.personcontrol;

/**
 * @author Akant
 * @author Ayberk
 */
import controller.MapControl;
import model.Common;
import model.Common.Direction;
import view.CharacterView;

public class CharacterController {
	// View class
	CharacterView chView;
	// Map controller
	MapControl map;

	public CharacterController(CharacterView chView, MapControl map) {
		// initialize view and control objects
		this.chView = (CharacterView) chView;
		this.map = map;
	}

	/*
	 * This function checks if a character can move towards specified direction with
	 * the help of mapController. If the movement is possible; This function sets
	 * the animation of Player1 character according to current direction of that
	 * character.
	 */
	public boolean move(Common.Direction dir, float delta) {
		delta = delta * chView.ch.getSpeed();
		if (chView.ch.getCharacter_id() == 0) {
			if (map.canMove(chView.ch, delta, dir)) {
				if (dir == Direction.RIGHT) {
					chView.setAnimation("right");
					return true;
				} else if (dir == Direction.LEFT) {
					chView.setAnimation("left");
					return true;
				} else if (dir == Direction.UP) {
					chView.setAnimation("up");
					return true;
				} else if (dir == Direction.DOWN) {
					chView.setAnimation("down");
					return true;
				}
				return false;
			}
		}

		return false;
	}
}
