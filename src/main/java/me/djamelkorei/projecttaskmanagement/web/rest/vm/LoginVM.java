package me.djamelkorei.projecttaskmanagement.web.rest.vm;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * View Model object for storing a user's credentials.
 */
@Getter
@Setter
public class LoginVM {

    @NotNull
    private String login;

    @NotNull
    private String password;

    private Boolean rememberMe;

}
