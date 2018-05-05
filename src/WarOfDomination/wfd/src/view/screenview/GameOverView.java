package view.screenview;

import org.lwjgl.input.Keyboard;

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
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameManager;
import model.RoundData;

public class GameOverView extends BasicGameState {

	// properties
	private Sound game_over;
	private int time = 0;
	private float alpha1 = 0f;
	private boolean flag = true;
	private float alpha2 = 0f;
	Input input;
	// Picture
	Image background;

	// Winner name
	String winner = "";

	// constructor
	public GameOverView(int gameover) {

	}

	@Override
	// loads resources and initializes variables
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		RoundData.round = 1;
		background = new Image("res/game_over_background.png");
		game_over = new Sound("res/game_over_sound.ogg");
		alpha2 = 0f;
		alpha1 = 0f;
		flag = true;

		// Checks if game is over.
		if (GameManager.hero_win > GameManager.enemy_win) {
			winner = "Player1";
		} else {
			winner = "Player2";
		}
	}

	@Override
	// Draws Information strings on the screen
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {

		g.drawImage(background, 0, 0, null);
		g.setColor(new Color(1f, 1f, 1f, alpha1));
		alpha1 = alpha1 + 0.02f;
		g.drawString("Hero Score is " + GameManager.hero_win, 100, 100);
		g.drawString("Enemy Score is " + GameManager.enemy_win, 100, 150);
		g.drawString("GAME WINNER IS: " + winner, 100, 200);
		g.drawString("Press Enter to Return Main Menu", 600, 500);
		g.drawString("", container.getScreenWidth() / 2 - 150f, container.getScreenHeight() / 2);
	}

	@Override
	// Checks user inputs: Exits the state if user presses enter
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		input = gc.getInput();
		if (flag) {
			game_over.play();
			flag = false;
		}
		if (Keyboard.isKeyDown(Input.KEY_ENTER)) {
			sbg.getState(0).init(gc, sbg);
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {

		return 4;
	}

}
