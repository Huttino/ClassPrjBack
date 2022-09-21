package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class TeacherDTO extends UserDTO {


	private Map<String,Long> hasCreated;

	public TeacherDTO(Long id, String username, String firstName, String lastName,List<String> authorities, Map<String, Long> hasCreated) {
		super(id, username, firstName, lastName,authorities);
		this.hasCreated = hasCreated;
	}

	public TeacherDTO() {
	}

	public Map<String, Long> getHasCreated() {
		return hasCreated;
	}

	public void setHasCreated(Map<String, Long> hasCreated) {
		this.hasCreated = hasCreated;
	}
}
