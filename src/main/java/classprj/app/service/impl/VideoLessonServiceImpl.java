package classprj.app.service.impl;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Document;
import classprj.app.domain.VideoLesson;
import classprj.app.exception.ApiException;
import classprj.app.mapper.VideoLessonMapper;
import classprj.app.model.dto.VideoLessonDTO;
import classprj.app.model.request.newVideoLessonRequest;
import classprj.app.repository.ClassRoomRepository;
import classprj.app.repository.DocumentRepository;
import classprj.app.repository.VideoLessonRepository;
import classprj.app.service.VideoLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VideoLessonServiceImpl implements VideoLessonService {
    private final VideoLessonRepository videoLessonRepository;
    private final ClassRoomRepository classRoomRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public VideoLessonServiceImpl(
            VideoLessonRepository videoLessonRepository,
            ClassRoomRepository classRoomRepository,
            DocumentRepository documentRepository
    ) {
        this.videoLessonRepository=videoLessonRepository;
        this.classRoomRepository=classRoomRepository;
        this.documentRepository=documentRepository;
    }



    @Override
    public VideoLessonDTO addLesson(newVideoLessonRequest request, Long classId, Long myId) {
        Optional<ClassRoom> whereOptional=classRoomRepository.findById(classId);
        if(whereOptional.isEmpty()) throw new ApiException("ClassRoom not found", HttpStatus.NOT_FOUND.value());
        ClassRoom where=whereOptional.get();
        if(!where.getCreator().getId().equals(myId)) throw new ApiException("you didn't create this classRoom",HttpStatus.UNAUTHORIZED.value());
        ArrayList<Document> documents=new ArrayList<>();
        request.getDocumentsAttached().forEach(
                x-> this.documentRepository.findById(x).ifPresent( y-> documents.add(y))
        );
        VideoLesson saved=videoLessonRepository.save(VideoLessonMapper.newRequestToEntity(request,documents,where));
        return VideoLessonMapper.entityToDTO(saved);
    }
}
