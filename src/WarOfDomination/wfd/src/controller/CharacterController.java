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
				if (dir == Direction.RIGHT && chView.ch.getX() - map.getX() < 1070) {
					return true;
				} else if (dir == Direction.LEFT && chView.ch.getX() - map.getX() > 4) {
					return true;
				} else if (dir == Direction.UP && chView.ch.getY() - map.getY()> 4) {
					return true;
				} else if (dir == Direction.DOWN && chView.ch.getY() - map.getY() < 1000) {
					return true;
				}
				return false;
			}
		}
		
		return false;
	}
	
	

	
	
	public void shoot(float targetX,float targetY)
	{
		
		
		
	}
}
