package se.studentnatet.cleaningduty.entities.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.studentnatet.cleaningduty.csv.CSVLoader;
import se.studentnatet.cleaningduty.entities.EntityAPI;
import se.studentnatet.cleaningduty.entities.user.UsersLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class MembersLoader {
  private static final Logger logger = LoggerFactory.getLogger(UsersLoader.class);
  private final CSVLoader csvLoader;

  @Value("${csv.members.path}")
  private String csvPath;

  @Autowired
  public MembersLoader(CSVLoader csvLoader, EntityAPI entityAPI) {
    this.csvLoader = csvLoader;
    entityAPI.register(this::getEntities);
  }

  public List<Member> getEntities() {
    try {
      return csvLoader.loadObjectList(csvPath, Member::new);
    } catch (IOException e) {
      logger.info("Could not load CSV " + Member.class.getSimpleName());
      return Collections.emptyList();
    }
  }
}
