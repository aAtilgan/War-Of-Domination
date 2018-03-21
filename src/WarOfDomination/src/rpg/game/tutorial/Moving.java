package rpg.game.tutorial;

import rpg.game.tutorial.Common.Direction;

public class Moving extends java.util.Observable{
	//Properties
	float posX;
	float posY;
	float posFeetX;
	float posFeetY;
	int width;
	int height;
	float speed;
	
	String name;
	Direction movingDirection;
	public Moving(String name) {
		this.name=name;
		posX=0;
		posY=0;
		posFeetX=posX+ width/2;
	}
	
	public void setPosition(float x, float y) {
		posX = x;
		posY = y;
		//setChanged();
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	public void changePosition(float change, boolean changeX) {
		if (changeX) posX += change;
		else posY += change;	
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	public float getPosFeetX() {
		return posFeetX;
	}

	public void setPosFeetX(float posFeetX) {
		this.posFeetX = posFeetX;
	}

	public float getPosFeetY() {
		return posFeetY;
	}

	public void setPosFeetY(float posFeetY) {
		this.posFeetY = posFeetY;
	}
	//INNER FUNCTIONS

}
