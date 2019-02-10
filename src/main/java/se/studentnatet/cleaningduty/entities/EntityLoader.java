package se.studentnatet.cleaningduty.entities;

import java.util.List;

/**
 * Interface to be used when parsing new objects from CSV
 */
@FunctionalInterface
public interface EntityLoader
{
	List<? extends Entity> getEntities();
}
