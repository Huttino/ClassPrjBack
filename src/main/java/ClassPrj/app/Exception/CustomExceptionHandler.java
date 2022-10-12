package ClassPrj.app.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice

public class CustomExceptionHandler {

	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Object> HandleException(ApiException e){
		ApiExceptionReturn ret=new ApiExceptionReturn(
				e,
				ZonedDateTime.now(ZoneId.systemDefault())
				);
		return ResponseEntity.status(ret.getHttpStatus()).body(ret);
	}
}
