package view.screenview;
/**
 * @author Ayberk
 *
 */
import model.Map;

public class MapView {
	Map map;
	public MapView(Map map) {
		this.map=map;
	}
	public void render(float x, float y) {
		map.render((int)x, (int)y);
	}
}
