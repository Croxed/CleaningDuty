package se.studentnatet.cleaningduty.entities;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Simple API to handle different entities. */
@Service
public class EntityAPI {
  private final List<EntityLoader> loaders = new ArrayList<>();

  /**
   * Method register a implemented {@link EntityLoader}. These loaders loads and extracts Entities
   *
   * @param loader Loader to register
   */
  public void register(EntityLoader loader) {
    loaders.add(loader);
  }

  /** Calls all loaders, collects responses and returns them as a list */
  public List<? extends Entity> getEntities() {
    return loaders.stream()
        .map(EntityLoader::getEntities)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
