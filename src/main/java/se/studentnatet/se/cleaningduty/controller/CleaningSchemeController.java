package se.studentnatet.se.cleaningduty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.studentnatet.se.cleaningduty.entities.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
class CleaningSchemeController
{

	private final EntityController entityController;
	private List<Entity> users = new ArrayList<>();
	private List<Entity> returnList = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	CleaningSchemeController(EntityController entityController)
	{

		this.entityController = entityController;
	}

	/**
	 * Load all members into list after the application has launched
	 *
	 */
	@EventListener
	public void postConstruct(ContextRefreshedEvent re)
	{
		this.users = entityController.getEntities();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	/**
	 * Run once every tuesday at 19:30.
	 *
	 */
	@Scheduled(cron = "0 30 19 * * TUE")
	private void newScheme()
	{
		if (users.size() < 2)
			users = entityController.getEntities();

		returnList.clear();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	@RequestMapping("/scheme")
	@ResponseBody
	List<Entity> getCleaningScheme(
		@RequestParam(value = "reload",
					  defaultValue = "false")
			Boolean reload) throws IOException
	{
		if (reload)
			newScheme();
		return returnList;
	}
}
