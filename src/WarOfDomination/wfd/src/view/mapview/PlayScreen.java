package view.mapview;

/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.MapControl;
import view.MovingView;
import view.screenview.MapView;

public class PlayScreen extends BasicGameState {
	// Control and view objects
	MapControl map;
	MapView mapView;
	MovingView movingView;

	@Override

	// loads map control and map view objects
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		map = new MapControl();
		mapView = new MapView(map.getMap());
	}

	@Override
	// Renders the map on default location
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		mapView.render(0, 0);
	}

	@Override
	public int getID() {
		return 99;
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

}
