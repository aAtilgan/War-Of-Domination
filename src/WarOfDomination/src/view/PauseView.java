package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseView extends BasicGameState {
	// Properties
	// VARIABLES
	int posX = 90, posY = 70;
	public String exit = "Exit?";
	// Sonradan eklenecek
	public String options = "Options";
	public String help = "Help";

	public String cont = "Continue";

	public PauseView(int state) {

	}

	public PauseView() {

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	// Draws stuff on screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		// Exit Button
		g.drawString(exit, 305, 300);
		g.drawRect(300, 300, 55, 20);

		// Continue Button
		g.drawString(cont, 305, 150);
		g.drawRect(300, 150, 90, 20);

		// Options Button
		g.drawString(options, 305, 200);
		g.drawRect(300, 200, 80, 20);

		// Help Button
		g.drawString(help, 305, 250);
		g.drawRect(300, 250, 55, 20);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		//System.out.println("X: " + xpos + " Y: " + ypos);
		if (input.isKeyDown(Input.KEY_UP)) {
			posY -= 1;
		}
		if ((xpos > 300 && xpos < 390) && (ypos > 528 && ypos < 550)) {
			exit = "Exit!";
			if (input.isMouseButtonDown(0)) {
				System.exit(0);
			}
		} else
			exit = "Exit?";

		if ((xpos > 280 && xpos < 400) && (ypos > 310 && ypos < 340)) {
			cont = "Continue!";
			if (input.isMouseButtonDown(0)) {
				//sbg.enterState(0);
				//game.enterState(0);
			}
		} else
			cont = "Continue?";

		if ((xpos > 280 && xpos < 380) && (ypos > 260 && ypos < 300)) {
			options = "Options!";
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(2);
				// Common.msc.playOptionsMusic();
			}
		} else
			options = "Options?";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}
}