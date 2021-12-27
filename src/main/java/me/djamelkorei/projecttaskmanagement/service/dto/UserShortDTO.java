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
public class UserShortDTO implements Serializable {

  private Long userId;

  private String username;

  private String photoName;

}
