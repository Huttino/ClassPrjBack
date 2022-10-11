package ClassPrj.app.Model.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateGradeRequest {

    @NotEmpty
    @NotNull
    private Long studentId;

    @NotEmpty
    @NotNull
    private Integer Grade;

    public UpdateGradeRequest(Long studentId, Integer grade) {
        this.studentId = studentId;
        Grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }
}
