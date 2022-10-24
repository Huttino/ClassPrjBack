package classprj.app.domain;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;


@Entity
@Table(name="document")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title",nullable = false)
	private String title;
	
	@Column(name="type",nullable = false)
	private String type;
	
	@Column (name="file",nullable = false)
	@Lob
	private byte[] file;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="dateofUpload")
	private LocalDateTime dateOfUpload;
	
	@ManyToOne
	@JoinColumn(name="uploadedBy",nullable = false)
	private Teacher uploadedBy;
	
	@ManyToOne
	@JoinColumn(name="ClassId",nullable = false)
	private ClassRoom uploadedTo;



	public Document() {
	}

	public Document(Long id, String title, String type, byte[] file, String notes, Teacher uploadedBy,
			ClassRoom uploadedTo,LocalDateTime dateOfUpload) throws IOException {
		this.id = id;
		this.title = title;
		this.type = type;
		this.file = file;
		this.notes = notes;
		this.uploadedBy = uploadedBy;
		this.uploadedTo = uploadedTo;
		this.dateOfUpload=dateOfUpload;
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file){
		this.file = file;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Teacher getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(Teacher uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public ClassRoom getUploadedTo() {
		return uploadedTo;
	}

	public void setUploadedTo(ClassRoom uploadedTo) {
		this.uploadedTo = uploadedTo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(LocalDateTime dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}
	
	
}