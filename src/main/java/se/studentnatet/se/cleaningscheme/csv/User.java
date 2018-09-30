package se.studentnatet.se.cleaningscheme.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.csv.CSVRecord;

@Getter
@AllArgsConstructor
public class User
{
	@JsonProperty("first_name")
	public final String firstName;
	@JsonProperty("last_name")
	public final String lastName;
	@JsonProperty("login")
	public final String login;

	/**
	 * Build a User from a {@link CSVRecord}
	 *
	 * @param record
	 * @return
	 */
	static User fromRecord(CSVRecord record)
	{
		return new User(record.get("firstname"), record.get("lastname"), record.get("login"));
	}
}
