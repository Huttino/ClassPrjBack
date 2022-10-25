package classprj.app.webSocket;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SentMessage {
    private String message;
    private String name;
    private String surname;
    @NotNull
    @NotEmpty
    private String classId;
    private String username;

    public SentMessage(String message, String name, String surname, String username,String classId) {
        this.message = message;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.classId=classId;
    }

    public SentMessage() {
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "SentMessage{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
