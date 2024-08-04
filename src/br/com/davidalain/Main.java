package br.com.davidalain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class Main {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static void main(String[] args) throws ParseException {

		final String path = "C:\\Users\\david\\Downloads\\";
		final String csvFilePath = path + "History_2024.01.28-2024.08.04_UTC -3.csv";

		final String minDateOnlyStr = "21/06/2024";
		final String maxDateOnlyStr = "23/07/2024";

		final String minDateTimeStr = minDateOnlyStr + " 00:00";
		final String maxDateTimeStr = maxDateOnlyStr + " 23:59";

		final DatabaseSonoffPowElite databaseSonoffPowElite = new DatabaseSonoffPowElite(csvFilePath);

		final double consumptionInPeriod = databaseSonoffPowElite.calculateConsumption(
				SDF.parse(minDateTimeStr), 
				SDF.parse(maxDateTimeStr)
				);

		System.out.println(
				"De  " + minDateTimeStr 
				+ "\r\n" 
				+ "Até " + maxDateTimeStr 
				+ "\r\n" 
				+ "Consumo (kWh): " + consumptionInPeriod);

		System.out.println();
		System.out.println("Por dia:\r\n" +
				databaseSonoffPowElite.calculateConsumptionByDaySorted(
						SDF.parse(minDateTimeStr), 
						SDF.parse(maxDateTimeStr)
						)
		.stream()
		.map(entry -> entry.toString())
		.collect(Collectors.joining("\r\n")));

		System.out.println();
		System.out.println("Por mês:\r\n" +
				databaseSonoffPowElite.calculateConsumptionByMonthSorted(
						SDF.parse(minDateTimeStr), 
						SDF.parse(maxDateTimeStr)
						)
		.stream()
		.map(entry -> entry.toString())
		.collect(Collectors.joining("\r\n")));

		System.out.println();
		System.out.println("Por ano:\r\n" +
				databaseSonoffPowElite.calculateConsumptionByYearSorted(
						SDF.parse(minDateTimeStr), 
						SDF.parse(maxDateTimeStr)
						)
		.stream()
		.map(entry -> entry.toString())
		.collect(Collectors.joining("\r\n")));

	}

}
