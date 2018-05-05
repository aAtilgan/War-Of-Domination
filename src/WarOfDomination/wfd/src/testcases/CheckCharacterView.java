package testcases;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.CharacterView;

/* Test Case for the CharacterView Class*/
public class CheckCharacterView {
	
	CharacterView chview;
	Image[] right;
	
	/* All the testing is done in the constructor
	 * the Try-catch blocks will identify whether the tests are successful or not.
	 * The result will be printed on the console.
	 */
	public CheckCharacterView() {
		System.out.println("\nTesting view.CharacterView Class:");
		System.out.println("Creating CharacterView with ninja character...");
		try {
			chview = new CharacterView("ninja");
			System.out.println("CharacterView creation successful.");			
		}catch(SlickException e) {
			System.out.println("Creating CharacterView failed.");
		}
		
		System.out.println("Testing setting character animation...");
		try {
			int[] duration = { 200, 200, 200 };
			right = new Image[3];
			right[0] = new Image("res/ninja_right_1.png");
			right[1] = new Image("res/ninja_right_2.png");
			right[2] = new Image("res/ninja_right_3.png");
			
			Animation anim = new Animation(right, duration, false);
			chview.setDefault(anim);
			
			if(chview.getMainAnimation() == anim) {
				System.out.println("Character Animation succesfully set\nExpected animation: Right\nActual Animaiton = Right");
			}
			else {
				throw new SlickException("ex");
			}
			
		} catch (SlickException e) {
			System.out.println("Setting Character animation failed.");
		}
	}
}
