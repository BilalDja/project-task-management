package me.djamelkorei.projecttaskmanagement.validation.validator;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.util.RequestUtils;
import me.djamelkorei.projecttaskmanagement.util.SecurityUtils;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Unique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Unique custom validation
 *
 * @author Djamel Eddine Korei
 */
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {
  Logger log = LoggerFactory.getLogger(UniqueValidator.class);

  private final JdbcTemplate jdbcTemplate;

  private String table;

  private String field;

  private boolean auth;

  @Override
  public void initialize(Unique constraintAnnotation) {

    this.table = constraintAnnotation.table();

    this.field = constraintAnnotation.field();

    this.auth = constraintAnnotation.auth();

  }

  @Override
  public boolean isValid(String fieldValue, ConstraintValidatorContext constraintValidatorContext) {

    Long keyValue = auth
      ? SecurityUtils.getCurrentUserId().orElse(null)
      : RequestUtils.getLongPathVariable("id");

    try {
      final String sql = "select count(*) from " + table + " where " + field + " = ? " + getIgnore(keyValue);
      log.info("sql: {} - value {}", sql, keyValue);
      Integer count = jdbcTemplate.queryForObject(sql, Integer.class, getParams(keyValue, fieldValue));
      return count != null && count.equals(0);
    } catch (Exception e) {
      return false;
    }

  }

  private String getIgnore(Long keyValue) {
    return keyValue == null
      ? ""
      : " AND " + table + "_id  NOT IN ( ? ) ";
  }

  private Object[] getParams(Long keyValue, Object fieldValue) {
    return keyValue == null
      ? new Object[]{fieldValue}
      : new Object[]{fieldValue, keyValue};
  }

}
