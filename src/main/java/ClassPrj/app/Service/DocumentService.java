package ClassPrj.app.Service;

import java.util.List;

import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;

public interface DocumentService {

    public List<DocumentDTO> upload(UploadDocumentWithData toUpload, Long classId, String username);
    public void delete(Long documentId,String username)throws Exception;
    public byte[] getFile(Long id,String username);
}
