package rpg.game.tutorial;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class BulletManager {

	ArrayList<Bullet> listOfBullets;

	public BulletManager() {
		listOfBullets = new ArrayList<Bullet>();

	}

	public void shoot(int x, int y, float sourceX, float sourceY) {
		Bullet aBullet = new Bullet(x, y, sourceX, sourceY);
		listOfBullets.add(aBullet);
	}

	public void updateBullet(Bullet aBullet, float screenPosX, float screenPosY, int objectLayer, TiledMap map) {
		Vector2f diffvector = new Vector2f((float) (aBullet.targetgetX() - aBullet.getX()),
				(float) (aBullet.targetgetY() - aBullet.getY()));
		float angle = (float) diffvector.getTheta();

		if (((aBullet.getX() - screenPosX) / 32 > 0) && ((aBullet.getY() - screenPosY) / 32 > 0) // harita dýsý check
				&& ((aBullet.getY() - screenPosY) / 32 < 20) && ((aBullet.getX() - screenPosX) / 32 < 20)) { 
			if (map.getTileId((((int) (aBullet.getX() - screenPosX) / 32)), ((int) (aBullet.getY() - screenPosY) / 32), // tile'a carpýyo mu check
					objectLayer) != 65) {
				if (Math.hypot(aBullet.targetgetX() - aBullet.getX(), aBullet.targetgetY() - aBullet.getY()) > 3) { // uzaklýðý ölcer
					aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(angle))));
					aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(angle))));
				}

				else {
					listOfBullets.remove(aBullet);
				}
			} else
				listOfBullets.remove(aBullet);
		}

		else {
			listOfBullets.remove(aBullet);

		}
	}

	public ArrayList<Bullet> returnBulletsList() {
		return this.listOfBullets;
	}

}
