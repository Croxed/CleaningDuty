package se.studentnatet.se.cleaningscheme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.studentnatet.se.cleaningscheme.csv.User;
import se.studentnatet.se.cleaningscheme.csv.UsersLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
class CleaningSchemeController
{

	private final UsersLoader loader;
	private List<User> users = new ArrayList<>();
	private List<User> returnList = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	CleaningSchemeController(UsersLoader loader)
	{
		this.loader = loader;
	}

	/**
	 * Load all members into list after the application has launched
	 *
	 * @throws IOException
	 */
	@EventListener
	public void postConstruct(ContextRefreshedEvent re) throws IOException
	{
		this.users = loader.getUsers();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	/**
	 * Run once every tuesday at 19:30.
	 *
	 * @throws IOException
	 */
	@Scheduled(cron = "0 30 19 * * TUE")
	private void newScheme() throws IOException
	{
		if (users.size() < 2)
			users = loader.getUsers();

		returnList.clear();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	@RequestMapping("/scheme")
	@ResponseBody
	List<User> getCleaningScheme(
		@RequestParam(value = "reload",
					  defaultValue = "false")
			Boolean reload) throws IOException
	{
		if (reload)
			newScheme();
		return returnList;
	}
}
