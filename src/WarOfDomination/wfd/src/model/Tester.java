package model;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Tester {
	public static void main(String[] args) {
		AppGameContainer agc;
		try {
			agc = new AppGameContainer(new Game("War of Domination"));
			agc.setDisplayMode(1000, 700, false);
			agc.start();
			
		} catch (SlickException e) {

		}
	}
}
