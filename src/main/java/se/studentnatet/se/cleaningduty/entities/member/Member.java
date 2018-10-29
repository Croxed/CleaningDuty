package se.studentnatet.se.cleaningduty.entities.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.csv.CSVRecord;

public class Member {
  @JsonProperty("first_name")
  public final String firstName;

  @JsonProperty("last_name")
  public final String lastName;

  @JsonProperty("login")
  public final String login;

  @JsonProperty("rank")
  public final String rank;

  /**
   * Build a User from a {@link CSVRecord}
   *
   * @param record
   * @return
   */
  public Member(CSVRecord record) {
    firstName = record.get("firstname");
    lastName = record.get("lastname");
    login = record.get("login");
    rank = record.get("rank");
  }
}
