package rpg.game.tutorial;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class TutorialGame extends StateBasedGame {
	public static final String gameName = "RPG Game";
	public static final int startMenu = 0;
	public static final int worldMap=1;

	public TutorialGame(String gameName) {
		super(gameName);
		this.addState(new MainMenu(startMenu));
		this.addState(new WorldMap(worldMap));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.enterState(startMenu);
		
	}

	public static void main(String[] args) {
		AppGameContainer agc;
		try {
			agc = new AppGameContainer(new TutorialGame(gameName));

			agc.setDisplayMode(1000, 700, false);
			agc.start();
		} catch (SlickException e) {

		}
	}
}
