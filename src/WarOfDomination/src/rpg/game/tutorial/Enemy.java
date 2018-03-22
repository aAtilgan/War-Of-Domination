package rpg.game.tutorial;

import org.newdawn.slick.SlickException;

public class Enemy extends Moving{

	public Enemy(int character_id) throws SlickException {
		super(character_id);
		setShiftX(300);
		setShiftY(300);
		setSpeed(.1f);
	}

}
