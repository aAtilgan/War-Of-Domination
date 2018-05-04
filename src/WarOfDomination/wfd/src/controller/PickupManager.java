package controller;

/**
 * @author Akant
 *
 */
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.HealthBox;
import model.personmodel.Moving;
import model.weaponmodel.AmmoBox;

public class PickupManager 
{

	//List of pickup objects
	ArrayList<AmmoBox> listOfAmmoBoxes;
	ArrayList<HealthBox> listOfHealthBoxes;
	
	//Grab radius of characters
	static final int GRAB_RADIUS = 10;
	public PickupManager()
	{
		//initialize lists
		listOfAmmoBoxes = new ArrayList<AmmoBox>();
		listOfHealthBoxes = new ArrayList<HealthBox>();
	}
	
	//Adds ammobox to a specified location
	public void addAmmoBox(float x,float y,int amount) throws SlickException
	{
		listOfAmmoBoxes.add(new AmmoBox(x,y,amount));
	}
	
	//Adds hpbox to a specified location
	public void addHpBox(float x,float y,int amount) throws SlickException
	{
		listOfHealthBoxes.add(new HealthBox(x,y,amount));
	}
	
	//Checks collision between characters and pickup objects. Objects are "picked up" when collided.
	public void update(Moving character1,Moving character2)
	{
		//get locations
		float character1X = character1.getX();
		float character1Y = character1.getY();
		float character2X = character2.getX();
		float character2Y = character2.getY();
		
		
		//Check ammo box collision
		for(int i = 0; i < listOfAmmoBoxes.size();i++)
		{
			if (Math.hypot(character1X - listOfAmmoBoxes.get(i).getX(), character1Y - listOfAmmoBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				//reload character
				character1.reload();
				listOfAmmoBoxes.remove(i);
			}
			
			else if (Math.hypot(character2X - listOfAmmoBoxes.get(i).getX(), character2Y - listOfAmmoBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				//reload character
				character2.reload();
				listOfAmmoBoxes.remove(i);
			}
		}
		
		//Check hp box collision
		for(int i = 0;i < listOfHealthBoxes.size();i++)
		{
			if (Math.hypot(character1X - listOfHealthBoxes.get(i).getX(), character1Y - listOfHealthBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				//heal character
				character1.heal(listOfHealthBoxes.get(i).getAmount());
				listOfHealthBoxes.remove(i);
			}
			
			else if (Math.hypot(character2X - listOfHealthBoxes.get(i).getX(), character2Y - listOfHealthBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				//heal character
				character2.heal(listOfHealthBoxes.get(i).getAmount());
				listOfHealthBoxes.remove(i);
			
			}
		}
	}
	
	public ArrayList<AmmoBox> getAmmoBoxList()
	{
		return this.listOfAmmoBoxes;
	}
	
	public ArrayList<HealthBox> getHpBoxList()
	{
		return this.listOfHealthBoxes;
	}
	
	//Renders all pickup objects.
	public void renderPickups(Graphics g)
	{
		
		//Render AmmoBoxes
		for(int i = 0; i < listOfAmmoBoxes.size();i++)
		{
			listOfAmmoBoxes.get(i).draw(g);
		}
		//Render HPboxes
		for(int i = 0; i < listOfHealthBoxes.size();i++)
		{
			listOfHealthBoxes.get(i).draw(g);
		}
	}
}
