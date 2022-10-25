package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StudentClassRequest {
    @NotNull
    @NotEmpty
    private Long studentId;
    @NotNull
    @NotEmpty
    private Long classRoomId;

    public StudentClassRequest(Long studentId, Long classRoomId) {
        this.studentId = studentId;
        this.classRoomId = classRoomId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Long classRoomId) {
        this.classRoomId = classRoomId;
    }
}
