package ClassPrj.app.Model.Dto;

import java.util.List;

public class StudentDTO extends UserDTO {
	public List<ClassInStudent> memberOf;

	public StudentDTO(Long id, String username, String firstName, String lastName, List<String> authorities, List<ClassInStudent> memberOf) {
		super(id, username, firstName, lastName,authorities);
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
