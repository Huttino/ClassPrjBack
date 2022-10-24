package classprj.app.model.dto;

import java.util.List;

public class ClassRoomDTO {
	
	
	private Long id;
	private String className;
	private String creator;
	private List<DocumentDTO> uploadedDocuments;
	private List<StudentInClass> members;
	private List<VideoLessonDTO> lessons;


	public ClassRoomDTO(Long id, String className, String creator, List<DocumentDTO> uploadedDocuments, List<StudentInClass> members, List<VideoLessonDTO> lessons) {
		this.id = id;
		this.className = className;
		this.creator = creator;
		this.uploadedDocuments = uploadedDocuments;
		this.members = members;
		this.lessons = lessons;
	}

	public ClassRoomDTO() {
	
	}

	public List<DocumentDTO> getUploadedDocuments() {
		return uploadedDocuments;
	}

	public void setUploadedDocuments(List<DocumentDTO> uploadedDocuments) {
		this.uploadedDocuments = uploadedDocuments;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public	List<StudentInClass> getMembers() {
		return members;
	}
	public void setMembers(List<StudentInClass> members) {
		this.members = members;
	}

	public List<VideoLessonDTO> getLessons() {
		return lessons;
	}

	public void setLessons(List<VideoLessonDTO> lessons) {
		this.lessons = lessons;
	}
}
