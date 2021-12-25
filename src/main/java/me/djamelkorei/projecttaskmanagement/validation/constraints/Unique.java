package me.djamelkorei.projecttaskmanagement.validation.constraints;

import me.djamelkorei.projecttaskmanagement.validation.validator.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Unique custom validation annotation
 *
 * @author Djamel Eddine Korei
 */
@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
  String message() default "Field already exist";

  String table();

  String field();

  boolean auth() default false;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
