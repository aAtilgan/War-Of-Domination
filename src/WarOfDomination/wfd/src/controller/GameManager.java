package controller;

/**
 * @author Akant
 * @author Ayberk
 */
import java.util.ArrayList;
import java.awt.Font;

/**
 * @author Ayberk
 *
 */
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.TrueTypeFont;
import controller.personcontrol.CharacterController;
import controller.personcontrol.EnemyController;
import controller.weaponcontrol.WeaponManager;
import model.HealthBox;
import model.RoundData;
import model.Common.Direction;
import model.personmodel.Character;
import model.personmodel.Enemy;
import model.weaponmodel.AmmoBox;
import model.weaponmodel.Knife;
import model.weaponmodel.Weapon;
import view.CharacterView;
import view.EnemyView;
import view.screenview.MapView;

public class GameManager extends BasicGameState {
	private static final int game_over = 4;
	private static final int story_mode = 7;
	// To show the scores of the players
	public static int hero_win = 0;
	public static int enemy_win = 0;
	// Character Properties
	Character hero;
	CharacterView heroView;
	int winner = -1;
	int timer;
	boolean lockFont;
	// Fonts
	org.newdawn.slick.Font original;
	TrueTypeFont fancy;

	CharacterController chControl;

	// Enemy Properties
	Enemy enemy;
	EnemyView enemyView;
	EnemyController enemyControl;

	// Map Properties
	MapControl map;
	MapView mapView;

	// Sound Properties
	Sound sound;

	// Input Properties
	InputManager in;
	// Map Names
	String map1_name = "res/timmy_map.tmx";
	String map2_name = "res/new_map_2.tmx";
	String map3_name = "res/new_map_3.tmx";
	// Additional Variables
	boolean isShoot = false;
	int objectLayer;
	// character random talk stuff
	int random;
	int random_count = 1;

	// Background Image
	Image background;
	// Helper Manager Initialization
	WeaponManager weaponManager;
	PickupManager pickupManager;
	// Locks the mouse input until it's released
	boolean lock = false;
	// Renders map based on player 1
	boolean renderRespectToHero = true;
	int direction;

	// Constructor
	public GameManager(int worldmap) throws SlickException {
	}

	@Override
	// Initialization method. This method is called first when SBG is changed to
	// this classes id.
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// Initialize rounddatas
		RoundData.num_of_bullet_enemy = 0;
		RoundData.num_of_bullet_hero = 0;
		RoundData.given_damage_enemy = 0;
		RoundData.given_damage_hero = 0;

		timer = 0;
		lockFont = false;
		// Create new managers each time the game starts to make sure the calculations
		// are not over-done.
		weaponManager = new WeaponManager();
		pickupManager = new PickupManager();
		in = new InputManager();
		// Initialize map and map controller
		map = new MapControl();
		map.setX(0);
		map.setY(0);
		fancy = new TrueTypeFont(new Font("Time New Roman", Font.PLAIN, 40), true);

		// Set maps to rounds
		if (RoundData.round == 1) {
			map.loadMap(map1_name);
		} else if (RoundData.round == 2) {
			map.loadMap(map2_name);
		} else if (RoundData.round == 3) {
			map.loadMap(map3_name);
		}

		mapView = new MapView(map.getMap());

		// Initialize player 1 character.
		// Character is set to a different location in each map to make sure it doesn't
		// spawn on obstacle.
		hero = new Character(0);
		heroView = new CharacterView(RoundData.character_choice_hero);
		hero.addObserver(heroView);
		if (RoundData.round == 1) {
			hero.setX(320);
			hero.setY(320);

			// Add additional pickupable items
			pickupManager.addHpBox(300, 450, 30);
			pickupManager.addHpBox(300, 450, 30);
		} else if (RoundData.round == 2) {
			hero.setX(170);
			hero.setY(320);

			// Add additional pickupable items
			pickupManager.addHpBox(300, 450, 30);
		} else if (RoundData.round == 3) {
			// 3.HERO MAP
			hero.setX(170);
			hero.setY(320);
			// Add additional pickupable items
		}
		chControl = new CharacterController(heroView, map);
		hero.notifyObservers();

