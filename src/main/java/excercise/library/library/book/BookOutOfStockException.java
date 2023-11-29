package excercise.library.library.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BookOutOfStockException extends RuntimeException {
  public BookOutOfStockException() {
    super("Opps.. Book out of stock!");
  }
}
