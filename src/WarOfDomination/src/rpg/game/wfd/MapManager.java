package rpg.game.tutorial;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MapManager extends BasicGameState {
	Animation bucky,up,down,left,right;
	//Image world;
	boolean quit=false;
	int[] duration= {200, 200};
	private TiledMap map;
	float positionX=0;
	float positionY=0;
	float shiftX=positionX+320-20;
	float shiftY=positionY+320-20;
	boolean isShoot = false;
	Vector2f diffvector;
	int objectLayer;
	GameManager gameManager;
	boolean lock = false;
	public MapManager(int worldmap) {
		// TODO Auto-generated constructor stub
		gameManager = new GameManager();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		map=new TiledMap("res/map.tmx");
		//world=new Image("res/world.png");
		Image[] walkUp = { new Image("res/buckysBack.png"),
				new Image("res/buckysBack.png") };
		Image[] walkDown = { new Image("res/buckysFront.png"),
				new Image("res/buckysFront.png") };
		Image[] walkLeft = { new Image("res/buckysLeft.png"),
				new Image("res/buckysLeft.png") };
		Image[] walkRight = { new Image("res/buckysRight.png"),
				new Image("res/buckysRight.png") };
		
		up = new Animation(walkUp, duration, false);
		down = new Animation(walkDown, duration, false);
		left = new Animation(walkLeft, duration, false);
		right = new Animation(walkRight, duration, false);
		bucky=down;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		map.render((int) positionX, (int) positionY);
		//world.draw(positionX,positionY);
		bucky.draw(shiftX,shiftY);
		/*g.drawString("Buckys X: " + positionX + "\nBuckys Y: "
				+ positionY, 400, 20);*/
		if(isShoot)
		{
			renderBullets(g);
		}
		if (quit) {
			g.setColor(Color.black);
			g.drawString("Resume(R)", 250, 100);
			g.drawString("Main Menu(M)", 250, 150);
			g.drawString("Quit Game(Q)", 250, 200);
			if (!quit) {
				g.clear();
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		/*Input input=gc.getInput();
		int objectLayer= map.getLayerIndex("Objects");
		map.getTileId(0, 6, objectLayer);
		if(input.isKeyDown(Input.KEY_UP)) {
			bucky=up;
			
			if(positionY<183) {
				positionY+=delta*.1f;
			}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN)) {
			bucky=down;
			
			if(positionY>-500) {
				positionY-=delta*.1f;
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			bucky=right;
			if(positionX>-657) {
				positionX-=delta*.1f;
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			bucky=left;
			
			if(positionX<340) {
				positionX+=delta*.1f;
			}
		}
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			quit=true;
		}
		
		if(quit) {
			if(input.isKeyDown(Input.KEY_R))
				quit=false;
			if(input.isKeyDown(Input.KEY_M)) {
				sbg.enterState(0);
			}
			if(input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}*/
		// TODO Auto-generated method stub
				Input input=gc.getInput();
				objectLayer = map.getLayerIndex("Objects");
				if(gameManager.returnBulletsList().isEmpty())
					isShoot = false;
				//System.out.println(map.getTileId((int) (320-positionX)/32, (int) (320-positionY)/32, objectLayer));
				if(!isShoot)
				{
				if(input.isKeyDown(Input.KEY_UP)) {
					bucky=up;
					
					if(positionY<320) {
						if(map.getTileId(((int) (320-positionX)/32), (((int) (320- positionY - 16 - delta*.1f)/32))  , objectLayer) != 65)
						positionY+=delta*.1f;
					}
				}
				
				if(input.isKeyDown(Input.KEY_DOWN)) {
					bucky=down;
					
					if(positionY>-280) {
						if(map.getTileId(((int) (320-positionX)/32), ((int) (320-positionY + 16 + delta*.1f)/32)  , objectLayer) != 65)
						positionY-=delta*.1f;
					}
				}
				if(input.isKeyDown(Input.KEY_RIGHT)) {
					bucky=right;
					if(positionX>-280) {
						if(map.getTileId((((int) (320 - positionX + 16 + delta*.1f)/32)), ((int) (320-positionY)/32), objectLayer) != 65)
						positionX-=delta*.1f;
					}
				}
				if(input.isKeyDown(Input.KEY_LEFT)) {
					bucky=left;
					
					if(positionX<320) {
						if(map.getTileId((((int) (320 - positionX - 16- delta*.1f)/32)) , ((int) (320-positionY)/32) , objectLayer) != 65)
						positionX+=delta*.1f;
					}
				}
				if(input.isKeyDown(Input.KEY_ESCAPE)) {
					quit=true;
				}
				}
				
				if(Mouse.isButtonDown(0) && lock == false)
				{
					
					gameManager.shoot(Mouse.getX(),700 - Mouse.getY(),shiftX + 16,shiftY + 16);
					isShoot = true;
					lock = true;
					
				}
				
				if((Mouse.isButtonDown(0) == false) && lock == true)
				{
					isShoot = true;
					lock = false;
				}
				
				
				if(isShoot == true)
				{
					for(int i = 0; i < gameManager.returnBulletsList().size();i++)
					{
						gameManager.updateBullet(gameManager.returnBulletsList().get(i), positionX, positionY,objectLayer, map);
					//System.out.println("entered");
					}
				}
	}
	
	public void renderBullets(Graphics g)
	{
		g.setColor(Color.black);
		for(int i = 0; i < gameManager.returnBulletsList().size();i++)
		{
			Bullet abullet = gameManager.returnBulletsList().get(i);
			
			g.fillRect(abullet.getX(),abullet.getY(), 10, 10);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
