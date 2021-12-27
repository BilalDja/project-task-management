package me.djamelkorei.projecttaskmanagement.service;

import me.djamelkorei.projecttaskmanagement.domain.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link User}.
 *
 * @author Djamel Eddine Korei
 */
public interface UserService {

  /**
   * Get all the users.
   *
   * @param dataTablesInput .
   * @return dataTablesOutput the list of entities.
   */
  @Transactional(readOnly = true)
  DataTablesOutput<User> findAll(DataTablesInput dataTablesInput);

  /**
   * Get all the users.
   *
   * @return  the list of entities.
   */
  @Transactional(readOnly = true)
  List<User> findAll();

  /**
   * Get one user by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  Optional<User> findById(Long id);

  /**
   * Get one user by id.
   *
   * @param email the email of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  Optional<User> findOneByEmail(String email);

  /**
   * Get user's name by id.
   *
   * @param id the id of the entity.
   * @return the name.
   */
  @Transactional(readOnly = true)
  String findName(Long id);

  /**
   * Save a user.
   *
   * @param user the entity to save.
   * @return the persisted entity.
   */
  @Transactional
  User save(User user);

  /**
   * Password reset.
   *
   * @param newPassword the new password to save.
   * @param key         the key.
   * @return the persisted entity.
   */
  @Transactional
  Optional<User> completePasswordReset(String newPassword, String key);

  /**
   * Delete a user.
   *
   * @param user the entity to delete.
   */
  @Transactional
  void delete(User user);
}
