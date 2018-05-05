package model;

/**
 * @author Ayberk
 *
 */
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainClass {

	// Starts the game
	public static void main(String[] args) {
		// Main class
		AppGameContainer agc;
		try {
			// Starts slick2d library
			agc = new AppGameContainer(new Game("War of Domination"));
			agc.setDisplayMode(1000, 700, false);
			agc.start();

		} catch (SlickException e) {

		}
	}
}
