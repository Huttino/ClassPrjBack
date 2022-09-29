package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class TeacherDTO extends UserDTO {


	private List<ClassRoomDTO> hasCreated;

	public TeacherDTO(Long id, String username, String firstName, String lastName,List<String> authorities,List<ClassRoomDTO> hasCreated) {
		super(id, username, firstName, lastName,authorities);
		this.hasCreated = hasCreated;
	}

	public TeacherDTO() {
	}

	public List<ClassRoomDTO> getHasCreated() {
		return hasCreated;
	}

	public void setHasCreated(List<ClassRoomDTO> hasCreated) {
		this.hasCreated = hasCreated;
	}
}
