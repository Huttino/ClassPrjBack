package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class StudentDTO extends UserDTO {
	public Map<String, Long> memberOf;

	public StudentDTO(Long id, String username, String firstName, String lastName, List<String> authorities, Map<String, Long> memberOf) {
		super(id, username, firstName, lastName,authorities);
		this.memberOf = memberOf;
	}

	public StudentDTO() {
    }

	public Map<String, Long> getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(Map<String, Long> memberOf) {
		this.memberOf = memberOf;
	}
	
	
}
