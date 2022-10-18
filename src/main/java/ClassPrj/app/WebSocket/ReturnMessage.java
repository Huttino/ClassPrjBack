package ClassPrj.app.WebSocket;

public class ReturnMessage {
    private String FirstName;
    private String LastName;
    private  String Message;

    public ReturnMessage(String firstName, String lastName, String message) {
        FirstName = firstName;
        LastName = lastName;
        Message = message;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
