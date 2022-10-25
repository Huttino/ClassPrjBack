package classprj.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table()
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    @Min(value = 0)
    @Max(value = 10)
    private Long grade;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classRoom_id",nullable = false)
    private ClassRoom classRoom;

    public Member(Long id, Student student, ClassRoom classRoom) {
        this.id = id;
        this.student = student;
        this.classRoom = classRoom;
    }

    public Member(Long id, Long grade, Student student, ClassRoom classRoom) {
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

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
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
