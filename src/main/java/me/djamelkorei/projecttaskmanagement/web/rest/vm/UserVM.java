package me.djamelkorei.projecttaskmanagement.web.rest.vm;

import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.validation.constraints.Unique;

import javax.validation.constraints.NotNull;

/**
 * View Model object for storing a user's credentials.
 */
@Getter
@Setter
public class UserVM {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Unique(table = "user", field = "username", auth = false)
  private String username;

  @NotNull
  @Unique(table = "user", field = "email", auth = false)
  private String email;

}
