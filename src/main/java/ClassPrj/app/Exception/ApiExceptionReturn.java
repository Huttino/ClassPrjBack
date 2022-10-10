package ClassPrj.app.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionReturn {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	
	public ApiExceptionReturn(ApiException e, ZonedDateTime timestamp) {
		super();
		this.message = e.getMessage();
		this.throwable = e;
		if(e.status!=0)
			this.httpStatus = HttpStatus.resolve(e.status);
		else
			this.httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
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
