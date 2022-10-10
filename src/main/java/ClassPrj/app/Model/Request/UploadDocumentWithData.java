package ClassPrj.app.Model.Request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UploadDocumentWithData {
	
	@NotNull
	private List<MultipartFile> file;
	private String notes;
	
	
	public UploadDocumentWithData(
			List<MultipartFile> file,
			String notes) {
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
