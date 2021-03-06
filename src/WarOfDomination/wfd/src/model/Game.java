package model;

/**
 * @author Ayberk
 *
 */
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.GameManager;
import controller.MenuManager;
import model.utilitiesmodel.S0und;
import model.utilitiesmodel.SettingsData;
import view.screenview.CharacterChoiceView;
import view.screenview.CreditsMenu;
import view.screenview.GameOverView;
import view.screenview.HowToPlayView;
import view.screenview.PauseMenu;
import view.screenview.SettingsMenu;
import view.screenview.StoryView;

public class Game extends StateBasedGame implements Observer {
	SettingsData data;
	S0und msc;

	// Game states
	public static final int startMenu = 0;
	public static final int worldMap = 1;
	public static final int settings = 2;
	public static final int pause = 3;
	public static final int gameOver = 4;
	public static final int credits = 5;
	public static final int how_to_play = 6;
	public static final int story_mode = 7;
	public static final int character_choice = 8;

	// Constructor
	public Game(String gameName) throws SlickException {

		// add the game states and start game.
		super(gameName);
		data = new SettingsData();
		data.addObserver(this);
		msc = new S0und();
		data.addObserver(msc);
		this.addState(new MenuManager(startMenu));
		this.addState(new GameManager(worldMap));
		this.addState(new SettingsMenu(settings, data));
		this.addState(new GameOverView(gameOver));
		this.addState(new CreditsMenu(credits));
		this.addState(new PauseMenu(pause));
		this.addState(new HowToPlayView(how_to_play));
		this.addState(new StoryView(story_mode));
		this.addState(new CharacterChoiceView(character_choice));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// initialize states
		// TODO Auto-generated method stub
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.getState(settings).init(gc, this);
		this.enterState(startMenu);

		msc.volume = 0.1f;
		// msc.playTitleMusic(); M�zi�i ses a�

	}

	@Override

	// Update gets the changes from settings and applies them with observer
	// interface
	public void update(Observable obs, Object arg1) {
		data = (SettingsData) obs;

	}
}
