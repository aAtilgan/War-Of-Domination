package view;
/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;
public class CharacterChoiceView extends BasicGameState{
	private boolean isCharacterChosen=false;
	public CharacterChoiceView(int characterChoice) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Pick your Character" , 100, 100);
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("X: "+ posX+ "Y: "+posY);
		if(!isCharacterChosen) {
			sbg.getState(9).init(gc, sbg);
			sbg.enterState(9);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 8;
	}

}
