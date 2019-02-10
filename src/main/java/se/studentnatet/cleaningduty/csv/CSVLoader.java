package se.studentnatet.cleaningduty.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import se.studentnatet.cleaningduty.entities.Entity;

@Component
public class CSVLoader
{
	/**
	 * Returns a list of user found in CSV
	 *
	 * @param filename
	 * 	path to CSV
	 *
	 * @return List of {@link Entity}
	 */
	public <T extends Entity> List<T> loadObjectList(String filename, Function<CSVRecord, T> mapper)
		throws IOException
	{
		if (filename.isEmpty()) return Collections.emptyList();

		try (Reader reader = Files.newBufferedReader(Paths.get(filename)))
		{
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

			// Java Streams are cool. We use them to minimize the usage of one-line for-loops.
			// We can pass any method that has CSVRecord as parameter and returns Entity as parameter to
			// this stream
			return StreamSupport.stream(records.spliterator(), true)
				.map(mapper)
				.collect(Collectors.toList());
		}
	}
}
