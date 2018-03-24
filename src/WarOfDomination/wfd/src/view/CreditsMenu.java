package view;
import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class CreditsMenu extends BasicGameState implements InformativeView{
	Input input;
	Image background;
	public CreditsMenu(int credits) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		background=new Image("res/credits_background.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(background, 0,0,null);
		g.setColor(Color.white);
		g.drawString("Press ESCAPE to turn the Main Menu", 75, 500);
		
		g.setBackground(Color.black);
		
		
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 35), true));
		g.setColor(Color.orange);
		g.drawString("CREDITS", 100, 100);
		g.setColor(new Color (0.3f, 0.4f, 0.6f));
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 30), true));
		g.drawString("AYBERK TECIMER", 100, 200);
		g.drawString("AKANT ATILGAN", 100, 250);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		input= gc.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void update() {}

	@Override
	public void init() {}


}
