package rpg.game.tutorial;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameStartManagement extends StateBasedGame {
	SettingsData data;
	public static final int startMenu = 0;
	public static final int worldMap=1;
	public static final int settings=2;
	public GameStartManagement(String gameName) {
		
		super(gameName);
		data=new SettingsData();
		this.addState(new MenuManager(startMenu));
		this.addState(new MapManager(worldMap));
		this.addState(new SettingsMenu(settings,data));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.enterState(startMenu);
		
	}
}
