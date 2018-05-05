package testcases;


import org.newdawn.slick.SlickException;

import model.personmodel.Moving;
import model.weaponmodel.Weapon;

/* Test Case for the Weapon Class*/
public class CheckWeapon {
	Weapon bullet, knife;
	
	/* All the testing is done in the constructor
	 * the Try-catch blocks will identify whether the tests are successful or not.
	 * The result will be printed on the console.
	 */
	public CheckWeapon() throws SlickException {
		System.out.println("\nTesting model.weaponmodel.Weapon Class:");

		try {
			System.out.println("Testing Bullet Creation");
			bullet = new Weapon(100, 100, 50, 50, 0, new Moving(1), 1);	//OpenGl context problem
			
			System.out.println("Testing knife Creation");
			knife = new Weapon(20, 20, 40, 40, 0, new Moving(0), 0);
			
			System.out.println("All weapons created successfully");
		}catch(RuntimeException e) {
			System.out.println("RuntimeException in Weapon creation\nTest incomplete");
		}
	}
	
}
