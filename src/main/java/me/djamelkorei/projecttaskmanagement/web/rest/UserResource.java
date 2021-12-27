package me.djamelkorei.projecttaskmanagement.web.rest;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.User;
import me.djamelkorei.projecttaskmanagement.mail.MailService;
import me.djamelkorei.projecttaskmanagement.repository.UserRepository;
import me.djamelkorei.projecttaskmanagement.service.UserService;
import me.djamelkorei.projecttaskmanagement.service.dto.UserDTO;
import me.djamelkorei.projecttaskmanagement.service.dto.UserShortDTO;
import me.djamelkorei.projecttaskmanagement.service.mapper.UserMapper;
import me.djamelkorei.projecttaskmanagement.util.RandomUtils;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link User}.
 *
 * @author Djamel Eddine Korei
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

  private final Logger log = LoggerFactory.getLogger(UserResource.class);

  private final UserService userService;

  private final MailService mailService;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  /**
   * {@code GET  /usersDataTable} : get all the users datatable.
   *
   * @param dataTablesInput .
   * @return the {@link DataTablesOutput} with status {@code 200 (OK)} and the list of users in body.
   */
  @GetMapping("/usersDataTable")
  @PreAuthorize("hasRole('ADMIN')")
  public DataTablesOutput<UserDTO> findAllUsersDatatable(DataTablesInput dataTablesInput) {
    log.debug("REST request to get a datatable of Users");
    return userMapper.maptToDataTableUserDTO(userService.findAll(dataTablesInput));
  }

  /**
   * {@code GET  /users} : get all the users.
   *
   * @return the {@link DataTablesOutput} with status {@code 200 (OK)} and the list of users in body.
   */
  @GetMapping("/usersShort")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserShortDTO> findAllUsers() {
    log.debug("REST request to get all Users");
    return userService.findAll().stream().map(userMapper::mapToUserShortDTO).toList();
  }

  /**
   * {@code POST  /users} : Create a new user.
   *
   * @param userDTO the userDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDTO,
   * or with status {@code 400 (Bad Request)} if the userDTO is not valid.
   */
  @PostMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
    log.debug("REST request to create User : {}", userDTO);

    String password = RandomUtils.generatePassword();

    User user = userMapper.mapToUser(userDTO, new User());
    user.setPassword(passwordEncoder.encode(password));
    User savedUser = userService.save(user);

    savedUser.setPassword(password);
    mailService.sendCreationEmail(savedUser);

    return ResponseEntity.ok(userMapper.mapToUserDTO(savedUser));
  }

  /**
   * {@code PUT  /users:id} : Updates an existing user.
   *
   * @param userDTO the userDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDTO,
   * or with status {@code 400 (Bad Request)} if the userDTO is not valid,
   * or with status {@code 404 (Not Found)}.
   * or with status {@code 500 (Internal Server Error)} if the userDTO couldn't be updated.
   */
  @PutMapping("/users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
    log.debug("REST request to update User : {}", userDTO);
    return userService.findById(id)
      .map(u -> userMapper.mapToUser(userDTO, u))
      .map(userService::save)
      .map(userMapper::mapToUserDTO)
      .map(ResponseEntity::ok)
      .orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * {@code DELETE  /users/:id} : delete the "id" user.
   *
   * @param id the id of the user to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)},
   * or with status {@code 404 (Not Found)}.
   */
  @DeleteMapping("/users/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
    log.debug("REST request to delete User : {}", id);
    User user = userService.findById(id)
      .orElseThrow(ResourceNotFoundException::new);
    userService.delete(user);
    return ResponseEntity.noContent().build();
  }

}
