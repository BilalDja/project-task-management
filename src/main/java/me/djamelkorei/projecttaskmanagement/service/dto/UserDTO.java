package me.djamelkorei.projecttaskmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Unique;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * User DTO.
 *
 * @author Djamel Eddine Korei
 */
@Getter
@Setter
public class UserDTO implements Serializable {

  private Long userId;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Unique(table = "user", field = "username", auth = false)
  private String username;

  private String photoName;

  @NotNull
  @Email
  @Unique(table = "user", field = "email", auth = false)
  private String email;

  @NotNull
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long roleId;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String roleName;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private RoleDTO role;

  @NotNull
  private Boolean active;

}
