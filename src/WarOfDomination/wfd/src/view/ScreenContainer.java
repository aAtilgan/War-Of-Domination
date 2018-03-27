package view;

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

public class ScreenContainer implements Observer{
	// Properties
	ArrayList<BasicGameState> screens;
	BasicGameState current;
	Game game;
	Graphics g;
	
	public ScreenContainer(Game game){
		screens = new ArrayList<BasicGameState>();
		this.game = game;
		current = (BasicGameState) ((StateBasedGame) game).getCurrentState();
	}
	
	public void addScreen (BasicGameState screen) {
		screens.add(screen);
	}
	
	public void setScreen (BasicGameState screen) {
		screens.add(screen);
	}

	public BasicGameState getCurrent() {
		return current;
	}

	@Override
	/*
	 * Is not used because:
	 * 1- Game already extends something
	 * 2- Game already holds the lists of state so it updates itself
	 * 3- It's not very logical to change the library when the library preserves design logic. In our reports, we just
	 * stored the states in another classes, storing the states in the model itself is not harmful and even beneficial.
	 */
	public void update(Observable obs, Object arg1)  {
		// TODO Auto-generated method stub
		//this.game = (Game)obs;
		//this.setScreen((BasicGameState)game.getCurrentState());
//		this.render((Game)obs, (GameContainer) arg1, g  );
	}
	
	public void render(GameContainer container, Graphics g, StateBasedGame sbg) throws SlickException {
		current.render(container, sbg, g);
	}
	
}
