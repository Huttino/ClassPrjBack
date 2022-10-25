package classprj.app.service;

import classprj.app.model.dto.VideoLessonDTO;
import classprj.app.model.request.newVideoLessonRequest;

public interface VideoLessonService {
    VideoLessonDTO addLesson(newVideoLessonRequest request, Long classId, Long myId);

    void remove(Long myId, Long classId, Long lessonId);
}
