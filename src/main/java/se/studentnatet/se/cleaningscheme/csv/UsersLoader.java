package se.studentnatet.se.cleaningscheme.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UsersLoader
{
	private final CSVLoader csvLoader;

	@Value("${csv.members.path}")
	private String csvPath;

	@Autowired
	public UsersLoader(CSVLoader csvLoader)
	{
		this.csvLoader = csvLoader;
	}

	/**
	 * Returns all user that were found from CSV
	 *
	 * @return
	 * @throws IOException
	 */
	public List<User> getUsers() throws IOException
	{
		return csvLoader.loadObjectList(csvPath);
	}
}
