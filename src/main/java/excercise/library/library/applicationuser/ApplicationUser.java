package excercise.library.library.applicationuser;

import excercise.library.library.common.base.model.AuditModel;
import excercise.library.library.customer.model.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser extends AuditModel {
  private String name;

  private String email;

  @Column(unique = true)
  private String username;

  private String password;

  // relation to customer
  @OneToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;
}
