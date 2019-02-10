package se.studentnatet.cleaningduty.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.studentnatet.cleaningduty.entities.Entity;
import se.studentnatet.cleaningduty.entities.EntityAPI;

@RestController
class CleaningSchemeController
{

	private final EntityAPI entityAPI;
	private final List<Object> returnList = Collections.synchronizedList(new ArrayList<>());
	private List<? extends Entity> users = new ArrayList<>();

	@Autowired
	CleaningSchemeController(EntityAPI entityAPI)
	{
		this.entityAPI = entityAPI;
	}

	/**
	 * Load all members into list after the application has launched
	 */
	@EventListener
	public void postConstruct(ContextRefreshedEvent re)
	{
		this.users = entityAPI.getEntities();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	/**
	 * Run once every tuesday at 19:30.
	 */
	@Scheduled(cron = "0 30 19 * * TUE")
	private void newScheme()
	{
		if (users.size() < 2) users = entityAPI.getEntities();

		returnList.clear();
		for (int i = 0; i < 2; i++)
		{
			returnList.add(users.remove((int) (Math.random() * users.size())));
		}
	}

	@RequestMapping("/scheme")
	@ResponseBody
	List<Object> getCleaningScheme(
		@RequestParam(value = "reload", defaultValue = "false") Boolean reload)
	{
		if (reload) newScheme();
		return returnList;
	}
}
