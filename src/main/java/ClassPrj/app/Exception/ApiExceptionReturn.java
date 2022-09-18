package ClassPrj.app.Exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptionReturn {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	
	public ApiExceptionReturn(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	
}