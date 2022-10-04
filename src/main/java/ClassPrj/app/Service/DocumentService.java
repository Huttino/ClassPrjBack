package ClassPrj.app.Service;

import ClassPrj.app.Model.Dto.DocumentDTO;
import ClassPrj.app.Model.Request.UploadDocumentWithData;

import java.util.List;

public interface DocumentService {

    public List<DocumentDTO> upload(UploadDocumentWithData toUpload, Long classId, String username);
    public void delete(Long documentId,String username)throws Exception;
    public byte[] getFile(Long id,Long userid);
}
