package controller;

import model.Common;
import model.Common.Direction;
import view.EnemyView;

public class EnemyController {
	EnemyView enemView;
	MapControl map;

	public EnemyController(EnemyView chView, MapControl map) {
		this.enemView = (EnemyView) chView;
		this.map = map;
	}

	public boolean move(Common.Direction dir, float delta) {
		delta = delta * enemView.ch.getSpeed();
		if (enemView.ch.getCharacter_id() == 1) {
			if (map.canMove(enemView.ch, delta, dir)) {
				if (dir == Direction.RIGHT && enemView.ch.getX() - map.getX() < 1070) {
					enemView.ch.setX(enemView.ch.getX() + delta);
					enemView.setAnimation("right");
				} else if (dir == Direction.LEFT && enemView.ch.getX() - map.getX() > 4) {
					enemView.ch.setX(enemView.ch.getX() - delta);
					enemView.setAnimation("left");
				} else if (dir == Direction.UP && enemView.ch.getY() - map.getY() > 4) {
					enemView.ch.setY(enemView.ch.getY() - delta);
					enemView.setAnimation("up");
				} else if (dir == Direction.DOWN && enemView.ch.getY() - map.getY() < 1000) {
					enemView.ch.setY(enemView.ch.getY() + delta);
					enemView.setAnimation("down");
				}
				return true;
			}
			
		}
		
		return false;
	}
	

	
	
	public void shoot(float targetX,float targetY)
	{
		
		
		
	}
}
