package ClassPrj.app.Exception;

public class ApiException extends RuntimeException {
	int status;
	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
	public ApiException(String message){
		super(message);
	}
	public ApiException(String message,int status) {
		super(message);
		this.status=status;
	}

	
}
