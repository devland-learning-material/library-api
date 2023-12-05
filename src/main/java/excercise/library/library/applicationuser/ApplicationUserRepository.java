package excercise.library.library.applicationuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
  Optional<ApplicationUser> findByUsername(String username);
}
