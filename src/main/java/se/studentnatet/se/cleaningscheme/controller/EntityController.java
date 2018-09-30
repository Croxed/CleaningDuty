package se.studentnatet.se.cleaningscheme.controller;

import org.springframework.stereotype.Component;
import se.studentnatet.se.cleaningscheme.entities.Entity;
import se.studentnatet.se.cleaningscheme.entities.EntityLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple controller to handle different entities.
 */
@Component
public class EntityController
{
	private List<EntityLoader> loaders = new ArrayList<>();

	public EntityController()
	{
	}

	/**
	 * Method register a implemented {@link EntityLoader}
	 * These loaders loads and extracts Entities
	 * @param loader Loader to register
	 */
	public void register(EntityLoader loader)
	{
		loaders.add(loader);
	}

	/**
	 * Calls all loaders, collects responses and returns them as a list
	 *
	 * @return
	 */
	List<Entity> getEntities()
	{
		return loaders.stream().map(EntityLoader::getEntities).flatMap(List::stream).collect(Collectors.toList());
	}
}
