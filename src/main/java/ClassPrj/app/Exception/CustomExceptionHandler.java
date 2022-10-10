package ClassPrj.app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice

public class CustomExceptionHandler {

	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Object> usernameAlreadyExists(ApiException e){
		ApiExceptionReturn ret=new ApiExceptionReturn(
				e.getMessage(),
				e,
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.systemDefault())
				);
		return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
	}
}
