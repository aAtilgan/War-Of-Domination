package rpg.game.tutorial;


public class Moving extends java.util.Observable{
	//Properties
	private float positionX;
	private float positionY;
	private float health;
	public final float MAX_HEALTH=400;
	boolean alive;
	String talk;
	private float shiftX;
	private float shiftY;
	private float speed;
	int character_id;
	
	public Moving(int character_id) {
		this.character_id=character_id;
		positionX=0;
		positionY=0;
		health=MAX_HEALTH;
	}
	
	public float getX() {
		return positionX;
	}

	public float getY() {
		return positionY;
	}
	
	public void setShiftX(float x) {
		shiftX=x;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	public void setShiftY(float y) {
		shiftY=y;
		setChanged();
		notifyObservers();
		clearChanged();
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
	
	public float getShiftX() {
		return shiftX;
	}

	public float getShiftY() {
		return shiftY;
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

	public void setAlive(boolean state) {
		this.alive = state;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	//INNER FUNCTIONS

}
