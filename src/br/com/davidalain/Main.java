package br.com.davidalain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static void main(String[] args) throws ParseException {

		final String path = "C:\\Users\\david\\Downloads\\";
		final String csvFilePath = path + "History_2024.01.28-2024.08.04_UTC -3.csv";

		final String minDateOnlyStr = "21/06/2024";
		final String maxDateOnlyStr = "23/07/2024";

		final String minDateTimeStr = minDateOnlyStr + " 00:00";
		final String maxDateTimeStr = maxDateOnlyStr + " 23:59";
		
		//========
		
		final DatabaseSonoffPowElite databaseSonoffPowElite = new DatabaseSonoffPowElite(csvFilePath);

		final Date minDateTime = SDF.parse(minDateTimeStr);
		final Date maxDateTime = SDF.parse(maxDateTimeStr);

		System.out.println(
				"De  " + minDateTimeStr 
				+ "\r\n" 
				+ "Até " + maxDateTimeStr 
				+ "\r\n" 
				+ "Consumo (kWh): " 
				+ databaseSonoffPowElite.calculateConsumption(minDateTime, maxDateTime));

		System.out.println();
		System.out.println("Por dia:\r\n" + 
				databaseSonoffPowElite.toStringByDaySorted(minDateTime, maxDateTime));

		System.out.println();
		System.out.println("Por mês:\r\n" +
				databaseSonoffPowElite.toStringByMonthSorted(minDateTime, maxDateTime));

		System.out.println();
		System.out.println("Por ano:\r\n" +
				databaseSonoffPowElite.toStringByYearSorted(minDateTime, maxDateTime));
	}


}
