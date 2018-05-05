package view.screenview;

/**
 * @author Ayberk
 *
 */
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends BasicGameState {

	// Button Images
	private Image resumeButton;
	private Image exitButton;

	// constructor
	public PauseMenu(int state) {

	}

	// constructor dummy
	public PauseMenu() {

	}

	// load button picture and scale them.
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		resumeButton = new Image("res/resume_button.png");
		exitButton = new Image("res/exit_button.png");
		exitButton = exitButton.getScaledCopy(0.5f);
		resumeButton = resumeButton.getScaledCopy(0.5f);
	}

	// Draws button pictures and texts on screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.orange);
		g.drawString("PAUSED!", 400, 100);

		resumeButton.draw(400, 150, new Color(0.8f, 0.8f, 0.8f, 1f));
		exitButton.draw(400, 250, new Color(0.8f, 0.8f, 0.8f, 1f));
	}

	@Override
	//This method checks user inputs by comparing the location of mouse click to the location of buttons.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// Get mouse location
		float x = Mouse.getX();
		float y = Mouse.getY();

		// Resume Game
		if (((x <= 570) && (x >= 404)) && ((y >= 500) && (y <= 545))) {
			resumeButton.setImageColor(1f, 1f, 1f, 1f);
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(1);
			}
		} else {
			resumeButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}

		// Exit Game
		if (((x <= 570) && (x >= 400)) && ((y >= 400) && (y <= 440))) {
			exitButton.setImageColor(1f, 1f, 1f, 1f);
			if (Mouse.isButtonDown(0)) {
				AL.destroy();
				System.exit(0);
			}
		}
	}

	@Override
	public int getID() {
		return 3;
	}
}