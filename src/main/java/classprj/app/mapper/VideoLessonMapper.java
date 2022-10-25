package classprj.app.mapper;

import classprj.app.domain.ClassRoom;
import classprj.app.domain.Document;
import classprj.app.domain.VideoLesson;
import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.dto.VideoLessonDTO;
import classprj.app.model.request.newVideoLessonRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VideoLessonMapper {

    public static VideoLessonDTO entityToDTO(VideoLesson videoLesson){
        VideoLessonDTO toReturn=new VideoLessonDTO();
        toReturn.setId(videoLesson.getId());
        toReturn.setDescription(videoLesson.getDescription());
        toReturn.setTitle(videoLesson.getTitle());
        toReturn.setYouTubeUrl(videoLesson.getYouTubeLink());
        ArrayList<DocumentDTO> toSet=new ArrayList<>();
        toReturn.setRelatedDocuments(toSet);
        videoLesson.getRelatedDocuments().forEach(
                x-> toSet.add(DocumentMapper.entityToDTO(x))

        );
        toReturn.setDateOfUpload(videoLesson.getDateOfUpload());
        return toReturn;
    }
    public static VideoLesson newRequestToEntity(newVideoLessonRequest newVideoLessonRequest, List<Document> documents, ClassRoom classRoom){
        VideoLesson toReturn = new VideoLesson();
        if(newVideoLessonRequest.getYouTubeUrl().contains("=")){
            toReturn.setYouTubeLink(newVideoLessonRequest.getYouTubeUrl().split("=")[1]);
        }
        else{
            toReturn.setYouTubeLink(newVideoLessonRequest.getYouTubeUrl());
        }
        toReturn.setClassRoom(classRoom);
        toReturn.setRelatedDocuments(documents);
        toReturn.setTitle(newVideoLessonRequest.getTitle());
        toReturn.setDescription(newVideoLessonRequest.getDescription());
        toReturn.setDateOfUpload(LocalDateTime.now());
        return toReturn;
    }
}
