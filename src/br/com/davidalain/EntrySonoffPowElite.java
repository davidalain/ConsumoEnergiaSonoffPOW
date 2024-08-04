package br.com.davidalain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntrySonoffPowElite {

	//2024/8/4 11:00
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	public final Date dateIni; 			//Data e hora inicial
	public final Date dateEnd;			//Data e hora final
	public final Double consumption;	//Consumo em kWh

	public EntrySonoffPowElite(Date dateIni, Date dateEnd, Double consumption) {
		super();
		this.dateIni = dateIni;
		this.dateEnd = dateEnd;
		this.consumption = consumption;
	}

	public boolean belongsTo(Date minDateIni, Date maxDateEnd) {
		return ((minDateIni.compareTo(this.dateIni) <= 0) && (maxDateEnd.compareTo(this.dateEnd) >= 0));
	}

	/**
	 * Pattern:
	 * 		data,time,consumption/KWh
	 * 
	 * Example:
	 * 		2024/8/4,11:00-12:00,0.05
	 * 
	 * @param csvLine
	 * @return
	 * @throws ParseException 
	 */
	public static EntrySonoffPowElite parse(String csvLine) {

		try {

			final String[] parts = csvLine.split(",");
			final String[] timeFromTo = parts[1].split("-");

			final String dateIniStr = parts[0] + " " + timeFromTo[0];
			final String dateEndStr = parts[0] + " " + timeFromTo[1];
			final String consumptionStr = parts[2];

			final Date dateIni = SDF.parse(dateIniStr);
			final Date dateEnd = SDF.parse(dateEndStr);
			final Double consumption = Double.valueOf(consumptionStr);

			return new EntrySonoffPowElite(dateIni, dateEnd, consumption);

		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}



}
