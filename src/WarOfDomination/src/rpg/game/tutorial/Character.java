package rpg.game.tutorial;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Character extends java.util.Observable{
	float health;
	public final float MAX_HEALTH = 400;
	boolean alive;
	String talk;
	
	private float positionX = 0;
	private float positionY = 0;
	private float shiftX = 300;
	private float shiftY = 300;
	private float speed=.1f;

	public Character() throws SlickException {
		health=MAX_HEALTH;
		this.alive = true;
		talk="";
	}

	public void setRandomTalk() {
		int rand = (int)(Math.random()*10 + 1);
		switch (rand) {
        case 1:  talk = "Only Van Helsing can hunt me.";
        break;
        case 2:  talk = "Still a better love story than twilight.";
        break;
        case 3:  talk = "Trolololololo.";
        break;
        case 4:  talk = "Wanna be my friend?";
        break;
        case 5:  talk = "I'm bored.";
        break;
        case 6:  talk = "I have a dream.";
        break;
        case 7:  talk = "Haters gonna hate.";
        break;
        case 8:  talk = "The internet is dark and full of spoilers.";
        break;
        case 9: talk = 	"Eto'o is done.";
        break;
        case 10: talk = "Give me your command.";
        break;
		}
		
	}
	/* alive setter and getter */
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean state) {
		this.alive = state;
	}

	
	/* location setters and getters */
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
	}


}