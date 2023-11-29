package excercise.library.library.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import excercise.library.library.customer.model.Customer;
import excercise.library.library.customer.model.dto.CustomerRequestDTO;
import excercise.library.library.customer.model.dto.CustomerResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("/customers/{id}")
  public ResponseEntity<CustomerResponseDTO> getOneById(@PathVariable Long id) {
    Customer customer = this.customerService.getOneById(id);
    return ResponseEntity.ok(customer.convertToResponse());
  }

  @PostMapping("/customers")
  public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
    Customer newCustomer = customerRequestDTO.convertToEntity();
    Customer savedCustomer = this.customerService.create(newCustomer);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer.convertToResponse());
  }
}
