package ClassPrj.app.Model.Dto;

import java.time.LocalDateTime;


public class DocumentDTO {
	private Long id;
	private String title;
	private String type;
	private String notes;
	private LocalDateTime dateOfUpdate;
	
	
	
	public DocumentDTO() {
	}



	public DocumentDTO(Long id, String title, String type, String notes, TeacherDTO uploadedBy,
			ClassRoomDTO uploadedTo,LocalDateTime dateOfUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.notes = notes;
		this.dateOfUpdate=dateOfUpdate;
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




	public LocalDateTime getDateOfUpdate() {
		return dateOfUpdate;
	}



	public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}
	
	
	
}
