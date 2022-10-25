package classprj.app.service.impl;

import classprj.app.exception.ApiException;
import classprj.app.mapper.DocumentMapper;
import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.request.UploadDocumentWithData;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.DocumentRepository;
import classprj.app.repository.TeacherRepository;
import classprj.app.repository.VideoLessonRepository;
import classprj.app.service.DocumentService;
import classprj.app.domain.ClassRoom;
import classprj.app.domain.Document;
import classprj.app.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRoomRepository classRoomRepository;
    private final VideoLessonRepository videoLessonRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, TeacherRepository teacherRepository,
                               ClassRoomRepository classRoomRepository, VideoLessonRepository videoLessonRepository) {
        super();
        this.documentRepository = documentRepository;
        this.teacherRepository = teacherRepository;
        this.classRoomRepository = classRoomRepository;
        this.videoLessonRepository=videoLessonRepository;
    }

    @Override
    public List<DocumentDTO> upload(UploadDocumentWithData toUpload, Long classId, String username) {
        List<Document> toSave=DocumentMapper.RequestToEntity(toUpload);
        Optional<Teacher> uploader=this.teacherRepository.findByUsername(username);
        if(uploader.isEmpty())throw new ApiException("Teacher not found", HttpStatus.NOT_FOUND.value());
        Optional<ClassRoom> uploadedTo=this.classRoomRepository.findById(classId);
        if(uploadedTo.isEmpty()) throw new ApiException("ClassRoom not found",HttpStatus.NOT_FOUND.value());
        toSave.forEach(x->{
            x.setUploadedBy(uploader.get());
            x.setUploadedTo(uploadedTo.get());
            x.setNotes(toUpload.getNotes());
        });
        List<DocumentDTO> savedDocuments=new ArrayList<>();
        toSave.forEach(x-> savedDocuments.add(DocumentMapper.entityToDTO( this.documentRepository.save(x))));
        return savedDocuments;
    }

    @Override
    public void delete(Long documentId,String username) {
        Optional<Document> toBeDeleted= this.documentRepository.findById(documentId);
        if (toBeDeleted.isEmpty())throw new ApiException("Document not found",HttpStatus.NOT_FOUND.value());
        Document toDelete=toBeDeleted.get();
        if (toDelete.getUploadedBy().getUsername().equals(username)) {
            toDelete.getRelatedTo().forEach(
                    x-> {
                        x.getRelatedDocuments().remove(toDelete);
                        this.videoLessonRepository.save(x);
                    }
            );
            this.documentRepository.deleteById(toDelete.getId());
        }
        else {
            throw new ApiException("Can't delete someone else's files",HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public byte[] getFile(Long id,Long userid) {
        Document toReturn =this.documentRepository.findById(id).orElseThrow(()->{
            throw new ApiException("document not Found",HttpStatus.NOT_FOUND.value());
        });
        if(!toReturn.getUploadedBy().getId().equals(userid)){
            if(toReturn.getUploadedTo().getMembers().stream().noneMatch(x-> x.getId().equals(userid))){
                throw new ApiException("You don't have access to this document",HttpStatus.UNAUTHORIZED.value());
            }
        }
        return toReturn.getFile();
    }

}
