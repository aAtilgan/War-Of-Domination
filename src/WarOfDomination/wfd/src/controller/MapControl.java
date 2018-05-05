package controller;

/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.SlickException;

import controller.weaponcontrol.BulletManager;
import model.Map;
import model.Common.Direction;
import model.personmodel.Moving;

public class MapControl {

	// Constants
	BulletManager bulletMngr;

	// Variables
	Map map;
	int layerIndex = -10000;
	int object;
	float mapX, mapY;
	String loc = "res/timmy_map.tmx";

	// constructor
	public boolean loadMap(String loc) throws SlickException {
		// initialize map according to location
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

	public void setX(float x) {
		this.mapX = x;
	}

	public void setY(float y) {
		this.mapY = y;
	}

	public float getX() {
		return this.mapX;
	}

	public float getY() {
		return this.mapY;
	}

	/*
	 * This function is the collision check of our game for character movement.
	 * Basically this function disables a character from moving closer than half of
	 * it's size to any obstacle on any map in our game. Function returns false if a
	 * character is too close to an obstacle in the specified direction.
	 */
	public boolean canMove(Moving ch, float delta, Direction dir) {

		// Only should be initialized once; -10000 is a layer number that doesn't exists
		// in our map.
		// Since both characters calls this method we don't want to re-initialize layers
		// for each of them.
		if (layerIndex == -10000) {
			layerIndex = map.getLayerIndex("Background");
			object = map.getTileId(1, 1, layerIndex);
		}

		if (dir == Direction.UP) {
			if ((ch.getY() - this.mapY + 8 + delta * .1f) > 8)// Outside map check
				// Obstacle collision check
				if (map.getTileId(((int) (ch.getX() - this.mapX) / 32),
						(((int) (ch.getY() - this.mapY - 8 - delta * .1f) / 32)), layerIndex) != object) {
					return true;
				}
		} else if (dir == Direction.DOWN) {
			if ((ch.getY() - this.mapY + 8 + delta * .1f) / 32 < this.map.getHeight())// Outside map check
				// Obstacle collision check
				if (map.getTileId(((int) (ch.getX() - this.mapX) / 32),
						((int) (ch.getY() - this.mapY + 8 + delta * .1f) / 32), layerIndex) != object)
					return true;
		} else if (dir == Direction.RIGHT) {
			if ((ch.getX() - this.mapX + 8 + delta * .1f) / 32 < this.map.getWidth())// Outside map check
				// Obstacle collision check
				if (map.getTileId((((int) (ch.getX() - this.mapX + 8 + delta * .1f) / 32)),
						((int) (ch.getY() - this.mapY) / 32), layerIndex) != object)
					return true;
		} else if (dir == Direction.LEFT) {
			if ((ch.getX() - this.mapX - 4 - delta * .1f) > 8)// Outside map check
				// Obstacle collision check
				if (map.getTileId((((int) (ch.getX() - this.mapX - 8 - delta * .1f) / 32)),
						((int) (ch.getY() - this.mapY) / 32), layerIndex) != object)
					return true;
		}
		return false;

	}

}
