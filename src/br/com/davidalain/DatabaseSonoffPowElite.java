package br.com.davidalain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseSonoffPowElite {

	private final List<EntrySonoffPowElite> database;

	public DatabaseSonoffPowElite(String csvFilepath) {
		this.database = DatabaseSonoffPowElite.readFile(csvFilepath);
	}

	public double calculateConsumption(Date minDateIni, Date maxDateEnd) {

		return this.database
				.stream()
				.filter(entry -> entry.belongsTo(minDateIni, maxDateEnd))
				.map(entry -> entry.consumption)
				.reduce(0.0, Double::sum);
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
