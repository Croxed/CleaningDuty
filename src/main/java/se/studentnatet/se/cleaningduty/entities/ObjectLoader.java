package se.studentnatet.se.cleaningduty.entities;

import java.util.List;

/** Interface to be used when parsing new objects from CSV */
@FunctionalInterface
public interface ObjectLoader {
  List<Object> getEntities() throws RuntimeException;
}
