package br.com.davidalain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static void main(String[] args) throws ParseException {

		final String path = "C:\\Users\\david\\Downloads\\";
		final String csvFilePath = path + "History_2024.02.25-2024.09.01_UTC -3 72.csv";

		final String minDateToFilterStr = "15/07/2024";
		final String maxDateToFilterStr = "15/08/2024";

		final String minDateTimeToFilterStr = minDateToFilterStr + " 12:00";
		final String maxDateTimeToFilterStr = maxDateToFilterStr + " 12:00";
		
		//========
		
		final DatabaseSonoffPowElite databaseSonoffPowElite = new DatabaseSonoffPowElite(csvFilePath);

		final Date minDateTime = SDF.parse(minDateTimeToFilterStr);
		final Date maxDateTime = SDF.parse(maxDateTimeToFilterStr);

		System.out.println(
				"De  " + minDateTimeToFilterStr 
				+ "\r\n" 
				+ "Até " + maxDateTimeToFilterStr 
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
