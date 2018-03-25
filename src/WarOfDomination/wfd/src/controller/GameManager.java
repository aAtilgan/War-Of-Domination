package controller;

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

import model.Character;
import model.Enemy;
import model.Knife;
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
	boolean lock = false;
	boolean renderRespectToHero = true;
	int direction;

	public GameManager(int worldmap) {
		weaponManager = new WeaponManager();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		
		
		in = new InputManager();
		map = new MapControl();
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
	}

	@Override

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (renderRespectToHero)
			mapView.render(hero.getX(), hero.getY());
		g.setColor(Color.white);
		g.drawString("Hero X: " + (320 - hero.getX()) + "\n Hero Y: " + (320 - hero.getY()), 400, 20);

		g.drawString(hero.setRandomTalk(random_count), hero.getShiftX() + 10, hero.getShiftY() - 10);
		g.drawString(enemy.setRandomTalk(random_count), enemy.getShiftX()- enemy.getX() +10, enemy.getShiftY()-enemy.getY()-10 );
		// g.drawString(enemy.setRandomTalk(random_count), enemy.getX() + 10,
		// enemy.getY() -10 );
		// Enemy set random talk location problem!!!
		g.drawString("" + hero.getHealth(), 400, 50);
		heroView.draw(gc, g);
		enemyView.draw(gc, g);
		if (isShoot) {
			weaponManager.renderWeapons(g);
		}
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
			weaponManager.updateWeapon(weaponManager.returnWeaponsList().get(i), hero.getX(), hero.getY(), map.getMap(),
					type);
		}

		return true;
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
					enemyView.ch.setShiftY(enemyView.ch.getShiftY() + delta * 0.1f);

			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				character_is_moved = chControl.move(Direction.DOWN, delta);
				if (character_is_moved)
					enemyView.ch.setShiftY(enemyView.ch.getShiftY() - delta * 0.1f);

			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {

				character_is_moved = chControl.move(Direction.RIGHT, delta);
				if (character_is_moved)
					enemyView.ch.setShiftX(enemyView.ch.getShiftX() - delta * 0.1f);

			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				character_is_moved = chControl.move(Direction.LEFT, delta);
				if (character_is_moved)
					enemyView.ch.setShiftX(enemyView.ch.getShiftX() + delta * 0.1f);
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
				hero.reloadSound();
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
				enemy.reloadSound();
			}

			// ENEMY SHOOT
			if (input.isKeyPressed(Input.KEY_LCONTROL)) {
				enemy.bulletSound();
				if (direction == 0)
					weaponManager.shoot((int) (enemy.getShiftX() - enemy.getX()),
							(int) ((enemy.getShiftY() - enemy.getY()) + 10), enemy.getShiftX() - enemy.getX(),
							enemy.getShiftY() - enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 1)
					weaponManager.shoot((int) ((enemy.getShiftX() - enemy.getX()) + 10),
							(int) (enemy.getShiftY() - enemy.getY()), enemy.getShiftX() - enemy.getX(),
							enemy.getShiftY() - enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 2)
					weaponManager.shoot((int) (enemy.getShiftX() - enemy.getX()),
							(int) ((enemy.getShiftY() - enemy.getY()) - 10), enemy.getShiftX() - enemy.getX(),
							enemy.getShiftY() - enemy.getY(), hero, enemy, enemy.getWeaponChoice());
				if (direction == 3)
					weaponManager.shoot((int) ((enemy.getShiftX() - enemy.getX()) - 10),
							(int) (enemy.getShiftY() - enemy.getY()), enemy.getShiftX() - enemy.getX(),
							enemy.getShiftY() - enemy.getY(), hero, enemy, enemy.getWeaponChoice());
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
				weaponManager.shoot(Mouse.getX(), 700 - Mouse.getY(), hero.getShiftX(), hero.getShiftY(), enemy, hero,
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
