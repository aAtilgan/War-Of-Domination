package controller.weaponcontrol;
/**
 * @author Akant
 *
 */
import org.newdawn.slick.tiled.TiledMap;

import model.RoundData;
import model.personmodel.Moving;
import model.weaponmodel.Bullet;
import model.weaponmodel.Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class BulletManager {

	//List of bullets
	ArrayList<Bullet> listOfBullets;
	//Temporary character object
	Moving ch;
	//Sounds of bullets
	Sound bullet_sound;
	Sound empty;

	//Character location on map
	float characterX,characterY;
	public BulletManager() throws SlickException 
	{
		//initialize sounds and list of bullets
		listOfBullets = new ArrayList<Bullet>();
		bullet_sound = new Sound("res/fire1.ogg");
		empty = new Sound("res/empty_sound.ogg");
	}

	/*
	 * This function creates a bullet object to track the projectile. It uses a vector to calculate the 
	 * angle between the shooters location and target location
	 */
	public void shoot(int x, int y, float sourceX, float sourceY,Moving shooter,Moving enemy) throws SlickException{

		
		
		Vector2f diffvector = new Vector2f((float) (x - sourceX),(float) (y - sourceY));
		float angle = (float) diffvector.getTheta();
		
		Bullet aBullet = new Bullet(x, 700 - y, sourceX, sourceY,angle,enemy);
		
		//Checks if user has enough ammunition to shoot.
		if(shooter.canShoot())
		{
			shooter.reduceAmmo();
			listOfBullets.add(aBullet);
			bullet_sound.play(1,0.06f);
		}
		//Plays empty weapon sound otherwise
		else
		{
			empty.play(1,0.06f);
		}
	}
	
	
	/*
	 * This function is the same as shoot() function of this class. However this one creates a "Shotgun" weapon effect
	 * by adding 5 bullets at once to the listOfBullets. Shotgun effect is created by randomizing the angle slightly.
	 */
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
		//Ammunition check
		if(shooter.canShootShell())
		{
			shooter.reduceShell();
			bullet_sound.play(1,0.06f);
		}
		else
		{
			empty.play(1,0.06f);
		}
	}
	
	
	
	
/*
 * This function deletes the bullet if it collides with a player or an obstacle on the map.
 * This function updates a SINGLE bullet at a time.
 */
	public void updateBullet(Weapon aBullet, float mapPosX, float mapPosY, TiledMap map) 
	{
		//Get maps objects layer for collusion check
		int layerIndex = map.getLayerIndex("Objects");
		//Extract target data from bullet
		ch = aBullet.getTarget();
		//Get target locations
		characterX = ch.getX();
		characterY = ch.getY();

		//Checks if the bullets are outside of the map or not. Deletes bullets if outside.
		if (((aBullet.getX() - mapPosX) / 32 > 0) && ((aBullet.getY() - mapPosY) / 32 > 0) 
				&& ((aBullet.getY() - mapPosY) / 32 < map.getHeight()) && ((aBullet.getX() - mapPosX) / 32 < map.getWidth())) 
		{ 

			//Checks if the bullet collides with an obstacle on the map
				if (map.getTileId((((int) (aBullet.getX() - mapPosX) / 32)), ((int) (aBullet.getY() - mapPosY) / 32),
						layerIndex) == 0) 
				{
					//Updates bullets location.
					aBullet.setX(aBullet.getX() + (float) (1 * Math.cos(Math.toRadians(aBullet.getangle()))));
					aBullet.setY(aBullet.getY() + (float) (1 * Math.sin(Math.toRadians(aBullet.getangle()))));
					
					//If the bullet collides with character it gets deleted.
					//(Done by proximity check between center of character and bullet location)
					if (Math.hypot(characterX - aBullet.getX(), characterY - aBullet.getY()) < 8) 
					{ 
						listOfBullets.remove(aBullet);
						//Reduce character life
						ch.setHealth(ch.getHealth()-10);
						
						//Update scores
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

	//Return listOfBullets
	public ArrayList<Bullet> returnBulletsList() {
		return this.listOfBullets;
	}
	
	
	//Renders all bullets in the list.
	public void renderBullets(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnBulletsList().size(); i++) {
			Bullet abullet = this.returnBulletsList().get(i);
			
			abullet.draw(g);
		}
	}

}
