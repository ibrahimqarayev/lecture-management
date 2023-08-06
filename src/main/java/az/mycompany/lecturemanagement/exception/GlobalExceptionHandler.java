package az.mycompany.lecturemanagement.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        ErrorResponse errorResponse = new ErrorResponse();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            String message = "Field name: " + fieldName + " , " + "Error message:" + errorMessage;

            errorResponse.setStatus("error");
            errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage(message);
            errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {

            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            String message = "Field name: " + fieldName + " , " + "Error message:" + errorMessage;

            errorResponse.setStatus("error");
            errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage(message);
            errorResponse.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidIdentityNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidIdentityNumberException(InvalidIdentityNumberException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "error",
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
