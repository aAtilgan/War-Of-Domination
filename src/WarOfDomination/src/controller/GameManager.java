package controller;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import model.Bullet;
import model.Character;
import model.Enemy;
import model.Common.Direction;
import view.CharacterView;
import view.EnemyView;
import view.MapView;

public class GameManager extends BasicGameState {
	// Character Properties
	Character hero;
	CharacterView heroView;
	CharacterController chControl;
	
	
	//Enemy Properties
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
		
		enemy=new Enemy(0);
		enemyView=new EnemyView("ch1");
		enemyControl = new EnemyController (enemyView,map);
		enemy.addObserver(enemyView);
		enemy.setX(0);
		enemy.setY(0);
		
		enemy.notifyObservers();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		mapView.render(hero.getX(), hero.getY());
		g.setColor(Color.white);
		g.drawString("Hero X: " + (hero.getX()) + "\n Hero Y: " + (hero.getY()), 400, 20);
		heroView.draw(gc,g);
		enemyView.draw();
		if (isShoot) {
			renderBullets(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		in.getInputs(gc, sbg, delta);
		isShoot = updateBullets();
	}

	public void renderBullets(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < bulletManager.returnBulletsList().size(); i++) {
			Bullet abullet = bulletManager.returnBulletsList().get(i);
			
			g.fillRect(abullet.getX(), abullet.getY(), 10, 10);
		}
	}
	
	public boolean updateBullets()
	{
		if(bulletManager.returnBulletsList().size() <= 0)
			return false;
		for(int i = 0;i < bulletManager.returnBulletsList().size();i++)
		{
			bulletManager.updateBullet(bulletManager.returnBulletsList().get(i), hero.getX(), hero.getY(), map.getMap());
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
        

        public void getInputs(GameContainer gc, StateBasedGame sbg, int delta) {
            key.keyInput(gc, sbg, delta);
            mouse.mouseInput(gc, sbg, delta);
        }
    }

    public class KeyManager {
        public void keyInput(GameContainer gc, StateBasedGame sbg, int delta) {
            Input input = gc.getInput();
            boolean moveEnemy;
    		//System.out.println(enemyView.ch.getY());
            if (input.isKeyDown(Input.KEY_UP)) {
            	moveEnemy = chControl.move(Direction.UP, delta);
            	if(moveEnemy)
            	{
            		enemyControl.move(Direction.DOWN, delta);
            	}
            }
            if (input.isKeyDown(Input.KEY_DOWN)) {
            	moveEnemy = chControl.move(Direction.DOWN, delta);
            }
            if (input.isKeyDown(Input.KEY_RIGHT)) {
            	moveEnemy = chControl.move(Direction.RIGHT, delta);
            }
            if (input.isKeyDown(Input.KEY_LEFT)) {
            	moveEnemy = chControl.move(Direction.LEFT, delta);
            }
        }
    }

    public class MouseManager {
        public void mouseInput(GameContainer gc, StateBasedGame sbg, int delta) {
        	if(Mouse.isButtonDown(0) && (lock == false))
        	{
        		isShoot = true;
        		lock = true;
        		map.getMap();
        		bulletManager.shoot(Mouse.getX(), 700 - Mouse.getY(), hero.getShiftX(), hero.getShiftY());
        	}
        	if(!Mouse.isButtonDown(0))
        	if(lock == true)
        	{
        		lock = false;
        	}
        }
	}
}
