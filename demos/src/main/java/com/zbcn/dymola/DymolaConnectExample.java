package com.zbcn.dymola;

import com.dassault_systemes.dymola.DymolaException;
import com.dassault_systemes.dymola.DymolaInterface;
import com.dassault_systemes.dymola.DymolaWrapper;

public class DymolaConnectExample
{
	public static void main(String[] args)
	{
		// determine OS
		String osName = System.getProperty("os.name");
		Boolean isWindows = osName.substring(0,3).equals("Win");

		DymolaInterface dymola = null;
		try {
			// Connect to an existing Dymola instance that has been started with "-serverport 8082"
			dymola = DymolaWrapper.connectTo(8082);

			// Call a function in Dymola and check its return value
			boolean result = dymola.translateModel("Modelica.Mechanics.Rotational.Examples.CoupledClutches");
			if (!result) {
				System.err.println("Translation failed.");
				// Get the translation log and print it
				String log = dymola.getLastErrorLog();
				System.err.println(log);
				return;
			}

			// Simulate the model
			result = dymola.simulateModel("", 0, 1.2);
			if (!result) {
				System.err.println("Simulation failed.");
				// Get the simulation log and print it
				String log = dymola.getLastErrorLog();
				System.err.println(log);
				return;
			}

			// Plot a few variables
			result = dymola.plot(new String[]{"J1.w", "J2.w", "J3.w", "J4.w"});
			if (!result) {
				System.err.println("Plot failed.");
				return;
			}

			// Save the plot as a PNG file
			String plotPath;
			if (isWindows) {
				plotPath = "C:/temp/plot.png";
			} else {
				plotPath = "/tmp/plot.png";
			}
			result = dymola.ExportPlotAsImage(plotPath);
			System.out.println("OK");
		} catch (DymolaException e) {
			System.err.println("Connection to Dymola failed. " + e.getMessage());
		} finally {
			// The connection to Dymola is closed and Dymola is terminated
			dymola = null;
		}
	}
}
