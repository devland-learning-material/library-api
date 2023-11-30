package excercise.library.library.borrowingRecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "Book returned doesn't match with book borrowed")
public class BorrowingRecordBookNotMatchException extends RuntimeException {

}
