package ClassPrj.app.Model.Dto;

public class StudentInClass {
    public Long id;
    public String username;
    public Long grade;

    public StudentInClass(Long id, String username,Long grade) {
        this.id = id;
        this.username = username;
        this.grade=grade;
    }

    public StudentInClass(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
