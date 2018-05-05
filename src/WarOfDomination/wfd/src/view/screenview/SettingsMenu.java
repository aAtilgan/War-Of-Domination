package view.screenview;

/**
 * @author Ayberk
 *
 */
//import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import controller.SettingsController;
import model.utilitiesmodel.SettingsData;

public class SettingsMenu extends BasicGameState {
	// Background picture
	Image settingBackground;
	// location of cursor
	int cursor = 0;
	String fullscreen;

	// settingsdata object
	SettingsData data;
	// settings controller object
	SettingsController control;

	// constructor
	public SettingsMenu(int settings, SettingsData data) {
		this.data = data;
		// initialize control object
		control = new SettingsController(data);
	}

	@Override
	// load background picture
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		settingBackground = new Image("res/settingsBackground.jpg");
	}

	@Override
	// Renders texts, buttons and sliders on the screen for user to interact
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Picture
		g.drawImage(settingBackground, 0, 0, null);
		g.setColor(Color.white);
		g.drawString(">", 100, (100 + cursor * 50));
		// Volume
		g.drawString("Volume", 150, 100);
		g.drawString("" + data.getVolume(), 420, 100); // 33 ü deðiþtir
		// Fullscreen
		g.drawString("Fullscreen", 150, 150);
		g.drawString(fullscreen, 420, 150); // burada deðiþtir"

		// Brightness
		g.drawString("Brightness", 150, 200);
		g.drawString("" + data.getBrightness(), 420, 200); // 180 i deðiþtir

		// Text
		g.drawString("Use Directions to adjust", 100, 250);
		g.drawString("Press Escape to Main Menu", 150, 300);
		g.drawString("Press Enter to Continue", 150, 350);

	}

	@Override
	// Checks the changes with the help of listenInput method.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (data.isFullscreen())
			fullscreen = "ON";
		else
			fullscreen = "OFF";
		listenInput(gc, sbg, delta);
	}

	// This helper method checks pressed keys and cursor locations to do necessary
	// changes if user performs a legit action.
	public void listenInput(GameContainer gc, StateBasedGame sbg, int delta) {
		// temp input
		Input input = gc.getInput();
		// Volume slider
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			cursor = Math.min(cursor + 1, 2);
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			cursor = Math.max(cursor - 1, 0);
		}
		// Volume
		if (cursor == 0 && input.isKeyDown(Input.KEY_RIGHT)) {
			control.changeVolume((delta * 0.5f));
		} else if (cursor == 0 && input.isKeyDown(Input.KEY_LEFT)) {
			control.changeVolume(-delta * 0.5f);
		}
		// Fullscreen
		if (cursor == 1 && input.isKeyDown(Input.KEY_RIGHT)) {
			control.goFullScreen(true);
		} else if (cursor == 1 && input.isKeyDown(Input.KEY_LEFT)) {
			control.goFullScreen(false);
		}
		// Brightness
		if (cursor == 2 && input.isKeyDown(Input.KEY_RIGHT)) {
			if (data.getBrightness() < 100) {
				control.changeBrightness(+5);
			}

		} else if (cursor == 2 && input.isKeyDown(Input.KEY_LEFT)) {
			if (data.getBrightness() > 0) {
				control.changeBrightness(-5);
			}
			// control.changeBrightness(-5);
		} else if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(0); // goes to main menu
		} else if (input.isKeyDown(Input.KEY_ENTER)) {
			sbg.enterState(1); // goes to main menu
		}
	}

	@Override
	public int getID() {
		return 2;

	}

}
