package controller;

import org.newdawn.slick.SlickException;

import model.Map;
import model.Moving;
import model.Common.Direction;

public class MapControl {
	Map map;
	BulletManager bulletMngr;
	String loc = "res/timmy_map.tmx";
	int layerIndex = -10000;
	int object;

	public boolean loadMap() throws SlickException {
		map = new Map(loc);
		bulletMngr = new BulletManager();
		return true;
	}

	public Map getMap() {
		return this.map;
	}

	public int getId(int x, int y, int layerIndex) {
		return this.map.getTileId(x, y, layerIndex);
	}

	public int getLayerIndex(String str) {
		return this.map.getLayerIndex(str);
	}

	public boolean canMove(Moving ch, float delta, Direction dir) {
		
		
		if(layerIndex == -10000)
		{
			layerIndex = map.getLayerIndex("Background");
			object = map.getTileId(1,1,layerIndex);
		}

		// TEST System.out.println("Tile:" + map.getTileId(((int) (320 - ch.getX()) / 32),((int) (320 - ch.getY()) / 32), layerIndex));
		if (dir == Direction.UP) {
			if (map.getTileId(((int) (320 - ch.getX()) / 32), (((int) (320 - ch.getY() - 4 - delta * .1f) /32)),
					layerIndex) != object) {
				return true;
			}
		}else if(dir== Direction.DOWN) {
			if (map.getTileId(((int) (320 - ch.getX()) / 32),
					((int) (320 - ch.getY() + 4 + delta * .1f) / 32), layerIndex) != object)
				return true;
		}else if(dir==Direction.RIGHT) {
			if (map.getTileId((((int) (320 - ch.getX() + 4 + delta * .1f) / 32)),
					((int) (320 - ch.getY()) / 32), layerIndex) != object)
				return true;
		}else if(dir== Direction.LEFT) {
			if (map.getTileId((((int) (320 - ch.getX() - 4 - delta * .1f) / 32)),
					((int) (320 - ch.getY()) / 32), layerIndex) != object)
				return true;
		}
		System.out.println("Tile:" + map.getTileId(((int) (320 - ch.getX()) / 32),((int) (320 - ch.getY()) / 32), layerIndex));
		return false;
		
	}
	
	

}
