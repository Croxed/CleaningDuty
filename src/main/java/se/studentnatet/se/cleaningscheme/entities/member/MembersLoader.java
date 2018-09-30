package se.studentnatet.se.cleaningscheme.entities.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.studentnatet.se.cleaningscheme.controller.EntityController;
import se.studentnatet.se.cleaningscheme.csv.CSVLoader;
import se.studentnatet.se.cleaningscheme.entities.Entity;

import java.io.IOException;
import java.util.List;

@Component
public class MembersLoader
{
	private final CSVLoader csvLoader;

	@Value("${csv.members.path}")
	private String csvPath;

	@Autowired
	public MembersLoader(CSVLoader csvLoader, EntityController entityController)
	{
		this.csvLoader = csvLoader;
		entityController.register(this::getMembers);
	}

	/**
	 * Returns all user that were found from CSV
	 *
	 * @return
	 * @throws RuntimeException
	 */
	public List<Entity> getMembers() throws RuntimeException
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
