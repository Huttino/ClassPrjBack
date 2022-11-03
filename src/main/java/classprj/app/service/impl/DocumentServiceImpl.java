package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Document;
import classprj.app.domain.Teacher;
import classprj.app.exception.ApiException;
import classprj.app.mapper.DocumentMapper;
import classprj.app.mapper.FileSaver;
import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.request.UploadDocumentWithData;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.DocumentRepository;
import classprj.app.repository.TeacherRepository;
import classprj.app.repository.VideoLessonRepository;
import classprj.app.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        List<Document> toSave=new ArrayList<>();
        Optional<Teacher> uploader=this.teacherRepository.findByUsername(username);
        if(uploader.isEmpty())throw new ApiException("Teacher not found", HttpStatus.NOT_FOUND.value());
        Optional<ClassRoom> uploadedTo=this.classRoomRepository.findById(classId);
        if(uploadedTo.isEmpty()) throw new ApiException("ClassRoom not found",HttpStatus.NOT_FOUND.value());
        toUpload.getFile().forEach(
                x->{
                    try {
                        toSave.add(DocumentMapper.RequestToEntity(
                                x,
                                FileSaver.saveFile(uploadedTo.get().getId(),true,x),
                                toUpload.getNotes(),
                                uploadedTo.get(),
                                uploader.get()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        List<DocumentDTO> savedDocuments=new ArrayList<DocumentDTO>();
        this.documentRepository.saveAll(toSave).forEach(
                x->{
                    savedDocuments.add(DocumentMapper.entityToDTO(x));
                }
            );
        return savedDocuments;
    }

    @Override
    public void delete(Long documentId,String username) {
        Optional<Document> toBeDeleted= this.documentRepository.findById(documentId);
        if (toBeDeleted.isEmpty())throw new ApiException("Document not found",HttpStatus.NOT_FOUND.value());
        Document toDelete=toBeDeleted.get();
        File toCanc=new File(toDelete.getPathFile());
        toCanc.delete();
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
    public byte[] getFile(Long id,Long userid){
        Document from =this.documentRepository.findById(id).orElseThrow(()->{
            throw new ApiException("document not Found",HttpStatus.NOT_FOUND.value());
        });
        if(!from.getUploadedBy().getId().equals(userid)){
            if(from.getUploadedTo().getMembers().stream().noneMatch(x-> x.getId().equals(userid))){
                throw new ApiException("You don't have access to this document",HttpStatus.UNAUTHORIZED.value());
            }
        }

        try {
            return Files.readAllBytes(Paths.get(from.getPathFile()));
        } catch (IOException e) {
            throw new ApiException("couldn't retrieve the File",500);
        }
    }

}
