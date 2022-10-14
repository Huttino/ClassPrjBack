package ClassPrj.app.WebSocket;

public class GreetingMessage {
    private String message;

    public GreetingMessage(String message) {
        this.message = message;
    }

    public GreetingMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
