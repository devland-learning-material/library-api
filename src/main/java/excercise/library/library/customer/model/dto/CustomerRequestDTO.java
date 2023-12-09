package excercise.library.library.customer.model.dto;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import excercise.library.library.applicationuser.ApplicationUser;
import excercise.library.library.customer.model.Customer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
  private Long id;

  @NotBlank(message = "name is requried")
  private String name;

  @NotBlank(message = "email is required")
  private String email;

  @NotBlank(message = "username is required")
  private String username;

  @NotBlank(message = "password is required")
  private String password;

  // add username password for application user data

  public Customer convertToEntity() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encriptedPassword = passwordEncoder.encode(password);

    ApplicationUser applicationUser = ApplicationUser.builder().name(this.name).email(this.email)
        .username(this.username).password(encriptedPassword).build();

    Customer customer = Customer.builder().name(this.name).applicationUser(applicationUser).build();
    applicationUser.setCustomer(customer);
    
    return customer;
  }
}
