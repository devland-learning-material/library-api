package excercise.library.library.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import excercise.library.library.borrowingRecord.exception.BorrowingRecordBookNotMatchException;
import excercise.library.library.borrowingRecord.exception.BorrowingRecordIsAlreadyReturnedException;
import excercise.library.library.borrowingRecord.exception.BorrowingRecordNotFoundException;
import excercise.library.library.borrowingRecord.exception.BorrowingRecordPenaltyException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.status(status).body(errors);
  }

  // @ExceptionHandler({ BorrowingRecordBookNotMatchException.class, BorrowingRecordIsAlreadyReturnedException.class, BorrowingRecordNotFoundException.class, BorrowingRecordPenaltyException.class})
  // protected ResponseEntity<Object> handleMedhodResponseError(RuntimeException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // Map<String, String> errors = new HashMap<>();
  // exception.getBindingResult().getAllErrors().forEach(error -> {
  //   String fieldName = ((FieldError) error).getField();
  //   String errorMessage = error.getDefaultMessage();
  //   errors.put(fieldName, errorMessage);
  // });

  // exception.

  // return ResponseEntity.status(status).body(errors);
  // }

}
