package model.utilitiesmodel;
/**
 * @author Ayberk
 *
 */
public class SettingsData extends java.util.Observable {
	// data
	int volume;
	int brightness;
	boolean fullscreen;
	boolean paused;

	public SettingsData() {
		volume = 20;
		brightness = 25;
		fullscreen = false;
		paused = false;
	}

	public int getVolume() {
		return volume;
	}
	public void setVolume(int vol) {
		if (vol>100) vol=100;
		else if(vol<0) vol=0;
		this.volume=vol;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int br) {
		if(br <0) br=0;
		else if(br>100) br=0;
		this.brightness=br;
	}
	public boolean isFullscreen() {
		return fullscreen;
	}
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen=fullscreen;
		setChanged();
		notifyObservers();
		clearChanged();
	}
}
