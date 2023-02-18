package com.zbcn.dymola;

import java.io.File;

import com.dassault_systemes.dymola.DymolaException;
import com.dassault_systemes.dymola.DymolaInterface;
import com.dassault_systemes.dymola.DymolaWrapper;

public class MultithreadingExample
{
	static String basePath;

	public static class CoupledClutchesThread implements Runnable
	{
		@Override
		public void run()
		{
			DymolaInterface dymola = null;
			try {
				System.out.println("1: Starting Dymola instance");
				dymola = DymolaWrapper.getInstance();
				System.out.println("1: Using port " + ((DymolaWrapper) dymola).portnumber);
				String path = basePath + "/CoupledClutches";
				System.out.println("1: Change working directory to " + path);
				File folder = new File(path);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				boolean result = dymola.cd(path);
				if (!result) {
					System.err.println("1: Failed to change working directory");
				}

				System.out.println("1: Simulating model");
				result = dymola.simulateModel("Modelica.Mechanics.Rotational.Examples.CoupledClutches");
				if (!result) {
					System.err.println("1: Simulation failed");
					String log = dymola.getLastErrorLog();
					System.err.println(log);
				}

				System.out.println("1: Plotting");
				result = dymola.plot(new String[] { "J1.w", "J2.w", "J3.w", "J4.w" });
				if (!result) {
					System.err.println("1: Plot failed");
				}

				System.out.println("1: Saving the plot");
				result = dymola.ExportPlotAsImage(path + "/plot.png");
				if (!result) {
					System.err.println("1: Failed to save the plot");
				}

				System.out.println("1: Saving log");
				dymola.savelog(path + "/log.txt");

				System.out.println("1: OK");
			} catch (DymolaException e) {
				System.err.println("Connection to Dymola failed. " + e.getMessage());
			} finally {
				dymola = null;
			}
		}
	}

	public static class FullRobotThread implements Runnable
	{
		@Override
		public void run()
		{
			DymolaInterface dymola = null;
			try {
				System.out.println("2: Starting Dymola instance");
				dymola = DymolaWrapper.getInstance();
				System.out.println("2: Using port " + ((DymolaWrapper) dymola).portnumber);
				String path = basePath + "/fullRobot";
				System.out.println("2: Change working directory to " + path);
				File folder = new File(path);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				boolean result = dymola.cd(path);
				if (!result) {
					System.err.println("2: Failed to change working directory");
				}

				System.out.println("2: Simulating model");
				result = dymola.simulateModel("Modelica.Mechanics.MultiBody.Examples.Systems.RobotR3.fullRobot");
				if (!result) {
					System.err.println("2: Simulation failed");
					String log = dymola.getLastErrorLog();
					System.err.println(log);
				}

				System.out.println("2: Plotting");
				result = dymola.plot(new String[] { "mechanics.q[1]", "mechanics.q[2]" });
				if (!result) {
					System.err.println("2: Plot failed");
				}

				System.out.println("2: Saving the plot");
				result = dymola.ExportPlotAsImage(path + "/plot.png");
				if (!result) {
					System.err.println("2: Failed to save the plot");
				}

				System.out.println("2: Saving log");
				dymola.savelog(path + "/log.txt");

				System.out.println("2: OK");
			} catch (DymolaException e) {
				System.err.println("Connection to Dymola failed. " + e.getMessage());
			} finally {
				dymola = null;
			}
		}
	}

	public static void main(String[] args)
	{
		// Set this flag to false if you want Dymola to be visible.
		// By default Dymola is hidden.
		//DymolaWrapper.nowindow = false;

		// determine proper base path
		String osName = System.getProperty("os.name");
		if (osName.substring(0,3).equals("Win")) {
			basePath = "C:/temp/Dymola";
		} else {
			basePath = "/tmp/Dymola";
		}

		Thread coupledClutchesThread = new Thread(new CoupledClutchesThread());
		Thread fullRobotThread = new Thread(new FullRobotThread());

		coupledClutchesThread.start();
		fullRobotThread.start();
	}
}
