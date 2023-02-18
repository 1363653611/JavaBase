package com.zbcn.dymola;

import com.dassault_systemes.dymola.DymolaException;
import com.dassault_systemes.dymola.DymolaInterface;
import com.dassault_systemes.dymola.DymolaWrapper;

public class SimulateExtendedExample
{
	private static void RunExample1(DymolaInterface dymola) throws DymolaException
	{
		System.out.println("simulateExtendedModel");

		String[] initialNames = { "J1.J", "J2.J" };
		double[] initialValues = { 2, 3 };
		String[] finalNames = { "J1.w", "J4.w" };

		Object[] output = dymola.simulateExtendedModel("Modelica.Mechanics.Rotational.Examples.CoupledClutches", 0.0, 1.0, 0, 0.0, "Dassl", 0.0001, 0.0, "test3", initialNames, initialValues, finalNames, true);

		if (output.length != 2) {
			System.err.println("Incorrect number of output parameters.");
			return;
		}

		boolean status = (Boolean) output[0];
		if (!status) {
			System.err.println("Simulation failed.");
			String log = dymola.getLastErrorLog();
			System.err.println(log);
			return;
		}

		double[] values = (double[]) output[1];
		if (values.length != 2) {
			System.err.println("Incorrect number of end-point values.");
			return;
		}
		double J1_w = values[0];
		double J4_w = values[1];
		System.out.println("J1.w=" + J1_w + "  J4.w=" + J4_w);
		System.out.println();
	}

	private static void RunExample2(DymolaInterface dymola) throws DymolaException
	{
		System.out.println("simulateMultiExtendedModel");

		String[] initialNames = { "J1.J", "J2.J" };
		double[][] initialValues = { {2,3}, {3,4}, {4,5} };
		String[] finalNames = { "J1.w", "J4.w" };

		Object[] output = dymola.simulateMultiExtendedModel("Modelica.Mechanics.Rotational.Examples.CoupledClutches", 0.0, 1.0, 0, 0.0, "Dassl", 0.0001, 0.0, "dsres", initialNames, initialValues, finalNames);

		if (output.length != 2) {
			System.err.println("Incorrect number of output parameters.");
			return;
		}

		boolean status = (Boolean) output[0];
		if (!status) {
			System.err.println("Simulation failed.");
			String log = dymola.getLastErrorLog();
			System.err.println(log);
			return;
		}

		double[][] values = (double[][]) output[1];
		if (values.length != 3) {
			System.err.println("Incorrect number of end-point values.");
			return;
		}
		if (values[0].length != 2 || values[1].length != 2 || values[2].length != 2) {
			System.err.println("Incorrect dimension of end-point matrix.");
			return;
		}

		double[] result1 = values[0];
		double J1_w1 = result1[0];
		double J4_w1 = result1[1];
		System.out.println("J1.w=" + J1_w1 + "  J4.w=" + J4_w1);

		double[] result2 = values[1];
		double J1_w2 = result2[0];
		double J4_w2 = result2[1];
		System.out.println("J1.w=" + J1_w2 + "  J4.w=" + J4_w2);

		double[] result3 = values[2];
		double J1_w3 = result3[0];
		double J4_w3 = result3[1];
		System.out.println("J1.w=" + J1_w3 + "  J4.w=" + J4_w3);
		System.out.println();
	}

	private static void RunExample3(DymolaInterface dymola) throws DymolaException
	{
		System.out.println("string parameter");
		boolean result = dymola.simulateModel("A(s=\"foo\")");
		if (!result) {
			System.err.println("Simulation failed.");
			String log = dymola.getLastErrorLog();
			System.err.println(log);
		}
	}

	public static void main(String[] args)
	{
		DymolaInterface dymola = null;
		try {
			dymola = DymolaWrapper.getInstance();
			RunExample1(dymola);
			RunExample2(dymola);
			RunExample3(dymola);
			System.out.println("OK");
		} catch (DymolaException e) {
			System.err.println("Connection to Dymola failed. " + e.getMessage());
		} finally {
			dymola = null;
		}
	}
}
