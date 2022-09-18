package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class StudentDTO extends UserDetailsDTO {
	public Map<String, Long> memberOf;

	public StudentDTO(Long id, String username, List<String> authorithies, Map<String, Long> memberOf) {
		super(id, username, authorithies);
		this.memberOf = memberOf;
	}

	public Map<String, Long> getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(Map<String, Long> memberOf) {
		this.memberOf = memberOf;
	}
	
	
}
