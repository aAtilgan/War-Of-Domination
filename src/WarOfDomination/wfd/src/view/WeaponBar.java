package view;
/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.*;

public class WeaponBar {

	public void render(GameContainer container, Graphics g, int choice,int characterID,int ammo,int shell) throws SlickException {
		
		Rectangle bar;
		
		Image img = new Image("res/m4_a1.png");
		Image img2 = new Image("res/Dagger_Douche.png");
		Image img3 = new Image("res/shotgun.png");
		
		if(characterID == 0)
		{
		
			//DRAW RIFLE
			g.setColor(Color.black);
			bar = new Rectangle(4, 500, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("1", 4, 536);
			
			//DRAW KNIFE
			g.setColor(Color.black);
			bar = new Rectangle(4, 552, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("2", 4, 588);
			
			//DRAW SHOTGUN
			g.setColor(Color.black);
			bar = new Rectangle(4, 604, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("3", 4, 640);
			
			
			//DRAW IMAGES AND STRINGS
			g.drawImage(img, 4, 520);
			g.drawImage(img2, 4, 552);
			g.drawImage(img3,4 + 5,604 + 5);
			g.setColor(Color.white);
			g.drawString(ammo + "", 36, 536);
			g.drawString(shell + "", 36, 640);
			
			if(choice == 0)
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 500, 51,51);
				g.draw(bar);
			}
			else if(choice == 1)
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 552, 51,51);
				g.draw(bar);
			}
			else
			{
				g.setColor(Color.red);
				bar = new Rectangle(4, 604, 51,51);
				g.draw(bar);
			}
		}
		
		else
		{
			//DRAW RIFLE
			g.setColor(Color.black);
			bar = new Rectangle(946, 500, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("1", 946, 536);
			
			//DRAW KNIFE
			g.setColor(Color.black);
			bar = new Rectangle(946, 552, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("2", 946, 588);
			
			//DRAW SHOTGUN
			g.setColor(Color.black);
			bar = new Rectangle(946, 604, 50,50);
			g.draw(bar);
			g.setColor(Color.white);
			g.drawString("3", 946, 640);
			
			//DRAWIMAGES AND STRINGS
			g.drawImage(img, 946, 520);
			g.drawImage(img2, 946, 552);
			g.drawImage(img3,946 + 5,604 + 5);
			g.setColor(Color.white);
			g.drawString(ammo + "", 978, 536);
			g.drawString(shell + "", 978, 640);
			
			if(choice == 0)
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 500, 51,51);
				g.draw(bar);
				g.setColor(Color.white);
				g.drawString(ammo + "", 978, 536);
				
			}
			else if(choice == 1)
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 552, 51,51);
				g.draw(bar);
			}
			else
			{
				g.setColor(Color.red);
				bar = new Rectangle(946, 604, 51,51);
				g.draw(bar);
			}
		}
		
	}
}
