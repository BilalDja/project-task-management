package me.djamelkorei.projecttaskmanagement.web.rest.vm;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/**
 * View Model object for storing a user's credentials.
 */
@Getter
@Setter
public class EmailVM {

    @Email
    private String email;

}
