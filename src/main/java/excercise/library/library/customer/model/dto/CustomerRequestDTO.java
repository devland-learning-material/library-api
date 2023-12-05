package excercise.library.library.customer.model.dto;

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

  // add username password for application user data

  public Customer convertToEntity() {
    return Customer.builder().id(this.id).name(this.name).build();
  }
}
