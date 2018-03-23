package rpg.game.tutorial;

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

import rpg.game.tutorial.Common.Direction;

public class GameManager extends BasicGameState {
	// Character Properties
	Character hero;
	CharacterView heroView;
	CharacterController chControl;
	
	
	//Enemy Properties
	Enemy enemy;
	EnemyView enemyView;
	
	// Map Properties
	MapControl map;
	MapView mapView;

	// Sound Properties
	Sound sound;

	// Input Properties
	InputManager in;
	
	boolean isShoot = false;
	Vector2f diffvector;
	int objectLayer;
	BulletManager bulletManager;
	boolean lock = false;

	public GameManager(int worldmap) {
		bulletManager = new BulletManager();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		in = new InputManager();
		map = new MapControl();
		map.loadMap();
				
		mapView = new MapView(map.getMap());

		sound = new Sound("res/fire1.ogg");

		hero = new Character(0);
		heroView = new CharacterView("ch");
		hero.addObserver(heroView);
		hero.setX(0);
		hero.setY(0);

		chControl = new CharacterController(heroView,map);

		hero.notifyObservers();
		
		enemy=new Enemy(1);
		enemyView=new EnemyView("ch1");
		enemy.addObserver(enemyView);
		enemy.setX(0);
		enemy.setY(0);
		
		enemy.notifyObservers();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		mapView.render(hero.getX(), hero.getY());
		g.setColor(Color.white);
		g.drawString("Hero X: " + (hero.getX()+300) + "\n Hero Y: " + (hero.getY()+300), 400, 20);
		heroView.draw(gc,g);
		enemyView.draw();
		if (isShoot) {
			renderBullets(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		in.listenInput(gc, sbg, delta);
	}

	public void renderBullets(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < bulletManager.returnBulletsList().size(); i++) {
			Bullet abullet = bulletManager.returnBulletsList().get(i);

			g.fillRect(abullet.getX(), abullet.getY(), 10, 10);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	public class InputManager {
		public void listenInput(GameContainer gc, StateBasedGame sbg, int delta) {
			Input input = gc.getInput();
			if (input.isKeyDown(Input.KEY_UP)) {
				chControl.move(Direction.UP, delta);
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				chControl.move(Direction.DOWN, delta);
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				chControl.move(Direction.RIGHT, delta);
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				chControl.move(Direction.LEFT, delta);
			}

		}
	}
}
