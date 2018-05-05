package testcases;

import org.newdawn.slick.SlickException;

import view.mapview.WeaponBar;

/* Test Case for the WeaponBar Class*/
public class CheckWeaponBar {
	WeaponBar wbar;
	
	/* All the testing is done in the constructor
	 * the Try-catch blocks will identify whether the tests are successful or not.
	 * The result will be printed on the console.
	 */
	public CheckWeaponBar() throws SlickException {
		System.out.println("\nTesting view.mapview.WeaponBar Class:");
		System.out.println("Creating WeaponBar...");
		try {
			wbar = new WeaponBar();
			System.out.println("WeaponBar creation successful");
		}catch(SlickException ex) {
			System.out.println("WeaponBar creation failed");
		}
	}
	
	
}
