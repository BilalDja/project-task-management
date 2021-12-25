package me.djamelkorei.projecttaskmanagement.web.rest.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Unique;
import javax.validation.constraints.NotNull;

/**
 * View Model used in the user management.
 */
@Getter
@Setter
@NoArgsConstructor
public class ManagedUserVM  {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Unique(table = "user", field = "username", auth = true)
  private String username;

  @NotNull
  @Unique(table = "user", field = "email", auth = true)
  private String email;

  @NotNull
  private String password;

}

