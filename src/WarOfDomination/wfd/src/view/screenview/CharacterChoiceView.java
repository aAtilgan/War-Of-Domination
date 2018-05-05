package view.screenview;

/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import model.RoundData;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class CharacterChoiceView extends BasicGameState {
	private boolean isFinished;
	// Character choices
	private int pl1Choice;
	private int pl2Choice;
	// Weapon lists
	private ArrayList<Integer> pl1WeaponChoice, pl2WeaponChoice;
	// Selection rectangle positions
	private int posX, posY;
	private int pos2X, pos2Y;
	// Images
	Image hero, enemy, ninja, kel, mavi, m4a1, dagger, shotgun, rifle, auto;

	// Default constructor
	public CharacterChoiceView(int characterChoice) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// Character pictures
		hero = new Image("res/hero_choice.png");
		ninja = new Image("res/ninja_choice.png");
		enemy = new Image("res/enemy_choice.png");
		kel = new Image("res/kel_choice.png");
		mavi = new Image("res/mavi_choice.png");
		// Weapon pictures
		m4a1 = new Image("res/m4_a1_menu.png");
		dagger = new Image("res/Dagger_Douche_menu.png");
		shotgun = new Image("res/shotgun_menu.png");
		rifle = new Image("res/rifle_menu.png");
		auto = new Image("res/Assault_Rifle_menu.png");

		// initialize the logic
		pl1Choice = -1;
		pl2Choice = -1;
		isFinished = false;
		posX = 1000;
		posY = 100;
		pos2X = 1000;
		pos2Y = 100;
		pl1WeaponChoice = new ArrayList<Integer>();
		pl2WeaponChoice = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			pl1WeaponChoice.add(-1);
			pl2WeaponChoice.add(-1);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		// Draw outline
		g.setColor(org.newdawn.slick.Color.white);
		g.drawString("Pick your Character", 400, 0);
		g.drawString("Pick your Weapons", 410, 360);
		g.setColor(org.newdawn.slick.Color.white);
		g.fillRect(0, 340, 1000, 20);

		// Draw Hero images
		g.drawImage(hero, 50, 100);
		g.drawImage(ninja, 250, 100);
		g.drawImage(enemy, 450, 100);
		g.drawImage(kel, 650, 100);
		g.drawImage(mavi, 850, 100);
		drawSelectionRectangles(g);

		// DRAW CHARACTER INFORMATION PANELS
		if (pl1Choice == 1 || pl2Choice == 1) {
			g.setColor(Color.green);
			g.drawString("Health: 400", 50, 200);
			g.drawString("Speed: 10", 50, 212);
			g.drawString("Damage: 5", 50, 224);
		}

		if (pl1Choice == 2 || pl2Choice == 2) {
			g.setColor(Color.green);
			g.drawString("Health: 350", 250, 200);
			g.drawString("Speed: 15", 250, 212);
			g.drawString("Damage: 4", 250, 224);
		}

		if (pl1Choice == 3 || pl2Choice == 3) {
			g.setColor(Color.green);
			g.drawString("Health: 300", 450, 200);
			g.drawString("Speed: 5", 450, 212);
			g.drawString("Damage: 40", 450, 224);
		}

		if (pl1Choice == 4 || pl2Choice == 4) {
			g.setColor(Color.green);
			g.drawString("Health: 1000", 650, 200);
			g.drawString("Speed: 5", 650, 212);
			g.drawString("Damage: 2", 650, 224);
		}

		if (pl1Choice == 5 || pl2Choice == 5) {
			g.setColor(Color.green);
			g.drawString("Health: 500", 850, 200);
			g.drawString("Speed: 10", 850, 212);
			g.drawString("Damage: 3", 850, 224);
		}

		// Draw weapon images
		g.drawImage(m4a1, 50, 425);
		g.drawImage(dagger, 250, 400);
		g.drawImage(shotgun, 450, 400);
		g.drawImage(rifle, 650, 425);
		g.drawImage(auto, 850, 430);

		// Draw weapon selection strings
		g.setColor(Color.white);
		for (int i = 0; i < pl1WeaponChoice.size(); i++) {
			if (pl1WeaponChoice.get(i) == 1)
				g.drawString("Assault Rifle", 200, 580 - i * 20);
			else if (pl1WeaponChoice.get(i) == 2)
				g.drawString("Throwing Knife", 200, 580 - i * 20);
			else if (pl1WeaponChoice.get(i) == 3)
				g.drawString("Shotgun", 200, 580 - i * 20);
			else if (pl1WeaponChoice.get(i) == 4)
				g.drawString("Rifle", 200, 580 - i * 20);
			else if (pl1WeaponChoice.get(i) == 5)
				g.drawString("Automatic Rifle", 200, 580 - i * 20);
		}

		for (int i = 0; i < pl2WeaponChoice.size(); i++) {
			if (pl2WeaponChoice.get(i) == 1)
				g.drawString("Assault Rifle", 650, 580 - i * 20);
			else if (pl2WeaponChoice.get(i) == 2)
				g.drawString("Throwing Knife", 650, 580 - i * 20);
			else if (pl2WeaponChoice.get(i) == 3)
				g.drawString("Shotgun", 650, 580 - i * 20);
			else if (pl2WeaponChoice.get(i) == 4)
				g.drawString("Rifle", 650, 580 - i * 20);
			else if (pl2WeaponChoice.get(i) == 5)
				g.drawString("Automatic Rifle", 650, 580 - i * 20);
		}

	}

	// Adds selected weapon to corresponding players list
	public void addWeapon(ArrayList<Integer> listToAdd, int selection) {
		if (listToAdd.contains(selection) == false) {
			if (listToAdd.size() >= 3) {
				listToAdd.remove(0);
			}
			listToAdd.add(selection);
		}
	}

	// Draws a rectangle around selected character to show the selection success
	public void drawSelectionRectangles(Graphics g) {
		// Character selection
		g.setColor(org.newdawn.slick.Color.red);
		g.drawRect(posX - 2, posY - 2, 104, 104);
		g.drawString("Player1", posX, posY - 20);
		g.setColor(org.newdawn.slick.Color.blue);
		g.drawRect(pos2X - 4, pos2Y - 4, 108, 108);
		g.drawString("Player2", pos2X, pos2Y - 20);

		// Weapon Selection
		g.setColor(org.newdawn.slick.Color.blue);
		g.drawString("Player1 Weapons:", 200, 520);
		g.setColor(org.newdawn.slick.Color.red);
		g.drawString("Player2 Weapons:", 650, 520);
	}

	@Override
	/*
	 * This function catches user character and weapon selections by checking simple
	 * coordinate constraints. If user clicks between these constraints this
	 * function does the required action
	 * 
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		// Left mouse click(Player 1)
		if (Mouse.isButtonDown(0)) {
			// character choice locations
			if (700 - Mouse.getY() > 100 && 700 - Mouse.getY() < 200) {
				if (Mouse.getX() > 50 && Mouse.getX() < 150) {
					pl1Choice = 1;
					this.posX = 50;
				}

				if (Mouse.getX() > 250 && Mouse.getX() < 350) {
					pl1Choice = 2;
					this.posX = 250;
				}

				if (Mouse.getX() > 450 && Mouse.getX() < 550) {
					pl1Choice = 3;
					this.posX = 450;
				}

				if (Mouse.getX() > 650 && Mouse.getX() < 750) {
					pl1Choice = 4;
					this.posX = 650;
				}

				if (Mouse.getX() > 850 && Mouse.getX() < 950) {
					pl1Choice = 5;
					this.posX = 850;
				}
			}

			// Weapon choice locations
			if (700 - Mouse.getY() > 400 && 700 - Mouse.getY() < 500) {
				if (Mouse.getX() > 50 && Mouse.getX() < 150) {
					this.addWeapon(pl1WeaponChoice, 1);
				}

				if (Mouse.getX() > 250 && Mouse.getX() < 350) {
					this.addWeapon(pl1WeaponChoice, 2);
				}

				if (Mouse.getX() > 450 && Mouse.getX() < 550) {
					this.addWeapon(pl1WeaponChoice, 3);
				}

				if (Mouse.getX() > 650 && Mouse.getX() < 750) {
					this.addWeapon(pl1WeaponChoice, 4);
				}

				if (Mouse.getX() > 850 && Mouse.getX() < 950) {
					this.addWeapon(pl1WeaponChoice, 5);
				}

			}

		}

		// Right mouse click(Player 2)
		else if (Mouse.isButtonDown(1)) {
			// character choice locations
			if (700 - Mouse.getY() > 100 && 700 - Mouse.getY() < 200) {
				if (Mouse.getX() > 50 && Mouse.getX() < 150) {
					pl2Choice = 1;
					this.pos2X = 50;
				}

				if (Mouse.getX() > 250 && Mouse.getX() < 350) {
					pl2Choice = 2;
					this.pos2X = 250;
				}

				if (Mouse.getX() > 450 && Mouse.getX() < 550) {
					pl2Choice = 3;
					this.pos2X = 450;
				}

				if (Mouse.getX() > 650 && Mouse.getX() < 750) {
					pl2Choice = 4;
					this.pos2X = 650;
				}

				if (Mouse.getX() > 850 && Mouse.getX() < 950) {
					pl2Choice = 5;
					this.pos2X = 850;
				}
			}

			// Weapon choice locations
			if (700 - Mouse.getY() > 400 && 700 - Mouse.getY() < 500) {
				if (Mouse.getX() > 50 && Mouse.getX() < 150) {
					this.addWeapon(pl2WeaponChoice, 1);
				}

				if (Mouse.getX() > 250 && Mouse.getX() < 350) {
					this.addWeapon(pl2WeaponChoice, 2);
				}

				if (Mouse.getX() > 450 && Mouse.getX() < 550) {
					this.addWeapon(pl2WeaponChoice, 3);
				}

				if (Mouse.getX() > 650 && Mouse.getX() < 750) {
					this.addWeapon(pl2WeaponChoice, 4);
				}

				if (Mouse.getX() > 850 && Mouse.getX() < 950) {
					this.addWeapon(pl2WeaponChoice, 5);
				}

			}
		}

		// Finish selection by pressing enter
		else if (Keyboard.isKeyDown(Input.KEY_ENTER)) {
			isFinished = true;
		}

		// Send required data to RoundData class when selection is finished
		else if (isFinished) {
			// Player 1 Choice
			if (pl1Choice == 1)
				RoundData.character_choice_hero = "ch";
			else if (pl1Choice == 2)
				RoundData.character_choice_hero = "ninja";
			else if (pl1Choice == 3)
				RoundData.character_choice_hero = "ch1";
			else if (pl1Choice == 4)
				RoundData.character_choice_hero = "kel";
			else if (pl1Choice == 5)
				RoundData.character_choice_hero = "mavi";

			// Player 2 Choice
			if (pl2Choice == 1)
				RoundData.character_choice_enemy = "ch";
			else if (pl2Choice == 2)
				RoundData.character_choice_enemy = "ninja";
			else if (pl2Choice == 3)
				RoundData.character_choice_enemy = "ch1";
			else if (pl2Choice == 4)
				RoundData.character_choice_enemy = "kel";
			else if (pl2Choice == 5)
				RoundData.character_choice_enemy = "mavi";

			// Weapon choices
			RoundData.weapon_choice_hero = pl1WeaponChoice;
			RoundData.weapon_choice_enemy = pl2WeaponChoice;
			// State Change
			sbg.getState(1).init(gc, sbg);
			sbg.enterState(1);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 8;
	}

}
