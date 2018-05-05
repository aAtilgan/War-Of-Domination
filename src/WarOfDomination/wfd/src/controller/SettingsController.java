package controller;

/**
 * @author Ayberk
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.utilitiesmodel.SettingsData;

public class SettingsController {

	// variables
	SettingsData data;

	// constructor
	public SettingsController(SettingsData data) {
		this.data = data;
	}

	// changes the volume of data object according to the change in Settings View
	// class
	// It also notifys observers to let them apply changes immidiately
	public void changeVolume(float delta) {
		int newDelta = (int) delta;
		data.setVolume(data.getVolume() + newDelta);
		data.notifyObservers();
	}

	// Changes the brightness of the game
	// It also notifys observers to let them apply changes immidiately
	public void changeBrightness(int delta) {
		data.setBrightness(data.getBrightness() + delta);
		data.notifyObservers();
		try {
			SettingsController.setBrightnessLogic(data.getBrightness());
		} catch (IOException e) {
			// System.out.println("Could not change brightness");
		}

	}

	public void goFullScreen(boolean fl) {
		data.setFullscreen(fl);
	}

	// Changes the brigtness of the game
	// Helper method for this class
	public static void setBrightnessLogic(int brightness) throws IOException {
		// Creates a powerShell command that will set the brightness to the requested
		// value (0-100), after the requested delay (in milliseconds) has passed.
		String s = String.format("$brightness = %d;", brightness) + "$delay = 0;"
				+ "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
				+ "$myMonitor.wmisetbrightness($delay, $brightness)";
		String command = "powershell.exe  " + s;
		// Executing the command
		Process powerShellProcess = Runtime.getRuntime().exec(command);

		powerShellProcess.getOutputStream().close();

		// Report any error messages
		String line;

		BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
		line = stderr.readLine();

		// Prints error if reading line fails.
		if (line != null) {
			System.err.println("Standard Error:");
			do {
				System.err.println(line);
			} while ((line = stderr.readLine()) != null);

		}
		stderr.close();

	}
}
