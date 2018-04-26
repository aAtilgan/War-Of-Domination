package controller;

import org.newdawn.slick.tiled.TiledMap;

import model.Bullet;
import model.Moving;
import model.Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class BulletManager {

	ArrayList<Bullet> listOfBullets;
	Moving ch;

	float characterX,characterY;
	public BulletManager() 
	{
		listOfBullets = new ArrayList<Bullet>();
	}

	public void shoot(int x, int y, float sourceX, float sourceY,Moving shooter,Moving enemy) throws SlickException{

		
		Vector2f diffvector = new Vector2f((float) (x - sourceX),(float) (y - sourceY));
		float angle = (float) diffvector.getTheta();
		
		Bullet aBullet = new Bullet(x, 700 - y, sourceX, sourceY,angle,enemy);
		if(shooter.canShoot())
		{
			shooter.reduceAmmo();
			listOfBullets.add(aBullet);
		}
	}
	
	public void shootShotgun(int x, int y, float sourceX, float sourceY,Moving shooter,Moving enemy) throws SlickException{
		
		float angle;
		Vector2f diffvector;
		diffvector = new Vector2f((float) (x - sourceX),(float) (y - sourceY));
		for(int i = 0;i < 5;i++)
		{
			angle = (float) (diffvector.getTheta() + Math.random()*5);
			Bullet aBullet = new Bullet(x, 700 - y, sourceX, sourceY,angle,enemy);
			if(shooter.canShootShell())
			{
				listOfBullets.add(aBullet);
				
			}
		}
		if(shooter.canShootShell())
			shooter.reduceShell();
	}
	
	
	
	

	public void updateBullet(Weapon aBullet, float screenPosX, float screenPosY, TiledMap map) 
	{
		int layerIndex = map.getLayerIndex("Objects");
		ch = aBullet.getTarget();
		if(ch.character_id == 0)
		{
			characterX = 320;
			characterY = 320 + 4;
		}
		else
		{
			characterX = ch.getShiftX()- ch.getX();
			characterY = (ch.getShiftY()- ch.getY()) + 4;
		}

		
		if (((aBullet.getX() - screenPosX) / 32 > 0) && ((aBullet.getY() - screenPosY) / 32 > 0) // harita dýsý check
				&& ((aBullet.getY() - screenPosY) / 32 < 30) && ((aBullet.getX() - screenPosX) / 32 < 34)) { 
			if (map.getTileId((((int) (aBullet.getX() - screenPosX) / 32)), ((int) (aBullet.getY() - screenPosY) / 32), // tile'a carpýyo mu check
					layerIndex) == 0) 
			{
				aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(aBullet.getangle()))));
				aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(aBullet.getangle()))));
				if (Math.hypot(characterX - aBullet.getX(), characterY - aBullet.getY()) < 10) 
				{ 
					listOfBullets.remove(aBullet);
					ch.setHealth(ch.getHealth()-10);
				}
				
			} 
			else
				listOfBullets.remove(aBullet);
		}

		else 
		{
			listOfBullets.remove(aBullet);

		}
	}

	public ArrayList<Bullet> returnBulletsList() {
		return this.listOfBullets;
	}
	
	
	public void renderBullets(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnBulletsList().size(); i++) {
			Bullet abullet = this.returnBulletsList().get(i);
			
			abullet.draw(g);
		}
	}

}
