package excercise.library.library.customer.model;

import excercise.library.library.applicationuser.ApplicationUser;
import excercise.library.library.borrowingRecord.model.BorrowingRecord;
import excercise.library.library.customer.model.dto.CustomerResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(cascade = CascadeType.PERSIST)
  BorrowingRecord borrowingRecord;

  // relation one to one (application user. cascade type = persist)
  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "application_user_id", referencedColumnName = "id")
  private ApplicationUser applicationUser;

  public CustomerResponseDTO convertToResponse() {
    return CustomerResponseDTO.builder().id(this.id).name(this.name).build();
  }

  public CustomerResponseDTO cunvertToResponsePublic() {
    return CustomerResponseDTO.builder().id(this.id).name(this.name).build();
  }
}
