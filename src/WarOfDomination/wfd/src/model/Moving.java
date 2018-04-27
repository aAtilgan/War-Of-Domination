package model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Moving extends java.util.Observable{
	//Properties
	private float positionX;
	private float positionY;
	private float health;
	private float ammo;
	private float shell;
	private int weaponChoice;
	public final float MAX_HEALTH=400;
	boolean alive;
	String talk;
	private float speed;
	public int character_id;
	Sound sound;
	Sound knife_sound;
	public Moving(int character_id) throws SlickException {
		sound = new Sound("res/fire1.ogg");
		knife_sound=new Sound("res/knife_sound.ogg");
		this.character_id=character_id;
		positionX=0;
		positionY=0;
		weaponChoice = 0;
		health=MAX_HEALTH;
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

	public int getCharacter_id() {
		return character_id;
	}

	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean state) 
	{
		this.alive = state;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	public void reduceAmmo()
	{
		this.ammo = this.ammo - 1;
	}
	
	public float getAmmo()
	{
		return this.ammo;
	}
	
	public void reload()
	{
		if(this.weaponChoice == 0)
			this.ammo = 30;
		else if(this.weaponChoice == 2)
			this.shell = 8;

	}
	
	public void heal(int amount)
	{
		this.health = this.health + amount;
	}
	
	public boolean canShoot()
	{
		if(this.ammo <= 0)
			return false;
		return true;
	}
	
	public int getWeaponChoice()
	{
		return this.weaponChoice;
	}
	
	public void setWeaponChoice(int choice)
	{
		this.weaponChoice = choice;
	}
	
	public boolean canShootShell()
	{
		if(shell <= 0)
			return false;
		return true;
	}
	
	public void reduceShell()
	{
		this.shell = this.shell - 1;
	}
	
	public float returnShell()
	{
		return this.shell;
	}
	
	//INNER FUNCTIONS
	public void bulletSound() {
		if(this.weaponChoice==0 && this.ammo >0)
			sound.play(1, 0.05f);
		else if(this.weaponChoice==2 && this.shell >0)
			sound.play(1, 0.05f);
		else if(this.weaponChoice==1)
			knife_sound.play(1,0.05f);
	}

}
