package view;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameManager;

public class GameOverView extends BasicGameState{
	private Sound game_over;
	private long time = System.currentTimeMillis();
	private float alpha1 =0f;
	private boolean flag = true;
	private float alpha2 = 0f;
	
	public GameOverView(int gameover) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		game_over = new Sound("res/game_over_sound.ogg");
		alpha2 = 0f;
		alpha1 =0f;
		flag = true;
		time = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 30), true));
		g.setColor(new Color(1f, 1f, 1f, alpha1));
		alpha1 = alpha1 + 0.02f;
		g.drawString("GAME",
				container.getScreenWidth()/2 - 350f, container.getScreenHeight()/2);
		g.setColor(new Color(1f, 1f, 1f, alpha2));
		if(alpha1>=1f)
			alpha2 = 0.02f + alpha2;
		g.drawString("OVER",
				container.getScreenWidth()/2-250f , container.getScreenHeight()/2);
		
		g.drawString("",
				container.getScreenWidth()/2-150f , container.getScreenHeight()/2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException 
	{
		if(flag)
		{
			game_over.play();
			flag =false;
		}
		
		if(alpha2 > 1f)
		{
			sbg.getState(0).init(container, sbg);
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
