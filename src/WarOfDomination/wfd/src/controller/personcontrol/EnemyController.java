package controller.personcontrol;
/**
 * @author Akant
 *
 */
import controller.MapControl;
import model.Common;
import model.Common.Direction;
import view.EnemyView;

public class EnemyController {
	//View class
	EnemyView enemView;
	//Map controller
	MapControl map;

	public EnemyController(EnemyView chView, MapControl map) {
		//initialize view and control objects
		this.enemView = (EnemyView) chView;
		this.map = map;
	}

	/*This function checks if a character can move towards specified direction with the help of mapController.
	 * If the movement is possible;
	 * This function sets the animation of Player2 character according to current direction of that character.
	 */
	public boolean move(Common.Direction dir, float delta) {
		delta = delta * enemView.ch.getSpeed();
		if (enemView.ch.getCharacter_id() == 1) {
			if (map.canMove(enemView.ch, delta, dir)) {
				if (dir == Direction.RIGHT ) {
					enemView.ch.setX(enemView.ch.getX() + delta);
					enemView.setAnimation("right");
				} else if (dir == Direction.LEFT ) {
					enemView.ch.setX(enemView.ch.getX() - delta);
					enemView.setAnimation("left");
				} else if (dir == Direction.UP ) {
					enemView.ch.setY(enemView.ch.getY() - delta);
					enemView.setAnimation("up");
				} else if (dir == Direction.DOWN ) {
					enemView.ch.setY(enemView.ch.getY() + delta);
					enemView.setAnimation("down");
				}
				return true;
			}
			
		}
		
		return false;
	}
}
