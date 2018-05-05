package controller.weaponcontrol;

/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.tiled.TiledMap;

import model.RoundData;
import model.personmodel.Moving;
import model.weaponmodel.Knife;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import java.util.*;

public class KnifeManager {

	ArrayList<Knife> listOfKnifes;
	Moving ch, shooter;
	Sound knife_sound;

	float characterX, characterY;

	public KnifeManager() throws SlickException {
		// initialize sounds and list of knifes
		listOfKnifes = new ArrayList<Knife>();
		knife_sound = new Sound("res/knife_sound.ogg");
	}

	/*
	 * This function creates a knife object to track the projectile. It uses a
	 * vector to calculate the angle between the shooters location and target
	 * location
	 */
	public void throwKnife(int x, int y, float sourceX, float sourceY, Moving shooter, Moving enemy)
			throws SlickException {

		// No need for ammunition check for knife it has infinite ammo.
		Vector2f diffvector = new Vector2f((float) (x - sourceX), (float) (y - sourceY));
		float angle = (float) diffvector.getTheta();

		Knife aKnife = new Knife(x, 700 - y, sourceX, sourceY, angle, enemy);
		listOfKnifes.add(aKnife);
		knife_sound.play(1, 0.06f);
	}

	/*
	 * This function deletes the knife if it collides with a player or an obstacle
	 * on the map. This function updates a SINGLE knife at a time.
	 */
	public void updateKnife(Knife aKnife, float mapPosX, float mapPosY, TiledMap map) {
		// Get maps objects layer for collusion check
		int layerIndex = map.getLayerIndex("Objects");
		// Extract target data from knife
		ch = aKnife.getTarget();
		// Get target locations
		characterX = ch.getX();
		characterY = ch.getY();

		// Checks if the knife is outside of the map or not. Deletes knife if outside.
		if (((aKnife.getX() - mapPosX) / 32 > 0) && ((aKnife.getY() - mapPosY) / 32 > 0)
				&& ((aKnife.getY() - mapPosY) / 32 < 30) && ((aKnife.getX() - mapPosX) / 32 < 34)) {
			// Checks if the knife collides with an obstacle on the map
			if (map.getTileId((((int) (aKnife.getX() - mapPosX) / 32)), ((int) (aKnife.getY() - mapPosY) / 32),
					layerIndex) == 0) {
				// Updates Knife location.
				aKnife.setX(aKnife.getX() + (float) (1 * Math.cos(Math.toRadians(aKnife.getangle()))));
				aKnife.setY(aKnife.getY() + (float) (1 * Math.sin(Math.toRadians(aKnife.getangle()))));
				// If the Knife collides with character it gets deleted.
				// (Done by proximity check between center of character and Knife location)
				if (Math.hypot(characterX - aKnife.getX(), characterY - aKnife.getY()) < 20) {
					listOfKnifes.remove(aKnife);
					// Reduce character life
					ch.setHealth(ch.getHealth() - 10);

					// Update scores
					if (ch.getCharacter_id() == 0)
						RoundData.given_damage_enemy += 10;
					else
						RoundData.given_damage_hero += 10;
				}

			} else
				listOfKnifes.remove(aKnife);
		}

		else {
			listOfKnifes.remove(aKnife);
		}
	}

	// Return listOfKnifes
	public ArrayList<Knife> returnKnifesList() {
		return this.listOfKnifes;
	}

	// Renders all Knifes in the list.
	public void renderBullets(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.returnKnifesList().size(); i++) {
			Knife aKnife = this.returnKnifesList().get(i);

			aKnife.draw(g);
			System.out.println("ENTERED");
		}
	}

}