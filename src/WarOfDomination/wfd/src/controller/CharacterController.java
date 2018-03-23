package controller;

import model.Common;
import model.Common.Direction;
import view.CharacterView;

public class CharacterController {
	CharacterView chView;
	MapControl map;

	public CharacterController(CharacterView chView, MapControl map) {
		this.chView = (CharacterView) chView;
		this.map = map;
	}

	public boolean move(Common.Direction dir, float delta) {
		delta = delta * chView.ch.getSpeed();
		if (chView.ch.getCharacter_id() == 0) {
			if (map.canMove(chView.ch, delta, dir)) {
				if (dir == Direction.RIGHT && chView.ch.getX() > -750) {
					chView.ch.setX(chView.ch.getX() - delta);
					chView.setAnimation("right");
				} else if (dir == Direction.LEFT && chView.ch.getX() < 320) {
					chView.ch.setX(chView.ch.getX() + delta);
					chView.setAnimation("left");
				} else if (dir == Direction.UP && chView.ch.getY() < 320) {
					chView.ch.setY(chView.ch.getY() + delta);
					chView.setAnimation("up");
				} else if (dir == Direction.DOWN && chView.ch.getY() > -620) {
					chView.ch.setY(chView.ch.getY() - delta);
					chView.setAnimation("down");
				}
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean counterMovement(Common.Direction dir, float delta) {
        delta = delta * chView.ch.getSpeed();

        if (map.canMove(chView.ch, delta, dir)) {
            if (dir == Direction.RIGHT && chView.ch.getX() > -750) {
                chView.ch.setX(chView.ch.getX() - delta);

            } else if (dir == Direction.LEFT && chView.ch.getX() < 320) {
                chView.ch.setX(chView.ch.getX() + delta);

            } else if (dir == Direction.UP && chView.ch.getY() < 320) {
                chView.ch.setY(chView.ch.getY() + delta);
            } else if (dir == Direction.DOWN && chView.ch.getY() > -620) {
                chView.ch.setY(chView.ch.getY() - delta);

            }
            return true;
        }

        return false;
    }
	
	
	public void shoot(float targetX,float targetY)
	{
		
		
		
	}
}
