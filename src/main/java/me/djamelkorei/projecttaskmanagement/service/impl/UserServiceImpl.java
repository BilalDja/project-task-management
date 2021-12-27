package me.djamelkorei.projecttaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.config.ApplicationProperties;
import me.djamelkorei.projecttaskmanagement.domain.User;
import me.djamelkorei.projecttaskmanagement.repository.UserRepository;
import me.djamelkorei.projecttaskmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link User}.
 *
 * @author Djamel Eddine Korei
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final ApplicationProperties applicationProperties;

  /**
   * Get all the users.
   *
   * @param dataTablesInput .
   * @return dataTablesOutput the list of entities.
   */
  @Override
  @Transactional(readOnly = true)
  public DataTablesOutput<User> findAll(DataTablesInput dataTablesInput) {
    log.debug("Request to get all Users");
    return userRepository.findAll(dataTablesInput);
  }

  /**
   * Get all the users.
   *
   * @return  the list of entities.
   */
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return userRepository.findAllByActiveIsTrue();
  }

  /**
   * Get one user by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(Long id) {
    log.debug("Request to get User : {}", id);
    return userRepository.findById(id);
  }

  /**
   * Get one user by id.
   *
   * @param email the email of the entity.
   * @return the entity.
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<User> findOneByEmail(String email) {
    log.debug("Request to get User : {}", email);
    return userRepository.findOneByEmail(email);
  }

  /**
   * Get user's name by id.
   *
   * @param id the id of the entity.
   * @return the name.
   */
  @Override
  @Transactional(readOnly = true)
  public String findName(Long id) {
    log.debug("Request to get User's username : {}", id);
    return userRepository.findName(id);
  }

  /**
   * Save a user.
   *
   * @param user the entity to save.
   * @return the persisted entity.
   */
  @Override
  @Transactional
  public User save(User user) {
    log.debug("Request to save User : {}", user);
    return userRepository.save(user);
  }

  /**
   * Password reset.
   *
   * @param newPassword the new password to save.
   * @param key         the key.
   * @return the persisted entity.
   */
  @Override
  @Transactional
  public Optional<User> completePasswordReset(String newPassword, String key) {
    log.debug("Request to reset Password : {} {}", newPassword, key);
    return userRepository.findOneByResetKey(key)
      .filter(u -> u.getResetDate().isAfter(Instant.now().minusSeconds(applicationProperties.getMail().getResetKeyValiditySecond())))
      .map(u -> {
        u.setPassword(passwordEncoder.encode(newPassword));
        u.setResetKey(null);
        u.setResetDate(null);
        return u;
      })
      .map(userRepository::save);
  }

  /**
   * Delete a user.
   *
   * @param user the entity to delete.
   */
  @Override
  @Transactional
  public void delete(User user) {
    log.debug("Request to delete User : {}", user);
    userRepository.deleteById(user.getUserId());
  }

}
