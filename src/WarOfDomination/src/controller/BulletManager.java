package controller;

import org.newdawn.slick.tiled.TiledMap;

import model.Bullet;

import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class BulletManager {

	ArrayList<Bullet> listOfBullets;

	public BulletManager() {
		listOfBullets = new ArrayList<Bullet>();

	}

	public void shoot(int x, int y, float sourceX, float sourceY) {

		
		Vector2f diffvector = new Vector2f((float) (x - sourceX),(float) (y - sourceY));
		float angle = (float) diffvector.getTheta();
		
		Bullet aBullet = new Bullet(x, 700 - y, sourceX, sourceY,angle);
		// TEST System.out.println("MOUSE Y: " + (700 - y));
		listOfBullets.add(aBullet);
	}

	public void updateBullet(Bullet aBullet, float screenPosX, float screenPosY, TiledMap map) {
		int layerIndex = map.getLayerIndex("Objects");

		
		
		if (((aBullet.getX() - screenPosX) / 32 > 0) && ((aBullet.getY() - screenPosY) / 32 > 0) // harita dýsý check
				&& ((aBullet.getY() - screenPosY) / 32 < 30) && ((aBullet.getX() - screenPosX) / 32 < 34)) { 
			if (map.getTileId((((int) (aBullet.getX() - screenPosX) / 32)), ((int) (aBullet.getY() - screenPosY) / 32), // tile'a carpýyo mu check
					layerIndex) == 0) 
			{
				aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(aBullet.getangle()))));
				aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(aBullet.getangle()))));
				/*if (Math.hypot(aBullet.targetgetX() - aBullet.getX(), aBullet.targetgetY() - aBullet.getY()) > 3) { // uzaklýðý ölcer
					aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(angle))));
					aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(angle))));
				}

				else {
					listOfBullets.remove(aBullet);
				}*/
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
