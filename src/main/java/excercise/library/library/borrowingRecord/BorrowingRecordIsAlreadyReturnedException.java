package excercise.library.library.borrowingRecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BorrowingRecordIsAlreadyReturnedException extends RuntimeException {
  public BorrowingRecordIsAlreadyReturnedException() {
    super("Borrowing Record Is Already Returned");
  }
}
