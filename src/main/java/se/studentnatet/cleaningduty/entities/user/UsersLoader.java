package se.studentnatet.cleaningduty.entities.user;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import se.studentnatet.cleaningduty.csv.CSVLoader;
import se.studentnatet.cleaningduty.entities.EntityAPI;

@Component
public class UsersLoader
{
	private static final Logger logger = LoggerFactory.getLogger(UsersLoader.class);
	private final CSVLoader csvLoader;

	@Value("${csv.users.path}")
	private String csvPath;

	@Autowired
	public UsersLoader(CSVLoader csvLoader, EntityAPI entityAPI)
	{
		this.csvLoader = csvLoader;
		entityAPI.register(this::getEntities);
	}

	/**
	 * Returns all user that were found from CSV
	 */
	public List<User> getEntities()
	{
		try
		{
			return csvLoader.loadObjectList(csvPath, User::new);
		}
		catch (IOException e)
		{
			logger.info("Could not load CSV for " + User.class.getSimpleName());
			return Collections.emptyList();
		}
	}
}
