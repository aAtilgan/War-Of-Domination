package controller;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuManager extends BasicGameState {
	Image background;
	public MenuManager(int startmenu) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		background=new Image("res/background.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(background,0,0,null);
		g.drawString("Play", 500, 400);
		g.drawString("Settings", 500, 500);
		g.drawString("Exit", 500, 600);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		checkStartMenu(sbg);
	}

	@Override
	public int getID() {
		// the return value of this method should be the same with your main class
		return 0;
	}

	public void checkStartMenu(StateBasedGame sbg) {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		 //System.out.println("X: "+ posX+ "Y: "+posY);
		if ((posX > 500 && posX < 541) && (posY > 278 && posY < 300)) {
			if (Mouse.isButtonDown(0)) {
				// zero because it is left click
				sbg.enterState(1);
			}
		}

		if ((posX > 500 && posX < 541) && (posY > 180 && posY < 195)) {
			if (Mouse.isButtonDown(0)) {
				// zero because it is left click
				sbg.enterState(2);
			}
		}
		
		if ((posX > 500 && posX < 541) && (posY > 82 && posY < 97)) {
			if (Mouse.isButtonDown(0)) {
				// zero because it is left click
				System.exit(0);
			}
		}
	}

}
