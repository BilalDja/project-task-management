package me.djamelkorei.projecttaskmanagement.web.rest;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.Role;
import me.djamelkorei.projecttaskmanagement.domain.User;
import me.djamelkorei.projecttaskmanagement.mail.MailService;
import me.djamelkorei.projecttaskmanagement.repository.RoleRepository;
import me.djamelkorei.projecttaskmanagement.security.jwt.JWTFilter;
import me.djamelkorei.projecttaskmanagement.security.jwt.JWToken;
import me.djamelkorei.projecttaskmanagement.security.jwt.TokenProvider;
import me.djamelkorei.projecttaskmanagement.service.UserService;
import me.djamelkorei.projecttaskmanagement.service.dto.UserDTO;
import me.djamelkorei.projecttaskmanagement.service.mapper.UserMapper;
import me.djamelkorei.projecttaskmanagement.util.RandomUtils;
import me.djamelkorei.projecttaskmanagement.util.SecurityUtils;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.CurrentUserLoginNotFoundException;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.EmailNotExistException;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.ResetKeyNotExistException;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.ResourceNotFoundException;
import me.djamelkorei.projecttaskmanagement.web.rest.vm.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

/**
 * REST controller for managing {@link User}.
 *
 * @author Djamel Eddine Korei
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthResource {

  private final UserService userService;

  private final RoleRepository roleRepository;

  private final MailService mailService;

  private final UserMapper userMapper;

  private final TokenProvider tokenProvider;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;


  /**
   * {@code POST  /register} : register the user.
   *
   * @param managedUserVM the managed user View Model.
   */
  @PostMapping("/auth/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Object> registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
    Role role = roleRepository.getById(2L);
    User user = new User();
    user.setFirstName(managedUserVM.getFirstName());
    user.setLastName(managedUserVM.getLastName());
    user.setUsername(managedUserVM.getUsername());
    user.setEmail(managedUserVM.getEmail());
    user.setPassword(passwordEncoder.encode(managedUserVM.getPassword()));
    user.setResetDate(null);
    user.setResetKey(null);
    user.setActive(true);
    user.setRole(role);
    userService.save(user);
    return ResponseEntity.ok().build();
  }

  /**
   * {@code GET  /auth/login} : login in the current user.
   *
   * @param loginVM credentials
   * @return access token
   */
  @PostMapping(path = "/auth/login")
  public ResponseEntity<JWToken> authorize(@Valid @RequestBody LoginVM loginVM) {
    UsernamePasswordAuthenticationToken authenticationToken =
      new UsernamePasswordAuthenticationToken(loginVM.getLogin(), loginVM.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    boolean rememberMe = loginVM.getRememberMe() != null && loginVM.getRememberMe();
    String jwt = tokenProvider.createToken(authentication, rememberMe);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new JWToken(jwt), httpHeaders, HttpStatus.OK);
  }

  /**
   * {@code GET  /auth/me} : get the current user.
   *
   * @return the current user.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @GetMapping(path = "/auth/me")
  public UserDTO getAccount() {
    return userService.findById(SecurityUtils.getCurrentUserId().orElseThrow(CurrentUserLoginNotFoundException::new))
      .map(userMapper::mapToUserDTO)
      .orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * {@code POST  /auth/me} : update the current user information.
   *
   * @param userVM the current user information.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user login wasn't found.
   */
  @PostMapping(path = "/auth/me")
  public ResponseEntity<Object> updateAccount(@Valid @RequestBody UserAuthVM userVM) {
    userService.findById(SecurityUtils.getCurrentUserId().orElseThrow(CurrentUserLoginNotFoundException::new))
      .map(u -> {
        u.setFirstName(userVM.getFirstName());
        u.setLastName(userVM.getLastName());
        u.setUsername(userVM.getUsername());
        u.setEmail(userVM.getEmail());
        u.setPhotoName(userVM.getPhotoName());
        return u;
      }).map(userService::save);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  /**
   * {@code POST  /auth/change-password} : changes the current user's password.
   *
   * @param passwordResetVM password reset vm.
   */
  @PostMapping(path = "/auth/change-password")
  public ResponseEntity<Object> changePassword(@Valid @RequestBody PasswordResetVM passwordResetVM) {
    User user = userService
      .findById(SecurityUtils.getCurrentUserId().orElse(0L))
      .orElseThrow(CurrentUserLoginNotFoundException::new);

    user.setPassword(passwordEncoder.encode(passwordResetVM.getNewPassword()));
    userService.save(user);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  /**
   * {@code POST   /auth/reset-password/init} : Send an email to reset the password of the user.
   *
   * @param emailVM the email of the user.
   * @throws EmailNotExistException {@code 400 (Bad Request)} if the email address is not registered.
   */
  @PostMapping(path = "/auth/reset-password/init")
  public void requestPasswordReset(@Valid @RequestBody EmailVM emailVM) {
    User user = userService.findOneByEmail(emailVM.getEmail())
      .orElseThrow(EmailNotExistException::new);

    user.setResetKey(RandomUtils.generateResetKey());
    user.setResetDate(Instant.now());
    User savedUser = userService.save(user);

    mailService.sendPasswordResetMail(savedUser);
  }

  /**
   * {@code POST   /auth/reset-password/finish} : Finish to reset the password of the user.
   *
   * @param keyPasswordVM the generated key and the new password.
   * @throws ResetKeyNotExistException {@code 400 (Bad Request)} if the Reset Key is not registered.
   * @throws RuntimeException          {@code 500 (Internal Server Error)} if the password could not be reset.
   */
  @PostMapping(path = "/auth/reset-password/finish")
  public void finishPasswordReset(@Valid @RequestBody KeyPasswordVM keyPasswordVM) {
    userService.completePasswordReset(keyPasswordVM.getPassword(), keyPasswordVM.getKey())
      .orElseThrow(ResetKeyNotExistException::new);
  }

}
