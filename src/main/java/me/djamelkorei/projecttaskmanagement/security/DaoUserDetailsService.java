package me.djamelkorei.projecttaskmanagement.security;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service for managing user authentication.
 *
 * @author Djamel Eddine Korei
 */
@Service
@RequiredArgsConstructor
public class DaoUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepository.findOneByUsernameOrEmail(login, login)
      .orElseThrow(() -> {
        throw new UsernameNotFoundException(login);
      });
  }
}
