package classprj.app.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class VideoLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @Column
    private String youTubeLink;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime dateOfUpload;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="lessonId"),inverseJoinColumns = @JoinColumn(name="documentId"))
    private List<Document> relatedDocuments;

    @ManyToOne
    @JoinColumn(name="classRoomId",nullable = false,updatable = false)
    private ClassRoom classRoom;

    public VideoLesson() {
    }

    public VideoLesson(Long id, String youTubeLink, String title, String description, LocalDateTime dateOfUpload, List<Document> relatedDocuments, ClassRoom classRoom) {
        Id = id;
        this.youTubeLink = youTubeLink;
        this.title = title;
        this.description = description;
        this.dateOfUpload = dateOfUpload;
        this.relatedDocuments = relatedDocuments;
        this.classRoom = classRoom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
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

    public List<Document> getRelatedDocuments() {
        return relatedDocuments;
    }

    public void setRelatedDocuments(List<Document> relatedDocuments) {
        this.relatedDocuments = relatedDocuments;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public LocalDateTime getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(LocalDateTime dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }
}
