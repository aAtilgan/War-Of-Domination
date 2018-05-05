package controller;

/**
 * @author Ayberk
 *
 */
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MenuManager extends BasicGameState {
	// Background image
	Image background;

	// constructor
	public MenuManager(int startmenu) {
	}

	@Override
	// loads background image.
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/background.png");
	}

	@Override
	// Renders buttons and background picture
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawImage(background, 0, 0, null);
		g.drawString("WAR OF DOMINATION", 400, 70);
		g.drawString("Play", 450, 200);
		g.drawString("Settings", 450, 300);
		g.drawString("How To Play", 450, 400);
		g.drawString("Credits", 450, 500);
		g.drawString("Exit", 450, 600);
	}

	@Override
	// checks user inputs by calling helper method
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		checkStartMenu(gc, sbg);
	}

	@Override
	public int getID() {
		// the return value of this method should be the same with your main class
		return 0;
	}

	// This helpet method checks the location of user clicks and
	// does necessary a state transitions if user clicks on a valid button.
	public void checkStartMenu(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// Get mouse locations
		int posX = Mouse.getX();
		int posY = Mouse.getY();

		if ((posX > 400 && posX < 485) && (posY > 484 && posY < 495)) {
			if (Mouse.isButtonDown(0)) {
				// Enter Play state
				sbg.getState(8).init(gc, sbg);
				sbg.enterState(8);
			}
		}

		if ((posX > 450 && posX < 520) && (posY > 385 && posY < 395)) {
			if (Mouse.isButtonDown(0)) {
				// Enter Settings State
				sbg.enterState(2);
			}
		}

		if ((posX > 450 && posX < 550) && (posY > 284 && posY < 298)) {
			if (Mouse.isButtonDown(0)) {
				// Enter How To Play State
				sbg.enterState(6);
			}
		}
		if ((posX > 450 && posX < 512) && (posY > 180 && posY < 198)) {
			if (Mouse.isButtonDown(0)) {
				// Enter CREDITS State
				sbg.enterState(5);
			}
		}
		if ((posX > 450 && posX < 484) && (posY > 85 && posY < 100)) {
			if (Mouse.isButtonDown(0)) {
				// Enter EXIT State
				AL.destroy();
				System.exit(0);
			}
		}
	}

}
