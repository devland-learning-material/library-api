package excercise.library.library.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import excercise.library.library.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
