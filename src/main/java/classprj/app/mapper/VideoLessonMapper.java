package classprj.app.mapper;

import classprj.app.domain.VideoLesson;
import classprj.app.model.dto.DocumentDTO;
import classprj.app.model.dto.VideoLessonDTO;

import java.util.ArrayList;

public class VideoLessonMapper {

    public static VideoLessonDTO entityToDTO(VideoLesson videoLesson){
        VideoLessonDTO toReturn=new VideoLessonDTO();
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
}
