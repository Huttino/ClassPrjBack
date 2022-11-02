package classprj.app.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "scopes")
    Set<ClassRoom> classRooms;

    public Scope(Long id, String title, String description, Set<ClassRoom> classRooms) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.classRooms = classRooms;
    }


    public Scope() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(Set<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }
}
