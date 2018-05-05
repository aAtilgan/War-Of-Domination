package testcases;

import model.utilitiesmodel.S0und;

/* Test Case for the S0und Class*/
public class CheckSound {
	S0und sound;
	
	/* The constructor tests the class by calling the check methods defined for checking various methods in the class
	 * The Test returns successful if all the function tests are successful.
	 */
	public CheckSound() {
		System.out.println("\nTesting model.utilitiesmodel.S0und Class:");
		sound = new S0und();
		
		boolean method1 = true;
		try {
			checkChangeVolumeMethod();
		}catch(NullPointerException e) {
			method1 = false;
			System.out.println("NullPointerException in volumeChange\nTest incomplete!\n");
		}
		
		if(method1) 
			System.out.println("\nS0und Class test complete");

	}
	
	/* Function to test changeVolume method */
	public boolean checkChangeVolumeMethod() {
		System.out.println("\nTesting changeVolume Method: ");
		sound.volume = 15;
		sound.changeVolume(50);
		System.out.println("Expected Value = 65"  + "\nActual Value = " + sound.volume);
		if(sound.volume == 65)
			return true;
		else 
			return false;		
	}
}
