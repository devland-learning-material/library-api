package excercise.library.library.customer;

import org.springframework.stereotype.Service;

import excercise.library.library.customer.model.Customer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  public Customer create(Customer newCustomer) {
    return this.customerRepository.save(newCustomer);
  }

  public Customer getOneById(Long id) {
    return this.customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
  }

}
