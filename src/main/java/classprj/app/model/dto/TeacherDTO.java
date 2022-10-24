package classprj.app.model.dto;

import java.util.List;

public class TeacherDTO extends UserDTO {


	private List<ClassRoomDTO> hasCreated;

	public TeacherDTO(Long id, String username, String firstName, String lastName,String authority,List<ClassRoomDTO> hasCreated) {
		super(id, username, firstName, lastName,authority);
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
