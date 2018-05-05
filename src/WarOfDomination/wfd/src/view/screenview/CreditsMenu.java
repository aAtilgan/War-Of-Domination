package view.screenview;

/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsMenu extends BasicGameState implements InformativeView {
	// Pictures
	Input input;
	Image background;

	// constructor
	public CreditsMenu(int credits) {
	}

	@Override
	// loads the background image.
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/credits_background.png");
	}

	@Override
	// Draws intormation strings on fixed locations on screen.
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0, null);
		g.setColor(Color.white);
		g.drawString("Press ESCAPE to turn the Main Menu", 75, 500);
		// Change color
		g.setBackground(Color.black);
		g.setColor(Color.orange);
		g.drawString("CREDITS", 100, 100);
		// Change color
		g.setColor(new Color(0.3f, 0.4f, 0.6f));
		g.drawString("AYBERK TECIMER", 100, 200);
		g.drawString("AKANT ATILGAN", 100, 250);
	}

	// Checks user input: Changes state if user presses escape key.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 5;
	}

	@Override
	public void update() {
	}

	@Override
	public void init() {
	}

}
