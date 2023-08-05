package az.mycompany.lecturemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidIdentityNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidIdentityNumberException(InvalidIdentityNumberException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getDescription(false),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getDescription(false),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
    }


}
