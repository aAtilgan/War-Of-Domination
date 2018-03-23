package model;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.GameManager;
import controller.MenuManager;
import view.PauseView;
import view.SettingsView;

public class Game extends StateBasedGame implements Observer {
	SettingsData data;
	S0und msc;
	public static final int startMenu = 0;
	public static final int worldMap = 1;
	public static final int settings = 2;
	public static final int pause = 5;

	public Game(String gameName) {

		super(gameName);
		data = new SettingsData();
		data.addObserver(this);
		msc = new S0und();
		data.addObserver(msc);
		this.addState(new MenuManager(startMenu));
		this.addState(new GameManager(worldMap));
		this.addState(new SettingsView(settings, data));
		this.addState(new PauseView(pause));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.getState(settings).init(gc, this);
		this.enterState(startMenu);
		// msc.playTitleMusic();
	}

	@Override
	public void update(Observable obs, Object arg1) {
		data = (SettingsData) obs;

	}
}
