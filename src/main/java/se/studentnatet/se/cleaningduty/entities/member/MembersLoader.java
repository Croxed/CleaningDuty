package se.studentnatet.se.cleaningduty.entities.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.studentnatet.se.cleaningduty.entities.EntityAPI;
import se.studentnatet.se.cleaningduty.csv.CSVLoader;

import java.io.IOException;
import java.util.List;

@Component
public class MembersLoader
{
	private final CSVLoader csvLoader;

	@Value("${csv.members.path}")
	private String csvPath;

	@Autowired
	public MembersLoader(CSVLoader csvLoader, EntityAPI entityAPI)
	{
		this.csvLoader = csvLoader;
		entityAPI.register(this::getMembers);
	}

	/**
	 * Returns all user that were found from CSV
	 *
	 * @return
	 * @throws RuntimeException
	 */
	private List<Object> getMembers() throws RuntimeException
	{
		try
		{
			return csvLoader.loadObjectList(csvPath, Member::new);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
