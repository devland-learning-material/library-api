package excercise.library.library.borrowingRecord;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BorrowingRecordPenaltyException  extends RuntimeException{
  public BorrowingRecordPenaltyException(String message){
    super(message);
  }
}
