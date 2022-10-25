package classprj.app.model.dto;

import java.util.List;

public class StudentDTO extends UserDTO {
	public List<ClassInStudent> memberOf;

	public StudentDTO(Long id, String username, String firstName, String lastName, String authority, List<ClassInStudent> memberOf) {
		super(id, username, firstName, lastName,authority);
		this.memberOf = memberOf;
	}

	public StudentDTO() {
    }

	public List<ClassInStudent> getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(List<ClassInStudent> memberOf) {
		this.memberOf = memberOf;
	}
	
	
}
