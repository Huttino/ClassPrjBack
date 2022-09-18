package ClassPrj.app.domain;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="document")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="type")
	private String type;
	
	@Column (name="file")
	@Lob
	private byte[] file;
	
	@Column(name="notes")
	private String notes;
	
	@ManyToOne
	@JoinColumn(name="uploadedBy")
	private Teacher uploadedBy;
	
	@ManyToOne
	@JoinColumn(name="ClassId")
	private ClassRoom uploadedTo;



	public Document() {
	}

	public Document(Long id, String title, String type, byte[] file, String notes, Teacher uploadedBy,
			ClassRoom uploadedTo) throws IOException {
		this.id = id;
		this.title = title;
		this.type = type;
		this.file = file;
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
	
	
}
