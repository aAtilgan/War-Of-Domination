package controller;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.AmmoBox;
import model.HealthBox;
import model.Moving;

public class PickupManager 
{

	ArrayList<AmmoBox> listOfAmmoBoxes;
	ArrayList<HealthBox> listOfHealthBoxes;
	
	static final int GRAB_RADIUS = 10;
	public PickupManager()
	{
		listOfAmmoBoxes = new ArrayList<AmmoBox>();
		listOfHealthBoxes = new ArrayList<HealthBox>();
	}
	
	public void addAmmoBox(float x,float y,int amount) throws SlickException
	{
		listOfAmmoBoxes.add(new AmmoBox(x,y,amount));
	}
	
	public void addHpBox(float x,float y,int amount) throws SlickException
	{
		listOfHealthBoxes.add(new HealthBox(x,y,amount));
	}
	
	public void update(Moving character1,Moving character2)
	{
		float character1X = character1.getX();
		float character1Y = character1.getY();
		float character2X = character2.getX();
		float character2Y = character2.getY();
		for(int i = 0; i < listOfAmmoBoxes.size();i++)
		{
			
			
			if (Math.hypot(character1X - listOfAmmoBoxes.get(i).getX(), character1Y - listOfAmmoBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				character1.reload();
				listOfAmmoBoxes.remove(i);
			}
			
			else if (Math.hypot(character2X - listOfAmmoBoxes.get(i).getX(), character2Y - listOfAmmoBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				character2.reload();
				listOfAmmoBoxes.remove(i);
			}
		}
		
		for(int i = 0;i < listOfHealthBoxes.size();i++)
		{
			if (Math.hypot(character1X - listOfHealthBoxes.get(i).getX(), character1Y - listOfHealthBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
				character1.heal(listOfHealthBoxes.get(i).getAmount());
				listOfHealthBoxes.remove(i);
			}
			
			else if (Math.hypot(character2X - listOfHealthBoxes.get(i).getX(), character2Y - listOfHealthBoxes.get(i).getY()) < GRAB_RADIUS) 
			{ 
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
	
	public void renderPickups(Graphics g)
	{
		for(int i = 0; i < listOfAmmoBoxes.size();i++)
		{
			listOfAmmoBoxes.get(i).draw(g);
		}
		
		for(int i = 0; i < listOfHealthBoxes.size();i++)
		{
			listOfHealthBoxes.get(i).draw(g);
		}
	}
}
