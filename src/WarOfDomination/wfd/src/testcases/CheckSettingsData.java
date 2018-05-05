package testcases;

import model.utilitiesmodel.SettingsData;

/* Test Case for the SettingsData Class*/
public class CheckSettingsData {
	SettingsData sData;
	
	/* The constructor tests the class by calling the check methods defined for checking various methods in the class
	 * The Test returns successful if all the function tests are successful.
	 */
	public CheckSettingsData() {
		System.out.println("\nTesting model.utitlitiesmodel.SettingsData Class:");
		sData = new SettingsData();
		
		boolean method1 = checkSetVolumeMethod();
		boolean method2 = checkSetBrightnessMethod();
		
		if(method1 && method2)
			System.out.println("\nSettingsData Class test complete");
		else
			System.out.println("\nSettingsData Class test failed");
	}
	
	/* Function to check the SetVolume Method */
	public boolean checkSetVolumeMethod() {
		System.out.println("\nTesting setVolume Method: ");
		sData.setVolume(15);
		System.out.println("Expected Value = 15"  + "\nActual Value = " + sData.getVolume());
		if(sData.getVolume() == 15) {
			System.out.println("setVolume is successful");
			return true;
		}
		else {
			System.out.println("setVolume failed");
			return false;
		}
	}
	
	/* Function to check the setBrightness Method */
	public boolean checkSetBrightnessMethod() {
		System.out.println("\nTesting setBrightness Method: ");
		sData.setBrightness(50);
		System.out.println("Expected Value = 50"  + "\nActual Value = " + sData.getBrightness());
		if(sData.getBrightness() == 50) {
			System.out.println("setBrightness is successful");
			return true;
		}
		else {
			System.out.println("setBrightness failed");
			return false;
		}
	}
	
	
}
