package testcases;

import view.mapview.HealthBar;

/* Test Case for the HealthBar Class*/
public class CheckHealthBar {
	
	HealthBar hbar;
	
	/* All the testing is done in the constructor
	 * the Try-catch blocks will identify whether the tests are successful or not.
	 * The result will be printed on the console.
	 */
	public CheckHealthBar() {
		System.out.println("\nTesting view.mapview.HealthBar Class:");
		System.out.println("Creating HealthBar...");
		try {
			hbar = new HealthBar();
			System.out.println("HealthBar creation successful");
		}catch(RuntimeException ex) {
			System.out.println("HealthBar creation failed");
		}
	}
}
