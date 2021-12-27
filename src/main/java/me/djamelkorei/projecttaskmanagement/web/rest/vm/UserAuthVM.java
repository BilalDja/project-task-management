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
public class UserAuthVM {

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

  private String photoName;

}
