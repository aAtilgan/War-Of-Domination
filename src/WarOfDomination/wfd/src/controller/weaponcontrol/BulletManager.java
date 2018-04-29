package controller.weaponcontrol;

import org.newdawn.slick.tiled.TiledMap;

import model.RoundData;
import model.personmodel.Moving;
import model.weaponmodel.Bullet;
import model.weaponmodel.Weapon;

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
	
	
	
	

	public void updateBullet(Weapon aBullet, float mapPosX, float mapPosY, TiledMap map) 
	{
		int layerIndex = map.getLayerIndex("Objects");
		ch = aBullet.getTarget();
		characterX = ch.getX();
		characterY = ch.getY();


		
		if (((aBullet.getX() - mapPosX) / 32 > 0) && ((aBullet.getY() - mapPosY) / 32 > 0) // harita dýsý check
				&& ((aBullet.getY() - mapPosY) / 32 < 30) && ((aBullet.getX() - mapPosX) / 32 < 34)) { 
			if (map.getTileId((((int) (aBullet.getX() - mapPosX) / 32)), ((int) (aBullet.getY() - mapPosY) / 32), // tile'a carpýyo mu check
					layerIndex) == 0) 
			{
				aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(aBullet.getangle()))));
				aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(aBullet.getangle()))));
				if (Math.hypot(characterX - aBullet.getX(), characterY - aBullet.getY()) < 7) 
				{ 
					listOfBullets.remove(aBullet);
					ch.setHealth(ch.getHealth()-10);
					if(ch.getCharacter_id()==0)
						RoundData.given_damage_enemy+=10;
					else
						RoundData.given_damage_hero+=10;
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
