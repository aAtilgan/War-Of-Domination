package model;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.GameManager;
import controller.MenuManager;
import view.CreditsMenu;
import view.GameOverView;
import view.HowToPlayView;
import view.PauseMenu;
import view.SettingsMenu;

public class Game extends StateBasedGame implements Observer {
	SettingsData data;
	S0und msc;
	public static final int startMenu = 0;
	public static final int worldMap=1;
	public static final int settings=2;
	public static final int pause = 3;
	public static final int gameOver=4;
	public static final int credits=5;
	public static final int how_to_play=6;
	public Game(String gameName) {
		
		super(gameName);
		data=new SettingsData();
		data.addObserver(this);
		msc=new S0und();
		data.addObserver(msc);
		this.addState(new MenuManager(startMenu));
		this.addState(new GameManager(worldMap));
		this.addState(new SettingsMenu(settings,data));
		this.addState(new GameOverView(gameOver));
		this.addState(new CreditsMenu(credits));
		this.addState(new PauseMenu(pause));
		this.addState(new HowToPlayView(how_to_play));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.getState(settings).init(gc, this);
		this.enterState(startMenu);
		msc.playTitleMusic();
	}

	@Override
	public void update(Observable obs, Object arg1) {
		// TODO Auto-generated method stub
		data = (SettingsData)obs;
		
	}
}
