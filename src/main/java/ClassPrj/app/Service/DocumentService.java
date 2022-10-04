package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;

import java.util.List;

public interface DocumentService {

    List<DocumentDTO> upload(UploadDocumentWithData toUpload, Long classId, String username);
    void delete(Long documentId,String username);
    byte[] getFile(Long id,Long userid);
}
