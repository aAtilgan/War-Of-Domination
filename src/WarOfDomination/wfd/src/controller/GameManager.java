package controller;

import java.util.ArrayList;

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
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import model.AmmoBox;
import model.Character;
import model.Enemy;
import model.HealthBox;
import model.Knife;
import model.Weapon;
import model.Common.Direction;
import view.CharacterView;
import view.EnemyView;
import view.MapView;

public class GameManager extends BasicGameState {
	private static final int game_over=4;
	// Character Properties
	Character hero;
	CharacterView heroView;
	CharacterController chControl;
	
	//TESTING FIELD
	//TESTING FIELD

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

	boolean isShoot = false;
	int objectLayer;
	//character random talk stuff
	int random;
	int random_count = 1;
	
	//Background Image
	Image background;
	WeaponManager weaponManager;
	PickupManager pickupManager;
	boolean lock = false;
	boolean renderRespectToHero = true;
	int direction;

	public GameManager(int worldmap) {
		weaponManager = new WeaponManager();
		pickupManager = new PickupManager();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		//TESTING FIELD
		pickupManager.addAmmoBox(300, 200, 30);
		pickupManager.addHpBox(300, 450, 30);
		//TESTING FIELD
		
		in = new InputManager();
		map = new MapControl();
		map.setX(0);
		map.setY(0);
		map.loadMap();

		mapView = new MapView(map.getMap());

		hero = new Character(0);
		heroView = new CharacterView("ch");
		hero.addObserver(heroView);
		hero.setX(0);
		hero.setY(0);

		chControl = new CharacterController(heroView, map);
		hero.notifyObservers();

		enemy = new Enemy(1);
		enemyView = new EnemyView("ch1");
		enemyControl = new EnemyController(enemyView, map);
		enemy.addObserver(enemyView);
		enemy.setX(0);
		enemy.setY(0);

		enemy.notifyObservers();
		direction = 0;
		
		hero.setX(320);
		hero.setY(320);
		
		enemy.setX(500);
		enemy.setY(500);
	}

	@Override

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (renderRespectToHero)
			mapView.render(map.getX(), map.getY());
		g.setColor(Color.white);
		g.drawString("Hero X On Map: " + (hero.getX()- map.getX()) + "\n Hero Y On Map: " + (hero.getY() - map.getY()), 400, 20);

		g.drawString(hero.setRandomTalk(random_count), hero.getX() + 10, hero.getY() - 10);
		// g.drawString(enemy.setRandomTalk(random_count), enemy.getX() + 10,
		// enemy.getY() -10 );
		// Enemy set random talk location problem!!!
		g.drawString("" + hero.getHealth(), 400, 50);
		heroView.draw(gc, g);
		enemyView.draw(gc, g);
		if (isShoot) {
			weaponManager.renderWeapons(g);
		}
		pickupManager.renderPickups(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		random = (int) (Math.random() * 500);
		if (random == 5) {
			random_count = (int) (Math.random() * 11);
		}
		// System.out.println(random_count);
		if (!this.isGameOver()) {
			in.getInputs(gc, sbg, delta);
			isShoot = updateBullets();
			pickupManager.update(hero, enemy);
		}else {
			sbg.enterState(game_over);
		}

		if(in.key.paused)
		{
			sbg.enterState(3);
			in.key.paused= !in.key.paused;
		}
		if(in.key.set)
		{
			sbg.enterState(2);
			in.key.set= !in.key.set;
		}
		if(in.key.mained)
		{
			sbg.enterState(0);
			in.key.mained= !in.key.mained;
		}
	}

	public boolean updateBullets() {
		if (weaponManager.returnWeaponsList().size() <= 0)
			return false;
		for (int i = 0; i < weaponManager.returnWeaponsList().size(); i++) {
			int type = 0;
			if (weaponManager.returnWeaponsList().get(i) instanceof Knife)
				type = 1;
			weaponManager.updateWeapon(weaponManager.returnWeaponsList().get(i), map.getX(), map.getY(), map.getMap(),
					type);
		}

		return true;
	}
	
