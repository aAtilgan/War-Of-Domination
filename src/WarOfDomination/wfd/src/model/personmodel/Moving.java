package model.personmodel;

/**
 * @author Akant
 * @author Ayberk
 */
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import model.RoundData;

public class Moving extends java.util.Observable {
	// variables
	private float positionX;
	private float positionY;
	private float health;
	private float ammo;
	private float shell;
	private int weaponChoice;
	boolean alive;
	String talk;

	// constants
	public final float MAX_HEALTH = 400;
	public int character_id;
	private float speed;

	// Weapon sounds
	Sound sound;
	Sound knife_sound;
	Sound reload;
	Sound empty;

	// constructors
	public Moving(int character_id) throws SlickException {
		sound = new Sound("res/fire1.ogg");
		knife_sound = new Sound("res/knife_sound.ogg");
		reload = new Sound("res/reloading.ogg");
		empty = new Sound("res/empty_sound.ogg");
		this.character_id = character_id;
		positionX = 0;
		positionY = 0;
		weaponChoice = 0;
		health = MAX_HEALTH;
		ammo = 30;
		shell = 8;
	}

	public float getX() {
		return positionX;
	}

	public float getY() {
		return positionY;
	}

	public void setX(float x) {
		positionX = x;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public void setY(float y) {
		positionY = y;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	// heals character according to max health
	public void heal(float value) {
		if (this.health + value > this.MAX_HEALTH)
			this.health = this.MAX_HEALTH;
		else
			this.health = this.health + value;
	}

	public int getCharacter_id() {
		return character_id;
	}

	// sets this characters id and notifys observers
	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public boolean isAlive() {
		return alive;
	}

	// sets the character state to alive and notifys observers
	public void setAlive(boolean state) {
		this.alive = state;
		setChanged();
		notifyObservers();
		clearChanged();
	}

	// reduces character ammo
	public void reduceAmmo() {
		this.ammo = this.ammo - 1;
	}

	public float getAmmo() {
		return this.ammo;
	}

	// This method reloads characters selected weapon according to their choice of
	// weapons.
	// The reason behind 3 checks is to make sure users can choose different hotkeys
	// for their weapons.
	// Ex: Shotgun can be in key 1 same as Knife and other weapons.
	public void reload() {
		if (this.character_id == 0) {
			if (this.weaponChoice == 0) {
				if (RoundData.weapon_choice_hero.get(2) == 1 || RoundData.weapon_choice_hero.get(2) == 4
						|| RoundData.weapon_choice_hero.get(2) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_hero.get(2) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			} else if (this.weaponChoice == 1) {
				if (RoundData.weapon_choice_hero.get(1) == 1 || RoundData.weapon_choice_hero.get(1) == 4
						|| RoundData.weapon_choice_hero.get(1) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_hero.get(1) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			} else if (this.weaponChoice == 2) {
				if (RoundData.weapon_choice_hero.get(0) == 1 || RoundData.weapon_choice_hero.get(0) == 4
						|| RoundData.weapon_choice_hero.get(0) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_hero.get(0) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			}
		}

		if (this.character_id == 1) {
			if (this.weaponChoice == 0) {
				if (RoundData.weapon_choice_enemy.get(2) == 1 || RoundData.weapon_choice_enemy.get(2) == 4
						|| RoundData.weapon_choice_enemy.get(2) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_enemy.get(2) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			} else if (this.weaponChoice == 1) {
				if (RoundData.weapon_choice_enemy.get(1) == 1 || RoundData.weapon_choice_enemy.get(1) == 4
						|| RoundData.weapon_choice_enemy.get(1) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_enemy.get(1) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			} else if (this.weaponChoice == 2) {
				if (RoundData.weapon_choice_enemy.get(0) == 1 || RoundData.weapon_choice_enemy.get(0) == 4
						|| RoundData.weapon_choice_enemy.get(0) == 5) {
					this.ammo = 30;
					reload.play(1, 0.06f);
				} else if (RoundData.weapon_choice_enemy.get(0) == 3) {
					this.shell = 8;
					reload.play(1, 0.06f);
				}
			}
		}

	}

	// checks if charactr is eligible to shoot
	public boolean canShoot() {
		if (this.ammo <= 0)
			return false;
		return true;
	}

	public int getWeaponChoice() {
		return this.weaponChoice;
	}

	public void setWeaponChoice(int choice) {
		this.weaponChoice = choice;
	}

	// checks if character is eligible to shoot shotgun
	public boolean canShootShell() {
		if (shell <= 0)
			return false;
		return true;
	}

	public void reduceShell() {
		this.shell = this.shell - 1;
	}

	public float returnShell() {
		return this.shell;
	}

}
