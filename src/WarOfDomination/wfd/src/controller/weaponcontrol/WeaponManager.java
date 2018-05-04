package controller.weaponcontrol;
/**
 * @author Akant
 *
 */
import org.newdawn.slick.tiled.TiledMap;

import model.RoundData;
import model.personmodel.Moving;
import model.weaponmodel.Bullet;
import model.weaponmodel.Knife;
import model.weaponmodel.Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.*;

public class WeaponManager {

	//List of weapon objects
	ArrayList<Weapon> listOfWeapons;
	//Child Managers
	BulletManager bulletMngr;
	KnifeManager knifeMngr;
	//Temp. character object
	Moving ch;

	//Character location
	float characterX,characterY;
	public WeaponManager() throws SlickException 
	{
		
		//initializes the list and child managers.
		listOfWeapons = new ArrayList<Weapon>();
		bulletMngr = new BulletManager();
		knifeMngr = new KnifeManager();

	}

	/*This class calls the corresponding method from one of it's helper managers. This call is done after 2 checks.
	 * 1- Checks the currently selected weapon of the shooting players character.(#Parameter  int type)
	 * 2- Checks the shooting characters weapon selection from menu.(ArrayList<Integer> RoundData.weapon_choice_hero)
	 * According to which of these checks are passed a shooting command is called.
	 */
	public void shoot(int x, int y, float sourceX, float sourceY,Moving enemy,Moving shooter,int type) throws SlickException
	{
		if(type == 2)
		{
			if(shooter.character_id == 0)
			{
				if(RoundData.weapon_choice_hero.get(0) == 1 || RoundData.weapon_choice_hero.get(0) == 4 || RoundData.weapon_choice_hero.get(0) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_hero.get(0) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_hero.get(0) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
				
			}
			else if(shooter.character_id == 1)
				if(RoundData.weapon_choice_enemy.get(0) == 1 || RoundData.weapon_choice_enemy.get(0) == 4 || RoundData.weapon_choice_enemy.get(0) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_enemy.get(0) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_enemy.get(0) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
		}
		else if(type == 1)
		{
			if(shooter.character_id == 0)
			{
				if(RoundData.weapon_choice_hero.get(1) == 1 || RoundData.weapon_choice_hero.get(1) == 4 || RoundData.weapon_choice_hero.get(1) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_hero.get(1) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_hero.get(1) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
				
			}
			else if(shooter.character_id == 1)
				if(RoundData.weapon_choice_enemy.get(1) == 1 || RoundData.weapon_choice_enemy.get(1) == 4 || RoundData.weapon_choice_enemy.get(1) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_enemy.get(1) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_enemy.get(1) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
		}
		
		else if(type == 0)
		{
			if(shooter.character_id == 0)
			{
				if(RoundData.weapon_choice_hero.get(2) == 1 || RoundData.weapon_choice_hero.get(2) == 4 || RoundData.weapon_choice_hero.get(2) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_hero.get(2) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_hero.get(2) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
				
			}
			else if(shooter.character_id == 1)
				if(RoundData.weapon_choice_enemy.get(2) == 1 || RoundData.weapon_choice_enemy.get(2) == 4 || RoundData.weapon_choice_enemy.get(2) == 5)
					bulletMngr.shoot(x, y, sourceX, sourceY, shooter,enemy);
				else if(RoundData.weapon_choice_enemy.get(2) == 2)
				{
					knifeMngr.throwKnife(x, y, sourceX, sourceY, shooter,enemy);
				}
				else if(RoundData.weapon_choice_enemy.get(2) == 3)
				{
					bulletMngr.shootShotgun(x, y, sourceX, sourceY, shooter, enemy);
				}
		}
	}
	
	
	
/*
 * This class updates all of the projectile objects on the map by calling their update methods individually.
 * Knifes and bullets might have different trajectory behaviours.
 */
	public void updateWeapon(Weapon aWeapon, float screenPosX, float screenPosY, TiledMap map,int type ) 
	{
		if(type == 0)
			bulletMngr.updateBullet((Bullet) aWeapon, screenPosX, screenPosY, map);
		else if(type == 1)
			knifeMngr.updateKnife((Knife) aWeapon, screenPosX, screenPosY, map);
	}
	
	
/*
 * Stores both Knives and Bullets into a single list under <Weapon> category.
 */
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
	
	/*
	 * Calls the render function of all projectiles in this classes listOfWeapons ArrayList
	 * Takes Graphics g as a parameter to pass it over.
	 */
	public void renderWeapons(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnWeaponsList().size(); i++) {
			Weapon aWeapon = this.returnWeaponsList().get(i);
			aWeapon.draw(g);
		}
	}

}
