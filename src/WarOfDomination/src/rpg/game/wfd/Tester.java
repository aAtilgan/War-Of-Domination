package rpg.game.tutorial;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Tester {
	public static void main(String[] args) {
		final String gameName = "War of Domination";
		AppGameContainer agc;
		try {
			agc = new AppGameContainer(new GameStartManagement(gameName));
			
			agc.setDisplayMode(1000, 700, false);
			agc.start();
		} catch (SlickException e) {

		}
	}
}
