package ClassPrj.app.Model.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddStudentRequest {
    @NotNull
    @NotEmpty
    private String studentUser;
    @NotNull
    @NotEmpty
    private Long classRoomId;

    public AddStudentRequest(String studentUser, Long classRoomId) {
        this.studentUser = studentUser;
        this.classRoomId = classRoomId;
    }

    public String getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(String studentUser) {
        this.studentUser = studentUser;
    }

    public Long getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Long classRoomId) {
        this.classRoomId = classRoomId;
    }
}