		// Initialize player 2 character.
		// Character is set to a different location in each map to make sure it doesn't
		// spawn on obstacle.
		enemy = new Enemy(1);
		enemyView = new EnemyView(RoundData.character_choice_enemy);
		enemy.addObserver(enemyView);
		if (RoundData.round == 1) {
			enemy.setX(500);
			enemy.setY(500);
		} else if (RoundData.round == 2) {
			enemy.setX(750);
			enemy.setY(500);
		} else if (RoundData.round == 3) {
			// 3.Enemy MAP
			enemy.setX(700);
			enemy.setY(400);
		}
		enemyControl = new EnemyController(enemyView, map);
		enemy.notifyObservers();
		// Default direction for characters when game starts
		direction = 0;
	}

	@Override

	/*
	 * This function is used as the main render function of the play game screen; it
	 * calls sub-render functions and draw() functions of other classes.
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		// Render the map on the screen
		if (renderRespectToHero)
			mapView.render(map.getX(), map.getY());
		g.setColor(Color.white);
		if (lockFont == false) {
			original = g.getFont();
			lockFont = true;
		}
		// Round starter image
		else if (timer > 0 && timer < 20000 && lockFont) {
			g.setFont(fancy);
			g.setColor(Color.red);
			g.drawString("Round " + RoundData.round, 500 - fancy.getWidth("Round " + RoundData.round) / 2,
					350 - fancy.getHeight("Round " + RoundData.round) / 2);
			g.setFont(original);
		}
		if (timer > 10000 && timer < 20000) {
			g.setFont(fancy);
			g.setColor(Color.red);
			g.drawString("Fight!", 500 - fancy.getWidth("Fight!") / 2, 400 - fancy.getHeight("Fight!") / 2);
			g.setFont(original);
		}

		if (timer > 20000) {
			g.setColor(Color.red);
			g.drawString("Round " + RoundData.round, 500 - original.getWidth("Round 1") / 2, 0);
		}

		// Draw random talk
		g.setColor(Color.red);
		g.drawString("Player1", hero.getX() - 30, hero.getY() - 35);
		g.setColor(Color.blue);
		g.drawString("Player2", enemy.getX() - 30, enemy.getY() - 35);
		// Draw characters of players
		heroView.draw(gc, g);
		enemyView.draw(gc, g);
		/*
		 * Weapons are only rendered when there are bullets on the screen. This way
		 * performance is improved slightly by avoiding excessive render function calls
		 */
		if (isShoot) {
			weaponManager.renderWeapons(g);
		}
		// Pickups are rendered on top of the map.
		pickupManager.renderPickups(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		timer = timer + 30;
		// Sets random talk by 1/500 chance on each update of the game.
		random = (int) (Math.random() * 500);
		if (random == 5) {
			random_count = (int) (Math.random() * 11);
		}

		// Checks game over state and updates every object on the map if game is not
		// over.
		if (!this.isGameOver()) {

			// Disable character move until "FIGHT!" appears
			if (timer > 10000)
				in.getInputs(gc, sbg, delta);
			isShoot = updateBullets();
			pickupManager.update(hero, enemy);
		} else {
			// Adds data to RoundData class to be displayed on next state.
			if (winner == 1) {
				enemy_win++;
			} else {
				hero_win++;
			}
			// Changes game state and checks final round condition
			if (RoundData.round == 4) {
				sbg.getState(game_over).init(gc, sbg);
				sbg.enterState(game_over);
			} else {
				sbg.getState(story_mode).init(gc, sbg);
				sbg.enterState(story_mode);
			}

		}

		// Additional checks like pause(These also change states if player decides to
		// exit or pause.

		if (in.key.paused) {
			sbg.enterState(3);
			in.key.paused = !in.key.paused;
		}
		if (in.key.set) {
			sbg.enterState(2);
			in.key.set = !in.key.set;
		}
		if (in.key.mained) {
			sbg.enterState(0);
			in.key.mained = !in.key.mained;
		}
	}

	// This function is a helper function for game manager. It checks if there are
	// projectiles travelling at that moment.
	// Returns false if no projectiles exists on the map; updates all projectiles
	// otherwise and returns true.
	public boolean updateBullets() {
		if (weaponManager.returnWeaponsList().size() <= 0)
			return false;
		if (weaponManager.returnWeaponsList() != null)
			for (int i = 0; i < weaponManager.returnWeaponsList().size(); i++) {
				int type = 0;
				if (weaponManager.returnWeaponsList().get(i) instanceof Knife)
					type = 1;
				weaponManager.updateWeapon(weaponManager.returnWeaponsList().get(i), map.getX(), map.getY(),
						map.getMap(), type);
			}

		return true;
	}

	// This function creates the illusion of character locked camera.
	// It pushes every object on the opposite direction of the desired movement
	// direction of players character.
	// It gets all objects on the map from corresponding managers and counter moves
	// them all one by one.
	public void counterMoveAll(Direction a, int delta) {
		// Get all objects
		ArrayList<AmmoBox> listOfAmmoBoxes = pickupManager.getAmmoBoxList();
		ArrayList<HealthBox> listOfHealthBoxes = pickupManager.getHpBoxList();
		ArrayList<Weapon> listOfWeapons = weaponManager.returnWeaponsList();
		if (a == Direction.UP) {
			// Map Counter move
			map.setY(map.getY() + delta * 0.1f);

			// Character Counter move
			enemyView.ch.setY(enemyView.ch.getY() + delta * 0.1f);

			// Box Counter Move
			for (int i = 0; i < listOfAmmoBoxes.size(); i++)
				listOfAmmoBoxes.get(i).setY(listOfAmmoBoxes.get(i).getY() + delta * 0.1f);

			for (int i = 0; i < listOfHealthBoxes.size(); i++)
				listOfHealthBoxes.get(i).setY(listOfHealthBoxes.get(i).getY() + delta * 0.1f);

			// Bullet Counter Move
			for (int i = 0; i < listOfWeapons.size(); i++) {
				listOfWeapons.get(i).setY(listOfWeapons.get(i).getY() + delta * 0.1f);
			}
		}

		if (a == Direction.DOWN) {
			// Map movement
			map.setY(map.getY() - delta * 0.1f);

			// Character Counter move
			enemyView.ch.setY(enemyView.ch.getY() - delta * 0.1f);

			// Box Counter Move
			for (int i = 0; i < listOfAmmoBoxes.size(); i++)
				listOfAmmoBoxes.get(i).setY(listOfAmmoBoxes.get(i).getY() - delta * 0.1f);

			for (int i = 0; i < listOfHealthBoxes.size(); i++)
				listOfHealthBoxes.get(i).setY(listOfHealthBoxes.get(i).getY() - delta * 0.1f);

			// Bullet Counter Move
			for (int i = 0; i < listOfWeapons.size(); i++) {
				listOfWeapons.get(i).setY(listOfWeapons.get(i).getY() - delta * 0.1f);
			}
		}

		if (a == Direction.RIGHT) {
			// Map movement
			map.setX(map.getX() - delta * 0.1f);

			// Character Counter move
			enemyView.ch.setX(enemyView.ch.getX() - delta * 0.1f);

			// Box Counter Move
			for (int i = 0; i < listOfAmmoBoxes.size(); i++)
				listOfAmmoBoxes.get(i).setX(listOfAmmoBoxes.get(i).getX() - delta * 0.1f);

			for (int i = 0; i < listOfHealthBoxes.size(); i++)
				listOfHealthBoxes.get(i).setX(listOfHealthBoxes.get(i).getX() - delta * 0.1f);

			// Bullet Counter Move
			for (int i = 0; i < listOfWeapons.size(); i++) {
				listOfWeapons.get(i).setX(listOfWeapons.get(i).getX() - delta * 0.1f);
			}
		}

		if (a == Direction.LEFT) {
			// Map movement
			map.setX(map.getX() + delta * 0.1f);

			// Character Counter move
			enemyView.ch.setX(enemyView.ch.getX() + delta * 0.1f);

			// Box Counter Move
			for (int i = 0; i < listOfAmmoBoxes.size(); i++)
				listOfAmmoBoxes.get(i).setX(listOfAmmoBoxes.get(i).getX() + delta * 0.1f);

			for (int i = 0; i < listOfHealthBoxes.size(); i++)
				listOfHealthBoxes.get(i).setX(listOfHealthBoxes.get(i).getX() + delta * 0.1f);

			// Bullet Counter Move
			for (int i = 0; i < listOfWeapons.size(); i++) {
				listOfWeapons.get(i).setX(listOfWeapons.get(i).getX() + delta * 0.1f);
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	// Processes user inputs while game is on this classes state.
	public class InputManager {

		// Initialize helper managers
		KeyManager key = new KeyManager();
		MouseManager mouse = new MouseManager();

		// This function tracks user inputs by being called each time on Game Managers
		// update() function
		public void getInputs(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			key.keyInput(gc, sbg, delta);
			mouse.mouseInput(gc, sbg, delta);
		}
	}

	// Helper class for input manager
	public class KeyManager {

		// initialize manager
		public boolean paused = false;
		public boolean set = false;
		public boolean mained = false;

		// This function calls necessary functions when user enters a valid input.
		public void keyInput(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			Input input = gc.getInput();
			boolean character_is_moved = false;

			// PLAYER1 CHARACTER MOVEMENT
			if (input.isKeyDown(Input.KEY_UP)) {

				character_is_moved = chControl.move(Direction.UP, delta);
				if (character_is_moved)
					counterMoveAll(Direction.UP, delta);

			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				character_is_moved = chControl.move(Direction.DOWN, delta);
				if (character_is_moved)
					counterMoveAll(Direction.DOWN, delta);

			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {

				character_is_moved = chControl.move(Direction.RIGHT, delta);
				if (character_is_moved)
					counterMoveAll(Direction.RIGHT, delta);

			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				character_is_moved = chControl.move(Direction.LEFT, delta);
				if (character_is_moved)
					counterMoveAll(Direction.LEFT, delta);
			}
			// Pause Option

			if (input.isKeyDown(Input.KEY_P)) {
				paused = true;
			}

			if (input.isKeyPressed(Input.KEY_O)) {
				set = true;
			}

			if (input.isKeyDown(Input.KEY_ESCAPE)) {

				mained = true;

			}
			// RELOAD
			if (input.isKeyPressed(Input.KEY_RCONTROL)) {
				heroView.ch.reload();
			}

			// CHOOSE WEAPON TYPE PLAYER1
			if (input.isKeyPressed(Input.KEY_NUMPAD1)) {
				hero.setWeaponChoice(0);
			}

			if (input.isKeyPressed(Input.KEY_NUMPAD2)) {
				hero.setWeaponChoice(1);
			}

			if (input.isKeyPressed(Input.KEY_NUMPAD3)) {
				hero.setWeaponChoice(2);
			}

			// PLAYER2 CHARACTER MOVEMENT
			if (input.isKeyDown(Input.KEY_W)) {

				enemyControl.move(Direction.UP, delta);
				direction = 2;
			}
			if (input.isKeyDown(Input.KEY_S)) {
				enemyControl.move(Direction.DOWN, delta);
				direction = 0;

			}
			if (input.isKeyDown(Input.KEY_D)) {
				enemyControl.move(Direction.RIGHT, delta);
				direction = 1;
			}

			if (input.isKeyDown(Input.KEY_A)) {
				enemyControl.move(Direction.LEFT, delta);
				direction = 3;

			}
			// PLAYER2 RELOAD

			if (input.isKeyPressed(Input.KEY_R)) {
				enemyView.ch.reload();
			}

			// PLAYER2 CHARACTER SHOOT
			if (input.isKeyPressed(Input.KEY_LCONTROL)) {
				RoundData.num_of_bullet_enemy++;

				// Player 2 shoots according to the direction it faces.
				if (direction == 0)
					weaponManager.shoot((int) (enemy.getX()), (int) ((enemy.getY()) + 10), enemy.getX(), enemy.getY(),
							hero, enemy, enemy.getWeaponChoice());
				if (direction == 1)
					weaponManager.shoot((int) ((enemy.getX()) + 10), (int) (enemy.getY()), enemy.getX(), enemy.getY(),
							hero, enemy, enemy.getWeaponChoice());
				if (direction == 2)
					weaponManager.shoot((int) (enemy.getX()), (int) ((enemy.getY()) - 10), enemy.getX(), enemy.getY(),
							hero, enemy, enemy.getWeaponChoice());
				if (direction == 3)
					weaponManager.shoot((int) ((enemy.getX()) - 10), (int) (enemy.getY()), enemy.getX(), enemy.getY(),
							hero, enemy, enemy.getWeaponChoice());
			}

			// PLAYER2 WEAPON SELECT
			if (input.isKeyDown(Input.KEY_1)) {
				enemy.setWeaponChoice(0);
			}

			if (input.isKeyDown(Input.KEY_2)) {
				enemy.setWeaponChoice(1);
			}

			if (input.isKeyDown(Input.KEY_3)) {
				enemy.setWeaponChoice(2);
			}

		}
	}

	// Helper class for input manager
	public class MouseManager {
		public void mouseInput(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			if (Mouse.isButtonDown(0) && (lock == false)) {
				RoundData.num_of_bullet_hero++;
				isShoot = true;
				lock = true;
				// Player1 Shoot
				// Player 1 projectiles are sent towards the direction of the mouse location
				weaponManager.shoot(Mouse.getX(), 700 - Mouse.getY(), hero.getX(), hero.getY(), enemy, hero,
						hero.getWeaponChoice());
			}

			// This statement is used to create a delay between shots by forcing user to
			// "tap" instead of keeping mouse pressed
			if (!Mouse.isButtonDown(0))
				if (lock == true) {
					lock = false;
				}
		}
	}

	// This function checks game over state by checking both characters lives.
	public boolean isGameOver() {
		if (hero.getHealth() <= 0 || enemy.getHealth() <= 0) {
			RoundData.round++;
			if (hero.getHealth() < enemy.getHealth()) {
				winner = 1;
			} else {
				winner = 0;
			}

			return true;
		}
		return false;
	}

	public void setFonts(Graphics g) {
		original = g.getFont();
		fancy = new TrueTypeFont(new Font("Time New Roman", Font.PLAIN, 40), true);
	}
}
