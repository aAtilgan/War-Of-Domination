package view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.MapControl;
import model.Moving;
import controller.GameManager.InputManager;

public class PlayScreen extends BasicGameState{
	MapControl map;
	MapView mapView;
	MovingView movingView;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		//in = new InputManager();
		map = new MapControl();
		map.loadMap();

		mapView = new MapView(map.getMap());
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub
		mapView.render(0, 0);
		//movingView.draw(0,0);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 99;
	}

}
