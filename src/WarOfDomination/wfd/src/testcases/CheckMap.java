package testcases;

import org.newdawn.slick.SlickException;

import model.Map;

/* Test Case for the Map Class*/
public class CheckMap {
	Map map;
	
	/* All the testing is done in the constructor
	 * the Try-catch blocks will identify whether the tests are successful or not.
	 * The result will be printed on the console.
	 */
	public CheckMap() throws SlickException {
		System.out.println("\nTesting model.Map Class:");
		System.out.println("Testing Map loading operation...");
		String loc = "res/timmy_map.tmx";
		try {
			map = new Map(loc);
			System.out.println("Map loaded without exception");
		}catch(SlickException e) {
			System.out.println("Map loading failed");
		}
		
	}
}
