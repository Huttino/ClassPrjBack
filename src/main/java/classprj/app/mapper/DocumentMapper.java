package classprj.app.mapper;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Document;
import classprj.app.domain.Teacher;
import classprj.app.model.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class DocumentMapper {
	public static Document RequestToEntity(MultipartFile x , String pathFile, String notes, ClassRoom uploadedTo, Teacher uploader){
		Document toReturn=new Document();
		toReturn.setTitle(x.getOriginalFilename());
		toReturn.setType(x.getContentType());
		toReturn.setRelatedTo(null);
		toReturn.setNotes(notes);
		toReturn.setUploadedTo(uploadedTo);
		toReturn.setUploadedBy(uploader);
		toReturn.setPathFile(pathFile);
		toReturn.setDateOfUpload(LocalDateTime.now());
		return toReturn;
	}

	public static DocumentDTO entityToDTO(Document docu) {
		DocumentDTO toReturn=new DocumentDTO();
		toReturn.setId(docu.getId());
		toReturn.setNotes(docu.getNotes());
		toReturn.setTitle(docu.getTitle());
		toReturn.setType(docu.getType());
		toReturn.setDateOfUpdate(docu.getDateOfUpload());
		return toReturn;
	}
}
