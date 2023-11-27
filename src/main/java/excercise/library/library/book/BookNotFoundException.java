package excercise.library.library.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException() {
    super("Book not found!");
  }
}
