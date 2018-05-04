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
	SettingsData data;
	
	public SettingsController(SettingsData data) {
		this.data=data;
	}
	public void changeVolume(float delta) {
		int newDelta = (int)delta;
		data.setVolume(data.getVolume() + newDelta);
		data.notifyObservers();
	}
	public void changeBrightness(int delta) {
		data.setBrightness(data.getBrightness() + delta);
		data.notifyObservers();
		try {
			SettingsController.setBrightnessLogic(data.getBrightness());
		} catch (IOException e) {
			//System.out.println("Could not change brightness");
		}
		
	}
	
	public void goFullScreen(boolean fl) {
		data.setFullscreen(fl);
	}
	
	public static void setBrightnessLogic(int brightness)
			throws IOException {
	        //Creates a powerShell command that will set the brightness to the requested value (0-100), after the requested delay (in milliseconds) has passed. 
	        String s = String.format("$brightness = %d;", brightness)
	                + "$delay = 0;"
	                + "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
	                + "$myMonitor.wmisetbrightness($delay, $brightness)";
	        String command = "powershell.exe  " + s;
	        // Executing the command
	        Process powerShellProcess = Runtime.getRuntime().exec(command);

	        powerShellProcess.getOutputStream().close();

	        //Report any error messages
	        String line;

	        BufferedReader stderr = new BufferedReader(new InputStreamReader(
	                powerShellProcess.getErrorStream()));
	        line = stderr.readLine();
	        if (line != null)
	        {
	            System.err.println("Standard Error:");
	            do
	            {
	                System.err.println(line);
	            } while ((line = stderr.readLine()) != null);

	        }
	        stderr.close();

   }
}
