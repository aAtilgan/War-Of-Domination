package view;



import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverView extends BasicGameState{
	private Sound game_over;
	private int time=0;
	private float alpha1 =0f;
	private boolean flag = true;
	private float alpha2 = 0f;
	Input input;
	Image background;
	public GameOverView(int gameover) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		background=new Image("res/game_over_background.png");
		game_over = new Sound("res/game_over_sound.ogg");
		alpha2 = 0f;
		alpha1 =0f;
		flag = true;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{
		g.drawImage(background, 0,0, null);
		g.setColor(new Color(1f, 1f, 1f, alpha1));
		alpha1 = alpha1 + 0.02f;
		/*g.drawString("GAME",
				400, 300);
		g.setColor(new Color(1f, 1f, 1f, alpha2));
		if(alpha1>=1f)
			alpha2 = 0.02f + alpha2;
		g.drawString("OVER",
				450 , 300);*/
		
		g.drawString("Press Enter to Return Main Menu", 600, 500);
		g.drawString("",
				container.getScreenWidth()/2-150f , container.getScreenHeight()/2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException 
	{
		input= gc.getInput();
		if(flag)
		{
			game_over.play();
			flag =false;
		}
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			sbg.getState(0).init(gc, sbg);
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
