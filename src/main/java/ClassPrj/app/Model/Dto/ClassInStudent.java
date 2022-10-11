package ClassPrj.app.Model.Dto;

public class ClassInStudent {
    public Long id;
    public String className;
    public Integer grade;

    public ClassInStudent(Long id,String className,Integer grade) {
        this.id = id;
        this.className = className;
        this.grade=grade;
    }

    public ClassInStudent(Long id, String className) {
        this.id = id;
        this.className = className;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
