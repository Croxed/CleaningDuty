package se.studentnatet.se.cleaningduty.entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.studentnatet.se.cleaningduty.csv.CSVLoader;
import se.studentnatet.se.cleaningduty.entities.EntityAPI;

import java.io.IOException;
import java.util.List;

@Component
public class UsersLoader {
  private final CSVLoader csvLoader;

  @Value("${csv.users.path}")
  private String csvPath;

  @Autowired
  public UsersLoader(CSVLoader csvLoader, EntityAPI entityAPI) {
    this.csvLoader = csvLoader;
    entityAPI.register(this::getUsers);
  }

  /**
   * Returns all user that were found from CSV
   *
   * @return
   * @throws RuntimeException
   */
  private List<Object> getUsers() throws RuntimeException {
    try {
      return csvLoader.loadObjectList(csvPath, User::new);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
