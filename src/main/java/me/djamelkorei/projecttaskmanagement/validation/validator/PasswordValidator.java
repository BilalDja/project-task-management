package me.djamelkorei.projecttaskmanagement.validation.validator;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.service.UserService;
import me.djamelkorei.projecttaskmanagement.util.SecurityUtils;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Password matcher custom validation
 *
 * @author Djamel Eddine Korei
 */
@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String> {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  @Override
  public void initialize(Password constraintAnnotation) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
    return userService
      .findById(SecurityUtils.getCurrentUserId().orElse(0L))
      .filter(u -> passwordEncoder.matches(password, u.getPassword()))
      .isPresent();
  }

}
