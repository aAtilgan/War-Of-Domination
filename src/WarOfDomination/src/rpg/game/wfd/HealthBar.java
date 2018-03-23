package rpg.game.tutorial;

import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.*;

public class HealthBar {

	public void render(GameContainer container, Graphics g, float health, float MaxHealth) throws SlickException {
		Rectangle bar = new Rectangle(0, 0, 20, 300* health/MaxHealth);
		GradientFill fill = new GradientFill(100, 0, new Color(255, 60, 0), 100 + 300, 0, new Color(255, 180, 0));

		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 20, 300);
		g.fill(bar, fill);
	}
}
