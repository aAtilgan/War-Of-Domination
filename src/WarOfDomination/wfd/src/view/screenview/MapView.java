package view.screenview;

/**
 * @author Ayberk
 *
 */
import model.Map;

public class MapView {
	// Stores our map object
	Map map;

	// constructor
	public MapView(Map map) {
		this.map = map;
	}

	// Calls the render function of our map object
	public void render(float x, float y) {
		map.render((int) x, (int) y);
	}
}
