package classprj.app.mapper;

import classprj.app.exception.ApiException;
import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.request.UploadDocumentWithData;
import classprj.app.domain.Document;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DocumentMapper {
	public static List<Document> RequestToEntity(UploadDocumentWithData toAdapt){
		List<Document> toReturn=new ArrayList<Document>();
		toAdapt.getFile().forEach((x)->{
			Document toBeAdded=new Document();
			try {
				toBeAdded.setFile(x.getBytes());
			} catch (IOException e) {
				throw new ApiException("error during the upload of the file");
			}
			toBeAdded.setNotes(toAdapt.getNotes());
			toBeAdded.setTitle(StringUtils.cleanPath(x.getOriginalFilename()));
			toBeAdded.setType(x.getContentType());
			toBeAdded.setDateOfUpload(LocalDateTime.now(ZoneId.systemDefault()));
			toReturn.add(toBeAdded);
		});
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
