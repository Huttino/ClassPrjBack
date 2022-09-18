package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class TeacherDTO {

	
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private Map<String,Long> hasCreated;
	
	public TeacherDTO() {
	}
	
	public TeacherDTO(Long id, String username, String firstName, String lastName, Map<String, Long> hasCreated) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hasCreated = hasCreated;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Map<String, Long> getHasCreated() {
		return hasCreated;
	}
	public void setHasCreated(Map<String, Long> hasCreated) {
		this.hasCreated = hasCreated;
	}
	
	
	
}
