package ClassPrj.app.Model.Dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDTO {
	private Long id;
	private String title;
	private String type;
	private String notes;
	private TeacherDTO uploadedBy;
	private ClassRoomDTO uploadedTo;
	
	
	
	public DocumentDTO() {
	}



	public DocumentDTO(Long id, String title, String type, String notes, TeacherDTO uploadedBy,
			ClassRoomDTO uploadedTo) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.notes = notes;
		this.uploadedBy = uploadedBy;
		this.uploadedTo = uploadedTo;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public TeacherDTO getUploadedBy() {
		return uploadedBy;
	}



	public void setUploadedBy(TeacherDTO uploadedBy) {
		this.uploadedBy = uploadedBy;
	}



	public ClassRoomDTO getUploadedTo() {
		return uploadedTo;
	}



	public void setUploadedTo(ClassRoomDTO uploadedTo) {
		this.uploadedTo = uploadedTo;
	}
	
	
	
}