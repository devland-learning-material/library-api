package excercise.library.library.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AuthorNotFoundException extends RuntimeException {
  public AuthorNotFoundException(){
    super("Author not found!");
  }
}
