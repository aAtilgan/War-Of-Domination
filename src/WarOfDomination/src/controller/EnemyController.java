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

		if (map.canMove(enemView.ch, delta, dir)) {
			if (dir == Direction.RIGHT && enemView.ch.getX() > -750) {
				enemView.ch.setX(enemView.ch.getX() - delta);
				enemView.setAnimation("right");
			} else if (dir == Direction.LEFT && enemView.ch.getX() < 320) {
				enemView.ch.setX(enemView.ch.getX() + delta);
				enemView.setAnimation("left");
			} else if (dir == Direction.UP && enemView.ch.getY() < 320) {
				enemView.ch.setY(enemView.ch.getY() + delta);
				// System.out.println("INSIDE Y:" + enemView.ch.getY());
				enemView.setAnimation("up");
				 System.out.println(enemView.ch.getX() + " Y: " + enemView.ch.getY());
			} else if (dir == Direction.DOWN && enemView.ch.getY() > -620) {
				enemView.ch.setY(enemView.ch.getY() - delta);
				enemView.setAnimation("down");
			}
			return true;
		}

		return false;
	}

	public void shoot(float targetX, float targetY) {

	}
}
