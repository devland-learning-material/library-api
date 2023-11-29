package excercise.library.library.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException() {
    super("Customer Not Found!");
  }
}
