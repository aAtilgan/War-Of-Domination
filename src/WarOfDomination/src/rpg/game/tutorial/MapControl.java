package rpg.game.tutorial;

import org.newdawn.slick.SlickException;

import rpg.game.tutorial.Common.Direction;

public class MapControl {
	Map map;
	String loc = "res/map.tmx";

	public boolean loadMap() throws SlickException {
		map = new Map(loc);
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

	public boolean canMove(Character ch, float delta, Direction dir) {
		int layerIndex = map.getLayerIndex("Objects");
		if (dir == Direction.UP) {
			if (map.getTileId(((int) (320 - ch.getX()) / 32), (((int) (320 - ch.getY() - 16 - delta * .1f) /32)),
					layerIndex) != 65) {
				return true;
			}
		}else if(dir== Direction.DOWN) {
			if (map.getTileId(((int) (320 - ch.getX()) / 32),
					((int) (320 - ch.getY() + 16 + delta * .1f) / 32), layerIndex) != 65)
				return true;
		}else if(dir==Direction.RIGHT) {
			if (map.getTileId((((int) (320 - ch.getX() + 16 + delta * .1f) / 32)),
					((int) (320 - ch.getY()) / 32), layerIndex) != 65)
				return true;
		}else if(dir== Direction.LEFT) {
			if (map.getTileId((((int) (320 - ch.getX() - 16 - delta * .1f) / 32)),
					((int) (320 - ch.getY()) / 32), layerIndex) != 65)
				return true;
		}
		return false;
		
	}
}
