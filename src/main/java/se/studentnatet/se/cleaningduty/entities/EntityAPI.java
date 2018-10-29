package se.studentnatet.se.cleaningduty.entities;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Simple controller to handle different entities. */
@Service
public class EntityAPI {
  private final List<ObjectLoader> loaders = new ArrayList<>();

  /**
   * Method register a implemented {@link ObjectLoader}. These loaders loads and extracts Entities
   *
   * @param loader Loader to register
   */
  public void register(ObjectLoader loader) {
    loaders.add(loader);
  }

  /**
   * Calls all loaders, collects responses and returns them as a list
   *
   * @return
   */
  public List<Object> getEntities() {
    return loaders
        .stream()
        .map(ObjectLoader::getEntities)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
