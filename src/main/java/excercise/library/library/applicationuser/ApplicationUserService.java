package excercise.library.library.applicationuser;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import excercise.library.library.authentication.model.UserPrincipal;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User Not Found with -> username or email :" + username));

    return UserPrincipal.build(applicationUser);
  }

  
}
