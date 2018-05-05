package view.screenview;
/**
 * @author Ayberk
 *
 */
/*
 * This class changes its current screen with respect to gamestate
 * But it also enables change of screen without changing the game state
 */

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenContainer implements Observer {
	// List of screens in the game
	ArrayList<BasicGameState> screens;

	// Stores current Game state
	BasicGameState current;

	// Game object
	Game game;

	// Main graphic representation of our game.
	// This is sent to all necessary controller or view classes to be manipulated
	Graphics g;

	// constructor
	public ScreenContainer(Game game) {
		// initialize list of states
		screens = new ArrayList<BasicGameState>();
		this.game = game;
		// get current state from slick2d
		current = (BasicGameState) ((StateBasedGame) game).getCurrentState();
	}

	// add screen to our game
	public void addScreen(BasicGameState screen) {
		screens.add(screen);
	}

	// add screen to our game
	public void setScreen(BasicGameState screen) {
		screens.add(screen);
	}

	public BasicGameState getCurrent() {
		return current;
	}

	@Override
	/*
	 * Is not used because: 1- Game already extends something 2- Game already holds
	 * the lists of state so it updates itself 3- It's not very logical to change
	 * the library when the library preserves design logic. In our reports, we just
	 * stored the states in another classes, storing the states in the model itself
	 * is not harmful and even beneficial.
	 */
	public void update(Observable obs, Object arg1) {
		// this.game = (Game)obs;
		// this.setScreen((BasicGameState)game.getCurrentState());
		// this.render((Game)obs, (GameContainer) arg1, g );
	}

	// Calls the render function of current game state class.
	public void render(GameContainer container, Graphics g, StateBasedGame sbg) throws SlickException {
		current.render(container, sbg, g);
	}

}
