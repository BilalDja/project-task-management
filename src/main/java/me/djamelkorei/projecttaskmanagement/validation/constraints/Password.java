package me.djamelkorei.projecttaskmanagement.validation.constraints;

import me.djamelkorei.projecttaskmanagement.validation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Password matcher custom validation annotation
 *
 * @author Djamel Eddine Korei
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
  String message() default "Field already exist";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
