package controller;

import org.newdawn.slick.tiled.TiledMap;

import model.Knife;
import model.Moving;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class KnifeManager {

	ArrayList<Knife> listOfKnifes;
	Moving ch;

	float characterX,characterY;
	public KnifeManager() 
	{
		listOfKnifes = new ArrayList<Knife>();
	}

	
	public void throwKnife(int x, int y, float sourceX, float sourceY,Moving shooter,Moving enemy) throws SlickException{

		
		Vector2f diffvector = new Vector2f((float) (x - sourceX),(float) (y - sourceY));
		float angle = (float) diffvector.getTheta();
		
		Knife aKnife = new Knife(x, 700 - y, sourceX, sourceY,angle,enemy);
		listOfKnifes.add(aKnife);
	}
	
	
	
	

	public void updateKnife(Knife aKnife, float screenPosX, float screenPosY, TiledMap map) 
	{
		int layerIndex = map.getLayerIndex("Objects");
		ch = aKnife.getTarget();
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

		
		if (((aKnife.getX() - screenPosX) / 32 > 0) && ((aKnife.getY() - screenPosY) / 32 > 0) // harita dýsý check
				&& ((aKnife.getY() - screenPosY) / 32 < 30) && ((aKnife.getX() - screenPosX) / 32 < 34)) { 
			if (map.getTileId((((int) (aKnife.getX() - screenPosX) / 32)), ((int) (aKnife.getY() - screenPosY) / 32), // tile'a carpýyo mu check
					layerIndex) == 0) 
			{
				aKnife.setX(aKnife.getX() + (float) (1 * Math.cos(Math.toRadians(aKnife.getangle()))));
				aKnife.setY(aKnife.getY() + (float) (1 * Math.sin(Math.toRadians(aKnife.getangle()))));
				if (Math.hypot(characterX - aKnife.getX(), characterY - aKnife.getY()) < 6) 
				{ 
					//HITS CHARACTER
					listOfKnifes.remove(aKnife);
					ch.setHealth(ch.getHealth()-10);
				}
				
			} 
			else
				listOfKnifes.remove(aKnife);
		}

		else 
		{
			listOfKnifes.remove(aKnife);

		}
	}

	public ArrayList<Knife> returnKnifesList() {
		return this.listOfKnifes;
	}
	
	
	public void renderBullets(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnKnifesList().size(); i++) {
			Knife aKnife = this.returnKnifesList().get(i);
			
			aKnife.draw(g);
			System.out.println("ENTERED");
		}
	}

}