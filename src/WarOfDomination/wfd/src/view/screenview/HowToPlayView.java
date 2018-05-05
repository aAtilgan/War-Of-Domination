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

public class HowToPlayView extends BasicGameState implements InformativeView {
	// constants
	Image background;
	String[] str = new String[10];

	// variables
	Input input;

	// constructor
	public HowToPlayView(int howToPlay) {
	}

	@Override
	// loads background map and calls helper function to initialize strings
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/how_to_play_background.png");
		fillStr(str);
	}

	@Override
	// Draws information texts on the screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.drawImage(background, 0, 0, null);
		// Welcome String
		g.drawString(str[0], 25, 100);

		// Movement Strings
		g.drawString(str[1], 25, 125);
		g.drawString(str[2], 525, 125);

		// Shoot Strings
		g.drawString(str[3], 25, 250);
		g.drawString(str[4], 525, 250);
		// General String
		g.drawString(str[5], 300, 375);
		g.setColor(Color.orange);

		g.drawString(str[6], 250, 500);
		g.drawString("How To Play", 300, 50);
	}

	@Override
	// Checks exit state by comparing user inputs. If user presses enter it exits
	// state.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 6;
	}

	@Override
	public void update() {
	}

	@Override
	public void init() {
	}

	// Initializes strings
	public void fillStr(String[] str) {
		str[0] = "Welcome to War of Domination";
		str[1] = " Character Controls: \n UP = up_key \n DOWN = down_key \n LEFT = left_key \n RIGHT = right_key";
		str[2] = " Enemy Controls: \n UP = W_key \n DOWN = S_key \n LEFT = A_key \n RIGHT= D_key";
		str[3] = " Character Shoot & Reload : \n Shoot = Mouse_left_click \n Reload = right_cntrl";
		str[4] = " Enemy Shoot & Reload : \n Shoot = left_cntrl \n Reload = r_key";

		str[5] = " General Controls : \n Pause = p_key \n Options =  o_key \n Return Main Menu = escape_key";
		str[6] = " Press Escape to return Main Menu";
	}
}
