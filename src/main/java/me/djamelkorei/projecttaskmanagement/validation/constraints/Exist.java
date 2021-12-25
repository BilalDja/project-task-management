package me.djamelkorei.projecttaskmanagement.validation.constraints;

import me.djamelkorei.projecttaskmanagement.validation.validator.ExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Exist custom validation annotation
 *
 * @author Djamel Eddine Korei
 */
@Documented
@Constraint(validatedBy = ExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exist {
  String message() default "Entity doesn't exist";

  String table();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
