package ClassPrj.app.Model.Dto;

import java.util.List;
import java.util.Map;

public class ClassRoomDTO {
	
	
	private Long id;
	private String className;
	private TeacherDTO creator;
	private Map<String, Long> members;
	
	
	
	public ClassRoomDTO(Long id, String className, TeacherDTO creator, Map<String,Long> members) {
		super();
		this.id = id;
		this.className = className;
		this.creator = creator;
		this.members = members;
	}
	
	public ClassRoomDTO() {
	
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public TeacherDTO getCreator() {
		return creator;
	}
	public void setCreator(TeacherDTO creator) {
		this.creator = creator;
	}
	public Map<String,Long> getMembers() {
		return members;
	}
	public void setMembers(Map<String,Long> members) {
		this.members = members;
	}
	
	
}
