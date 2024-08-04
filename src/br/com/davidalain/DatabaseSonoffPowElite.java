package br.com.davidalain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseSonoffPowElite {

	private static final SimpleDateFormat SDF_DAY_MONTH_YEAR = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat SDF_MONTH_YEAR = new SimpleDateFormat("MM/yyyy");
	private static final SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");

	private final List<EntrySonoffPowElite> database;

	public DatabaseSonoffPowElite(String csvFilepath) {
		this.database = DatabaseSonoffPowElite.readFile(csvFilepath);
	}

	//==
	
	public double calculateConsumption(Date minDateIni, Date maxDateEnd) {

		return this.database
				.stream()
				.filter(entry -> entry.belongsTo(minDateIni, maxDateEnd))
				.map(entry -> entry.consumption)
				.reduce(0.0, Double::sum);
	}
	
	private Map<String, Double> calculateConsumptionBy(SimpleDateFormat sdf) {

		return this.database
				.stream()
				.collect(
						Collectors.groupingBy(
								entry -> sdf.format(entry.dateIni),
								Collectors.mapping(item -> item.consumption, 
										Collectors.reducing(0.0, Double::sum))
								)
						);
	}

	private Map<String, Double> calculateConsumptionBy(Date minDate, Date maxDate, SimpleDateFormat sdf) {

		return this.database
				.stream()
				.filter(entry -> entry.belongsTo(minDate, maxDate))
				.collect(
						Collectors.groupingBy(
								entry -> sdf.format(entry.dateIni),
								Collectors.mapping(item -> item.consumption, 
										Collectors.reducing(0.0, Double::sum))
								)
						);
	}
	
	private List<Entry<String, Double>> calculateConsumptionBySorted(SimpleDateFormat sdf) {

		return calculateConsumptionBy(sdf)
				.entrySet()
				.stream()
				.sorted((e1,e2) -> {
					try {
						return sdf.parse(e1.getKey()).compareTo(sdf.parse(e2.getKey()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				})
				.collect(Collectors.toList());
	}

	private List<Entry<String, Double>> calculateConsumptionBySorted(Date minDate, Date maxDate, SimpleDateFormat simpleDateFormat) {

		return calculateConsumptionBy(minDate, maxDate, simpleDateFormat)
				.entrySet()
				.stream()
				.sorted((e1,e2) -> {
					try {
						return simpleDateFormat.parse(e1.getKey()).compareTo(simpleDateFormat.parse(e2.getKey()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				})
				.collect(Collectors.toList());
	}
	
	//==

	public Map<String, Double> calculateConsumptionByDay(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBy(minDateIni, maxDateEnd, SDF_DAY_MONTH_YEAR);
	}

	public Map<String, Double> calculateConsumptionByMonth(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBy(minDateIni, maxDateEnd, SDF_MONTH_YEAR);
	}

	public Map<String, Double> calculateConsumptionByYear(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBy(minDateIni, maxDateEnd, SDF_YEAR);
	}

	//==
	
	public List<Entry<String, Double>> calculateConsumptionByDaySorted(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBySorted(minDateIni, maxDateEnd, SDF_DAY_MONTH_YEAR);
	}

	public List<Entry<String, Double>> calculateConsumptionByMonthSorted(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBySorted(minDateIni, maxDateEnd, SDF_MONTH_YEAR);
	}

	public List<Entry<String, Double>> calculateConsumptionByYearSorted(Date minDateIni, Date maxDateEnd) {
		return calculateConsumptionBySorted(minDateIni, maxDateEnd, SDF_YEAR);
	}
	
	//==
	
	public Map<String, Double> calculateConsumptionByDay() {
		return calculateConsumptionBy(SDF_DAY_MONTH_YEAR);
	}

	public Map<String, Double> calculateConsumptionByMonth() {
		return calculateConsumptionBy(SDF_MONTH_YEAR);
	}

	public Map<String, Double> calculateConsumptionByYear() {
		return calculateConsumptionBy(SDF_YEAR);
	}

	//==
	
	public List<Entry<String, Double>> calculateConsumptionByDaySorted() {
		return calculateConsumptionBySorted(SDF_DAY_MONTH_YEAR);
	}

	public List<Entry<String, Double>> calculateConsumptionByMonthSorted() {
		return calculateConsumptionBySorted(SDF_MONTH_YEAR);
	}

	public List<Entry<String, Double>> calculateConsumptionByYearSorted() {
		return calculateConsumptionBySorted(SDF_YEAR);
	}
	

	private static List<EntrySonoffPowElite> readFile(String csvFilepath){

		return DatabaseSonoffPowElite.readLines(csvFilepath)
				.stream()
				.skip(1) //pula o cabeçalho
				.map(EntrySonoffPowElite::parse)
				.collect(Collectors.toList());

	}

	/**
	 * 
	 * @param csvFilepath
	 * @return
	 * @throws IOException 
	 */
	private static List<String> readLines(String csvFilepath) {

		final List<String> lines = new LinkedList<String>();

		final Path path = Path.of(csvFilepath);

		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) 
		{
			stream.forEach(s -> lines.add(s));
		}
		catch (IOException e)
		{
			throw new RuntimeException(e.getMessage());
		}

		return lines;
	}

}
