package model;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class S0und implements Observer {
	private Music msc;
	private Sound sound;
	boolean on=true;
	float volume;
	public void playTitleMusic() throws SlickException {
		msc=new Music("res/main_theme.ogg");
		if (on)
			msc.loop();
		volume = 100f;
	}
	public void changeVolume(float change) {
		volume += change;
		msc.setVolume(volume);
	}
	@Override
	public void update(Observable options, Object arg) {
		// TODO Auto-generated method stub
		if (options instanceof SettingsData) {
			msc.setVolume((((SettingsData)options).volume)*0.01f);
		}
	}

}
