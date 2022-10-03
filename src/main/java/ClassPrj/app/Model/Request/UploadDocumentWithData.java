package ClassPrj.app.Model.Request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadDocumentWithData {
	
	@NotNull
	private List<MultipartFile> file;
	private String notes;
	
	
	public UploadDocumentWithData(@NotNull List<MultipartFile> file, String notes) {
		super();
		this.file = file;
		this.notes = notes;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
