package model;

import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.*;

public class HealthBar {

	public void render(GameContainer container, Graphics g, float health, float MaxHealth,int characterID) throws SlickException {
		Rectangle bar = new Rectangle(0, 0, 20, 300* health/MaxHealth);
		GradientFill fill = new GradientFill(100, 0, new Color(255, 60, 0), 100 + 300, 0, new Color(255, 180, 0));

		g.setColor(Color.darkGray);
		if(characterID == 0)
			g.fillRect(0, 0, 20, 300);
		else
		{
			g.fillRect(980, 0, 20, 300);
			bar = new Rectangle(980, 0, 20, 300* health/MaxHealth);
		}
		
		g.fill(bar, fill);
	}
}
