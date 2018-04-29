package controller.weaponcontrol;

import org.newdawn.slick.tiled.TiledMap;

import model.RoundData;
import model.personmodel.Moving;
import model.weaponmodel.Knife;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class KnifeManager {

	ArrayList<Knife> listOfKnifes;
	Moving ch,shooter;

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


	public void updateKnife(Knife aKnife, float mapPosX, float mapPosY, TiledMap map) 
	{
		int layerIndex = map.getLayerIndex("Objects");
		ch = aKnife.getTarget();
		characterX = ch.getX();
		characterY = ch.getY();

		
		if (((aKnife.getX() - mapPosX) / 32 > 0) && ((aKnife.getY() - mapPosY) / 32 > 0) // harita dýsý check
				&& ((aKnife.getY() - mapPosY) / 32 < 30) && ((aKnife.getX() - mapPosX) / 32 < 34)) { 
			if (map.getTileId((((int) (aKnife.getX() - mapPosX) / 32)), ((int) (aKnife.getY() - mapPosY) / 32), // tile'a carpýyo mu check
					layerIndex) == 0) 
			{
				aKnife.setX(aKnife.getX() + (float) (1 * Math.cos(Math.toRadians(aKnife.getangle()))));
				aKnife.setY(aKnife.getY() + (float) (1 * Math.sin(Math.toRadians(aKnife.getangle()))));
				if (Math.hypot(characterX - aKnife.getX(), characterY - aKnife.getY()) < 10) 
				{ 
					//HITS CHARACTER
					listOfKnifes.remove(aKnife);
					ch.setHealth(ch.getHealth()-10);
					if(ch.getCharacter_id()==0)
						RoundData.given_damage_enemy+=10;
					else
						RoundData.given_damage_hero+=10;
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