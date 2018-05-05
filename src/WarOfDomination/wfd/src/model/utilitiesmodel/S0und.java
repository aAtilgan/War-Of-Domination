package model.utilitiesmodel;

/**
 * @author Ayberk
 *
 */
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class S0und implements Observer {

	// constants
	private Music msc;
	private Sound sound;

	// variables
	boolean on = true;
	public float volume;

	// Plays the music if music is on
	public void playTitleMusic() throws SlickException {
		msc = new Music("res/main_theme.ogg");
		if (on)
			msc.loop();
		volume = 1f;
	}

	// Changes the volume accordingly
	public void changeVolume(float change) {
		volume += change;
		msc.setVolume(volume);
	}

	@Override
	// Checks if volume in SettingsData class is changed. Applies changes if so.
	public void update(Observable options, Object arg) {
		// TODO Auto-generated method stub
		if (options instanceof SettingsData) {
			msc.setVolume((((SettingsData) options).volume) * 0.01f);
		}
	}

}