	public void counterMoveAll(Direction a,int delta)
	{
		ArrayList<AmmoBox> listOfAmmoBoxes = pickupManager.getAmmoBoxList();
		ArrayList<HealthBox> listOfHealthBoxes = pickupManager.getHpBoxList();
		ArrayList<Weapon> listOfWeapons = weaponManager.returnWeaponsList();
		if(a == Direction.UP)
		{
			//Map Counter move
			map.setY(map.getY() + delta * 0.1f);
			
			//Character Counter move
			enemyView.ch.setY(enemyView.ch.getY() + delta * 0.1f);
			
			//Box Counter Move
			for(int i = 0;i < listOfAmmoBoxes.size();i++)
				listOfAmmoBoxes.get(i).setY(listOfAmmoBoxes.get(i).getY() + delta * 0.1f);
			
			for(int i = 0;i < listOfHealthBoxes.size();i++)
				listOfHealthBoxes.get(i).setY(listOfHealthBoxes.get(i).getY() + delta * 0.1f);
			
			//Bullet Counter Move
			
			for(int i = 0;i < listOfWeapons.size();i++)
			{
				listOfWeapons.get(i).setY(listOfWeapons.get(i).getY() + delta * 0.1f);
			}
		}
		
		if(a == Direction.DOWN)
		{
			//Map movement
			map.setY(map.getY() - delta * 0.1f);
			
			//Character Counter move
			enemyView.ch.setY(enemyView.ch.getY() - delta * 0.1f);
			
			//Box Counter Move
			for(int i = 0;i < listOfAmmoBoxes.size();i++)
				listOfAmmoBoxes.get(i).setY(listOfAmmoBoxes.get(i).getY() - delta * 0.1f);
			
			for(int i = 0;i < listOfHealthBoxes.size();i++)
				listOfHealthBoxes.get(i).setY(listOfHealthBoxes.get(i).getY() - delta * 0.1f);
			
			//Bullet Counter Move
			
			for(int i = 0;i < listOfWeapons.size();i++)
			{
				listOfWeapons.get(i).setY(listOfWeapons.get(i).getY() - delta * 0.1f);
			}
		}
		
		if(a == Direction.RIGHT)
		{
			//Map movement
			map.setX(map.getX() - delta * 0.1f);
			
			//Character Counter move
			enemyView.ch.setX(enemyView.ch.getX() - delta * 0.1f);
			
			//Box Counter Move
			for(int i = 0;i < listOfAmmoBoxes.size();i++)
				listOfAmmoBoxes.get(i).setX(listOfAmmoBoxes.get(i).getX() - delta * 0.1f);
			
			for(int i = 0;i < listOfHealthBoxes.size();i++)
				listOfHealthBoxes.get(i).setX(listOfHealthBoxes.get(i).getX() - delta * 0.1f);
			
			//Bullet Counter Move
			
			for(int i = 0;i < listOfWeapons.size();i++)
			{
				listOfWeapons.get(i).setX(listOfWeapons.get(i).getX() - delta * 0.1f);
			}
		}
		
		if(a == Direction.LEFT)
		{
			//Map movement
			map.setX(map.getX() + delta * 0.1f);
			
			//Character Counter move
			enemyView.ch.setX(enemyView.ch.getX() + delta * 0.1f);
			
			//Box Counter Move
			for(int i = 0;i < listOfAmmoBoxes.size();i++)
				listOfAmmoBoxes.get(i).setX(listOfAmmoBoxes.get(i).getX() + delta * 0.1f);
			
			for(int i = 0;i < listOfHealthBoxes.size();i++)
				listOfHealthBoxes.get(i).setX(listOfHealthBoxes.get(i).getX() + delta * 0.1f);
			
			//Bullet Counter Move
			
			for(int i = 0;i < listOfWeapons.size();i++)
			{
				listOfWeapons.get(i).setX(listOfWeapons.get(i).getX() + delta * 0.1f);
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	public class InputManager {
		KeyManager key = new KeyManager();
		MouseManager mouse = new MouseManager();

		public void getInputs(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			key.keyInput(gc, sbg, delta);
			mouse.mouseInput(gc, sbg, delta);
		}
	}

	public class KeyManager {
		public boolean paused=false;
		public boolean set=false;
		public boolean mained=false;
		public void keyInput(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			Input input = gc.getInput();
			boolean character_is_moved = false;
			boolean enemy_is_moved = false;
			
			// CHARACTER MOVEMENT
			if (input.isKeyDown(Input.KEY_UP)) {

				character_is_moved = chControl.move(Direction.UP, delta);
				if (character_is_moved)
					counterMoveAll(Direction.UP,delta);

			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				character_is_moved = chControl.move(Direction.DOWN, delta);
				if (character_is_moved)
					counterMoveAll(Direction.DOWN,delta);

			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {

				character_is_moved = chControl.move(Direction.RIGHT, delta);
				if (character_is_moved)
					counterMoveAll(Direction.RIGHT,delta);

			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				character_is_moved = chControl.move(Direction.LEFT, delta);
				if (character_is_moved)
					counterMoveAll(Direction.LEFT,delta);
			}
			//Pause Option
			
			if (input.isKeyDown(Input.KEY_P))
			{
				paused = true;
			}
			
			if (input.isKeyPressed(Input.KEY_O)) {
				set=true;
			}
			
			if (input.isKeyDown(Input.KEY_ESCAPE)) {

				mained=true;

			}
			// RELOAD
			if (input.isKeyPressed(Input.KEY_RCONTROL)) {
				heroView.ch.reload();
			}
			
			// CHOOSE WEAPON TYPE
			if (input.isKeyPressed(Input.KEY_NUMPAD1)) {
				hero.setWeaponChoice(0);
			}

			if (input.isKeyPressed(Input.KEY_NUMPAD2)) {
				hero.setWeaponChoice(1);
			}

			if (input.isKeyPressed(Input.KEY_NUMPAD3)) {
				hero.setWeaponChoice(2);
			}

			// ENEMY CONTROL
			if (input.isKeyDown(Input.KEY_W)) {

				enemy_is_moved = enemyControl.move(Direction.UP, delta);
				direction = 2;
			}
			if (input.isKeyDown(Input.KEY_S)) {
				enemy_is_moved = enemyControl.move(Direction.DOWN, delta);
				direction = 0;

			}
			if (input.isKeyDown(Input.KEY_D)) {
				enemy_is_moved = enemyControl.move(Direction.RIGHT, delta);
				direction = 1;
			}

			if (input.isKeyDown(Input.KEY_A)) {
				enemy_is_moved = enemyControl.move(Direction.LEFT, delta);
				direction = 3;

			}

			if (input.isKeyPressed(Input.KEY_R)) {
				enemyView.ch.reload();
			}

			// ENEMY SHOOT
			if (input.isKeyPressed(Input.KEY_LCONTROL)) {
				enemy.bulletSound();
				if (direction == 0)
					weaponManager.shoot((int) (enemy.getX()),
							(int) ((enemy.getY()) + 10),enemy.getX(),
							enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 1)
					weaponManager.shoot((int) ((enemy.getX()) + 10),
							(int) (enemy.getY()), enemy.getX(),
							enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 2)
					weaponManager.shoot((int) (enemy.getX()),
							(int) ((enemy.getY()) - 10), enemy.getX(),
							enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 3)
					weaponManager.shoot((int) ((enemy.getX()) - 10),
							(int) (enemy.getY()), enemy.getX(),
							enemy.getY(), hero, enemy, enemy.getWeaponChoice());
			}

			// ENEMY WEAPON SELECT
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

	public class MouseManager {
		public void mouseInput(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			if (Mouse.isButtonDown(0) && (lock == false)) {
				hero.bulletSound();
				isShoot = true;
				lock = true;
				map.getMap();
				// Player Shoot
				weaponManager.shoot(Mouse.getX(), 700 - Mouse.getY(), hero.getX(), hero.getY(), enemy, hero,
						hero.getWeaponChoice());
				// Enemy Shoot Auto
			}

			if (!Mouse.isButtonDown(0))
				if (lock == true) {
					lock = false;
				}
		}
	}

	public boolean isGameOver() {
		if (hero.getHealth() <= 0 || enemy.getHealth() <= 0) {
			return true;
		}
		return false;
	}
}
