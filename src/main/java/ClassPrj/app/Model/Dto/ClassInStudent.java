package ClassPrj.app.Model.Dto;

public class ClassInStudent {
    public Long id;
    public String ClassName;

    public ClassInStudent(Long id,String className) {
        this.id = id;
        ClassName = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }
}
