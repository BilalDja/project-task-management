package me.djamelkorei.projecttaskmanagement.validation.validator;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Exist;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Exist custom validation
 *
 * @author Djamel Eddine Korei
 */
@RequiredArgsConstructor
public class ExistValidator implements ConstraintValidator<Exist, Long> {

  private final JdbcTemplate jdbcTemplate;

  private String table;

  @Override
  public void initialize(Exist constraintAnnotation) {
    this.table = constraintAnnotation.table();
  }

  @Override
  public boolean isValid(Long entityId, ConstraintValidatorContext constraintValidatorContext) {
    try {
      final String sql = "select count(*) from " + table + " where " + table + "_id " + "  = ? ";
      Integer count = jdbcTemplate.queryForObject(sql, Integer.class,  new Object[]{entityId});
      return count != null && count.equals(1);
    } catch (Exception e) {
      return false;
    }
  }

}
