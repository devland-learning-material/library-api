package excercise.library.library.applicationuser;

import excercise.library.library.common.base.model.AuditModel;
import jakarta.persistence.Entity;
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

  private String username;

  private String password;
}
