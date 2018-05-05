package testcases;

import org.newdawn.slick.SlickException;

import controller.GameManager;
import model.personmodel.Moving;
import model.RoundData;

public class CheckMoving {
	
	Moving mov;
	RoundData rd;
	GameManager gmngr;
	
	/* Constructor performs all the checks of different methods and return the result via printing on the console */
	public CheckMoving() throws SlickException {
		mov = new Moving(0);
		gmngr = new GameManager(1);
		
		System.out.println("\nTesting model.Moving Class:");
		
		//if(checkReduceAmmoMethod() && checkReloadMethod() && checkHealMethod()) 
		if(checkReduceAmmoMethod() && checkHealMethod()) 
			System.out.println("\nMoving Class test successful");
		else
			System.out.println("\nMoving Class test failed");
	}
	
	public boolean checkHealMethod() {
		System.out.println("\nTesting Heal Method: ");
		mov.setHealth(100);
		mov.heal(mov.MAX_HEALTH + 50);
		System.out.println("Expected Value = " + mov.MAX_HEALTH + "\nActual Value = " + mov.getHealth());
		if(mov.getHealth() > mov.MAX_HEALTH)
			return false;
		else 
			return true;
	}
	
	
	/* first check reduceAmmo method to check the reload method */
	public boolean checkReduceAmmoMethod() {
		System.out.println("\nTesting reduceAmmo Method: ");
		float ammo = mov.getAmmo();
		mov.reduceAmmo();
		System.out.println("Expected Value = " + (ammo-1) + "\nActual Value = " + mov.getAmmo());
		if(mov.getAmmo() < ammo)
			return true;
		else 
			return false;
	}
	
	
	public boolean checkReloadMethod() {	// ~fix this
		/* initial weapon is rifle */
		System.out.println(RoundData.weapon_choice_hero.get(0));
		
		
		mov.setWeaponChoice(3);
		mov.setWeaponChoice(2);
		mov.setWeaponChoice(1);
		mov.reload();
		if(mov.getAmmo() == 30)
			return true;
		else 
			return false;
	}
	
}
