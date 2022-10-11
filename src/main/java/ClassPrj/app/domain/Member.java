package ClassPrj.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table()
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    @Min(value = 0)
    @Max(value = 10)
    private Integer grade;

    @ManyToOne
    @NotNull
    @NotEmpty
    private Student student;

    @ManyToOne
    @NotNull
    @NotEmpty
    private ClassRoom classRoom;

    public Member(Long id, Student student, ClassRoom classRoom) {
        this.id = id;
        this.student = student;
        this.classRoom = classRoom;
    }

    public Member(Long id, Integer grade, Student student, ClassRoom classRoom) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.classRoom = classRoom;
    }

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
