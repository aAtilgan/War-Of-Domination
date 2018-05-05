package model;

/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap {

	public Map(String ref) throws SlickException {
		super(ref);
		// Map class extends TiledMap to access it's features
	}

}
