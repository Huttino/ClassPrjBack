package classprj.app.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VideoLessonDTO {
    private String youTubeUrl;
    private String title;
    private String description;
    private List<DocumentDTO> relatedDocuments;
    private LocalDateTime dateOfUpload;

    public VideoLessonDTO() {
    }

    public VideoLessonDTO(String youTubeUrl, String title, String description, List<DocumentDTO> relatedDocuments, LocalDateTime dateOfUpload) {
        this.youTubeUrl = youTubeUrl;
        this.title = title;
        this.description = description;
        this.relatedDocuments = relatedDocuments;
        this.dateOfUpload = dateOfUpload;
    }

    public VideoLessonDTO(String youTubeUrl, String title, String description, List<DocumentDTO> relatedDocuments) {
        this.youTubeUrl = youTubeUrl;
        this.title = title;
        this.description = description;
        this.relatedDocuments = relatedDocuments;
    }

    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentDTO> getRelatedDocuments() {
        return relatedDocuments;
    }

    public void setRelatedDocuments(List<DocumentDTO> relatedDocuments) {
        this.relatedDocuments = relatedDocuments;
    }

    public LocalDateTime getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(LocalDateTime dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }
}
