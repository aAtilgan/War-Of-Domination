package controller;

import org.newdawn.slick.tiled.TiledMap;

import model.Bullet;
import model.Knife;
import model.Moving;
import model.Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class WeaponManager {

	ArrayList<Weapon> listOfWeapons;
	BulletManager bulletMngr;
	KnifeManager knifeMngr;
	Moving ch;

	float characterX,characterY;
	public WeaponManager() 
	{
		listOfWeapons = new ArrayList<Weapon>();
		bulletMngr = new BulletManager();
		knifeMngr = new KnifeManager();

	}

	public void shoot(int x, int y, float sourceX, float sourceY,Moving enemy,Moving shooter,int type) throws SlickException
	{
		if(type == 0)
			bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
		else if(type == 1)
			knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
		else if(type == 2)
			bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
	}
	
	
	

	public void updateWeapon(Weapon aWeapon, float screenPosX, float screenPosY, TiledMap map,int type ) 
	{
		if(type == 0)
			bulletMngr.updateBullet((Bullet) aWeapon, screenPosX, screenPosY, map);
		else if(type == 1)
			knifeMngr.updateKnife((Knife) aWeapon, screenPosX, screenPosY, map);
	}
	
	

	public ArrayList<Weapon> returnWeaponsList() 
	{
		ArrayList<Weapon> theFinalList = new ArrayList<Weapon>();
		for(int i = 0;i < bulletMngr.returnBulletsList().size();i++)
		{
			theFinalList.add(bulletMngr.returnBulletsList().get(i));
		}
		
		
		for(int i = 0;i < knifeMngr.returnKnifesList().size();i++)
		{
			theFinalList.add(knifeMngr.returnKnifesList().get(i));
		}
		
		return theFinalList;
	}
	
	
	public void renderWeapons(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnWeaponsList().size(); i++) {
			Weapon aWeapon = this.returnWeaponsList().get(i);
			
			aWeapon.draw(g);
		}
	}

}
