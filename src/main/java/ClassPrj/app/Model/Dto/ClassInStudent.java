package ClassPrj.app.Model.Dto;

public class ClassInStudent {
    public Long id;
    public String className;

    public ClassInStudent(Long id,String className) {
        this.id = id;
        className = className;
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
        className = className;
    }
}
