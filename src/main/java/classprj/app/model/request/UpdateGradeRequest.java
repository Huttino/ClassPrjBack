package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateGradeRequest {

    @NotEmpty
    @NotNull
    private Long studentId;

    @NotEmpty
    @NotNull
    private Long Grade;

    public UpdateGradeRequest(Long studentId, Long grade) {
        this.studentId = studentId;
        Grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getGrade() {
        return Grade;
    }

    public void setGrade(Long grade) {
        Grade = grade;
    }
}
