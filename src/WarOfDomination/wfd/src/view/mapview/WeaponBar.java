package view.mapview;
/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import model.RoundData;

import org.newdawn.slick.*;

public class WeaponBar {
	//Weapon images
	Image img;
	Image img2;
	Image img3;
	Image img4;
	Image img5;
	//Variable rectangle
	Rectangle bar;
	public WeaponBar() throws SlickException
	{
		//initialize weapon images
		img = new Image("res/m4_a1.png");
		img2 = new Image("res/Dagger_Douche.png");
		img3 = new Image("res/shotgun.png");
		img4 = new Image("res/rifle.png");
		img5 = new Image("res/Assault_Rifle.png");
	}
	
	//draws weapon bars according to choice made in CharacterChoiceView screen
	public void render(GameContainer container, Graphics g, int choice,int characterID,int ammo,int shell) throws SlickException 
	{
		
		
		if(characterID == 0)
		{
			//DRAW Selection 1
			g.setColor(Color.black);
			bar = new Rectangle(4, 500, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("1", 4, 536);
			
			//DRAW Selection 2
			g.setColor(Color.black);
			bar = new Rectangle(4, 552, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("2", 4, 588);
			
			//DRAW Selection 3
			g.setColor(Color.black);
			bar = new Rectangle(4, 604, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("3", 4, 640);
			
			
			//DRAW IMAGES AND STRINGS
			//Weapon 1

			if(RoundData.weapon_choice_hero.get(2) == 1)
			{
				g.drawImage(img, 4, 520);
				g.drawString(ammo + "", 36, 536);
			}
			else if(RoundData.weapon_choice_hero.get(2) == 2)
			{
				g.drawImage(img2, 4, 520);
			}
			else if(RoundData.weapon_choice_hero.get(2) == 3)
			{
				g.drawImage(img3, 4 + 5, 520);
				g.drawString(shell + "", 36, 536);
			}
			else if(RoundData.weapon_choice_hero.get(2) == 4)
			{
				g.drawImage(img4, 4, 520);
				g.drawString(ammo + "", 36, 536);
			}
			else if(RoundData.weapon_choice_hero.get(2) == 5)
			{
				g.drawImage(img5, 4, 520);
				g.drawString(ammo + "", 36, 536);
			}
			
			//Weapon 2
			
			if(RoundData.weapon_choice_hero.get(1) == 1)
			{
				g.drawImage(img, 4, 552);
				g.drawString(ammo + "", 36, 552 + 16);
			}
			else if(RoundData.weapon_choice_hero.get(1) == 2)
			{
				g.drawImage(img2, 4, 552);
			}
			else if(RoundData.weapon_choice_hero.get(1) == 3)
			{
				g.drawImage(img3, 4, 552);
				g.drawString(shell + "", 36, 552 + 16);
			}
			else if(RoundData.weapon_choice_hero.get(1) == 4)
			{
				g.drawImage(img4, 4, 552);
				g.drawString(ammo + "", 36, 552 + 16);
			}
			else if(RoundData.weapon_choice_hero.get(1) == 5)
			{
				g.drawImage(img5, 4, 552);
				g.drawString(ammo + "", 36, 552 + 16);
			}
			
			//Weapon 3
			
			if(RoundData.weapon_choice_hero.get(0) == 1)
			{
				g.drawImage(img, 4 + 5,604 + 5);
				g.drawString(ammo + "", 36, 640);
			}
			else if(RoundData.weapon_choice_hero.get(0) == 2)
			{
				g.drawImage(img2, 4 + 5,604 + 5);
			}
			else if(RoundData.weapon_choice_hero.get(0) == 3)
			{
				g.drawImage(img3, 4 + 5,604 + 5);
				g.drawString(shell + "", 36, 640);
			}
			else if(RoundData.weapon_choice_hero.get(0) == 4)
			{
				g.drawImage(img4, 4 + 5,604 + 5);
				g.drawString(ammo + "", 36, 640);
			}
			else if(RoundData.weapon_choice_hero.get(0) == 5)
			{
				g.drawImage(img5, 4 + 5,604 + 5);
				g.drawString(ammo + "", 36, 640);
			}
			
			g.setColor(Color.white);
			
			if(choice == 0)
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 500, 51,51);
				g.draw(bar);
			}
			else if(choice == 1)
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 552, 51,51);
				g.draw(bar);
			}
			else
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 604, 51,51);
				g.draw(bar);
			}
		}
		
		//ENEMY WEAPON BAR
		
		else
		{
			//DRAW Selection 1
			g.setColor(Color.black);
			bar = new Rectangle(946, 500, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("1", 946, 536);
			
			//DRAW Selection 2
			g.setColor(Color.black);
			bar = new Rectangle(946, 552, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("2", 946, 588);
			
			//DRAW Selection 3
			g.setColor(Color.black);
			bar = new Rectangle(946, 604, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("3", 946, 640);
			
			//DRAWIMAGES AND STRINGS
			
			
			if(RoundData.weapon_choice_enemy.get(2) == 1)
			{
				g.drawImage(img, 946, 520);
				g.drawString(ammo + "", 978, 536);
			}
			else if(RoundData.weapon_choice_enemy.get(2) == 2)
			{
				g.drawImage(img2, 946, 520);
			}
			else if(RoundData.weapon_choice_enemy.get(2) == 3)
			{
				g.drawImage(img3, 946 + 5, 520);
				g.drawString(shell + "", 978, 536);
			}
			else if(RoundData.weapon_choice_enemy.get(2) == 4)
			{
				g.drawImage(img4, 946, 520);
				g.drawString(ammo + "", 978, 536);
			}
			else if(RoundData.weapon_choice_enemy.get(2) == 5)
			{
				g.drawImage(img5, 946, 520);
				g.drawString(ammo + "", 978, 536);
			}
			
			//Weapon 2
			
			if(RoundData.weapon_choice_enemy.get(1) == 1)
			{
				g.drawImage(img, 946, 552);
				g.drawString(ammo + "", 978, 552 + 16);
			}
			else if(RoundData.weapon_choice_enemy.get(1) == 2)
			{
				g.drawImage(img2, 946, 552);
			}
			else if(RoundData.weapon_choice_enemy.get(1) == 3)
			{
				g.drawImage(img3, 946, 552);
				g.drawString(shell + "", 978, 552 + 16);
			}
			else if(RoundData.weapon_choice_enemy.get(1) == 4)
			{
				g.drawImage(img4, 946, 552);
				g.drawString(ammo + "", 978, 552 + 16);
			}
			else if(RoundData.weapon_choice_enemy.get(1) == 5)
			{
				g.drawImage(img5, 946, 552);
				g.drawString(ammo + "", 978, 552 + 16);
			}
			
			//Weapon 3
			
			if(RoundData.weapon_choice_enemy.get(0) == 1)
			{
				g.drawImage(img, 946 + 5,604 + 5);
				g.drawString(ammo + "", 978, 640);
			}
			else if(RoundData.weapon_choice_enemy.get(0) == 2)
			{
				g.drawImage(img2, 946 + 5,604 + 5);
			}
			else if(RoundData.weapon_choice_enemy.get(0) == 3)
			{
				g.drawImage(img3, 946 + 5,604 + 5);
				g.drawString(shell + "", 978, 640);
			}
			else if(RoundData.weapon_choice_enemy.get(0) == 4)
			{
				g.drawImage(img4, 946 + 5,604 + 5);
				g.drawString(ammo + "", 978, 640);
			}
			else if(RoundData.weapon_choice_enemy.get(0) == 5)
			{
				g.drawImage(img5, 946 + 5,604 + 5);
				g.drawString(ammo + "", 978, 640);
			}
			g.setColor(Color.white);
			
			if(choice == 0)
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 500, 51,51);
				g.draw(bar);
				g.setColor(Color.white);
				
			}
			else if(choice == 1)
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 552, 51,51);
				g.draw(bar);
			}
			else
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 604, 51,51);
				g.draw(bar);
			}
		}
		
	}
}
