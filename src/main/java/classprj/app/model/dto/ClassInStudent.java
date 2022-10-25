package classprj.app.model.dto;

import java.util.List;

public class ClassInStudent {
    private Long id;
    private String className;

    private List<VideoLessonDTO> videoLessons;

    public ClassInStudent(Long id,String className) {
        this.id = id;
        this.className = className;
    }

    public ClassInStudent(Long id, String className, List<VideoLessonDTO> videoLessons) {
        this.id = id;
        this.className = className;
        this.videoLessons = videoLessons;
    }

    public ClassInStudent() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<VideoLessonDTO> getVideoLessons() {
        return videoLessons;
    }

    public void setVideoLessons(List<VideoLessonDTO> videoLessons) {
        this.videoLessons = videoLessons;
    }
}
