package ClassPrj.app.Mapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.yaml.snakeyaml.events.DocumentEndEvent;

import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;
import ClassPrj.app.domain.Document;
import net.bytebuddy.asm.Advice.This;

public class DocumentMapper {
	public static Document RequestToEntity(UploadDocumentWithData toAdapt) throws IOException {
		Document toReturn=new Document();
		toReturn.setFile(toAdapt.getFile().getBytes());
		toReturn.setNotes(toAdapt.getNotes());
		toReturn.setTitle(StringUtils.cleanPath(toAdapt.getFile().getOriginalFilename()));
		toReturn.setType(toAdapt.getFile().getContentType());
		toReturn.setDateOfUpload(LocalDateTime.now(ZoneId.systemDefault()));
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
