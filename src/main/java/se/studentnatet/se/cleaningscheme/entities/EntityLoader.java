package se.studentnatet.se.cleaningscheme.entities;

import java.util.List;

/**
 * Interface to be used when
 */
@FunctionalInterface
public interface EntityLoader
{
	List<Entity> getEntities() throws RuntimeException;
}
