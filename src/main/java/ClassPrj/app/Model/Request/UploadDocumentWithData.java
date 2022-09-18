package ClassPrj.app.Model.Request;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadDocumentWithData {
	
	@NotNull
	private MultipartFile file;
	private String notes;
	
	
	public UploadDocumentWithData(@NotNull MultipartFile file, String notes) {
		super();
		this.file = file;
		this.notes = notes;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
