package testcases;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.Game;

/* The MainTest class is created using the BasicGame class so that an AppGameContainer can be created to initiallize the OpenGL context.
 * The init method will call all the constructors of all the testcases classes.
 * The output of these test cases can be seen on the console.
 * The MainTest class will act as a Test Suite for all the test cases.
 */

public class MainTest extends BasicGame{
	
	public MainTest(String gamename) {
		super(gamename);
	}

	/* init calls all the constructors of the test case classes */
	@Override
	public void init(GameContainer gc) throws SlickException {
		CheckMoving testmoving = new CheckMoving();
		CheckSound testsound = new CheckSound();
		CheckSettingsData testsettingsdata = new CheckSettingsData();
		CheckWeapon testweapon = new CheckWeapon();
		CheckMap testmap = new CheckMap();
		CheckHealthBar testhealthbar = new CheckHealthBar();
		CheckWeaponBar testweaponbar = new CheckWeaponBar();
		CheckCharacterView testcharacterview = new CheckCharacterView();
	}

	/* Update will not be used as there is nothing being rendered */
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	/* Rendering is not used in the test cases */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {	
	}
	
	/* The main method initializes the AppGameContainer */
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainTest("Tester"));
			appgc.setDisplayMode(200, 40, false);
			appgc.start();
		} catch (SlickException ex) {
			
		}
		
	}


}
