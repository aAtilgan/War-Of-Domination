package view;
/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.SlickException;

import view.mapview.HealthBar;
import view.mapview.WeaponBar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class EnemyView extends MovingView {
	HealthBar healthbar;
	WeaponBar weaponBar;
	int[] duration = { 200, 200, 200 };
	static final int halfSizeX = 16;
	static final int halfSizeY = 18;
	public EnemyView(String str) throws SlickException {
		super();
		weaponBar = new WeaponBar();
		healthbar = new HealthBar();
		walkUp = new Image[3];
		walkDown = new Image[3];
		walkRight = new Image[3];
		walkLeft = new Image[3];

		for (int i = 1; i < 4; i++) {
			walkUp[i - 1] = new Image("res/" + str + "_up_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkDown[i - 1] = new Image("res/" + str + "_down_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkLeft[i - 1] = new Image("res/" + str + "_left_" + i + ".png");
		}
		for (int i = 1; i < 4; i++) {
			walkRight[i - 1] = new Image("res/" + str + "_right_" + i + ".png");
		}
		up = new Animation(walkUp, duration, false);
		down = new Animation(walkDown, duration, false);
		left = new Animation(walkLeft, duration, false);
		right = new Animation(walkRight, duration, false);
		animList = new Animation[4];
		animList[0] = up;
		animList[1] = right;
		animList[2] = left;
		animList[3] = down;
		bucky = down;
		for (int i = 0; i < animList.length; i++) {
			animList[i].setAutoUpdate(true);
		}
	}


	public void draw(GameContainer gc, Graphics g)  throws SlickException {
		this.getMainAnimation().draw(ch.getX() - halfSizeX, ch.getY() - halfSizeY);
		healthbar.render(gc, g, ch.getHealth(), ch.MAX_HEALTH,1);
		weaponBar.render(gc, g, ch.getWeaponChoice(), 1,(int) ch.getAmmo(),(int) ch.returnShell());
		
	}
	public void setAnimation(String str) {
		if (str == "up")
			bucky = up;
		if (str == "down")
			bucky = down;
		if (str == "right")
			bucky = right;
		if (str == "left")
			bucky = left;
	}

}
