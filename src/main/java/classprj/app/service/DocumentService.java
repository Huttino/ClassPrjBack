package classprj.app.service;

import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.request.UploadDocumentWithData;

import java.util.List;

public interface DocumentService {

    List<DocumentDTO> upload(UploadDocumentWithData toUpload, Long classId, String username);
    void delete(Long documentId,String username);
    byte[] getFile(Long id,Long userid);
}
