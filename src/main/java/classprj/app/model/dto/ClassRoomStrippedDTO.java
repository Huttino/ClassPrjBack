package classprj.app.model.dto;

public class ClassRoomStrippedDTO {
    private Long id;
    private String className;
    private String creator;
    private Integer numberOfDocuments;
    private Integer numberOfMembers;
    private Integer numberOfLessons;
    private boolean graded;

    public ClassRoomStrippedDTO() {
    }

    public ClassRoomStrippedDTO(Long id, String className, String creator, Integer numberOfDocuments, Integer numberOfMembers, Integer numberOfLessons, boolean graded) {
        this.id = id;
        this.className = className;
        this.creator = creator;
        this.numberOfDocuments = numberOfDocuments;
        this.numberOfMembers = numberOfMembers;
        this.numberOfLessons = numberOfLessons;
        this.graded = graded;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getNumberOfDocuments() {
        return numberOfDocuments;
    }

    public void setNumberOfDocuments(Integer numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Integer getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(Integer numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }
}
