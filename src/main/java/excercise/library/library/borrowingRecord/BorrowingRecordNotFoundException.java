package excercise.library.library.borrowingRecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class BorrowingRecordNotFoundException extends RuntimeException {
  public BorrowingRecordNotFoundException() {
    super("Borrowing Record Not Found!");
  }
}
