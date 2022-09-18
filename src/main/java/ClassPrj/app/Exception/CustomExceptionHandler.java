package ClassPrj.app.Exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ClassPrj.app.Model.Request.NewClassRoomRequest;

@ControllerAdvice

public class CustomExceptionHandler {

	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Object> usernameAlreadyExists(ApiException e){
		ApiExceptionReturn ret=new ApiExceptionReturn(
				e.getMessage(),
				e,
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		return new ResponseEntity<>(ret,HttpStatus.BAD_REQUEST);
	}
}
