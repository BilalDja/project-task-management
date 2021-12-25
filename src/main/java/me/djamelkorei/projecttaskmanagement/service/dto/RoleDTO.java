package me.djamelkorei.projecttaskmanagement.service.dto;

import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Role DTO.
 *
 * @author Djamel Eddine Korei
 */
@Getter
@Setter
public class RoleDTO implements Serializable {

    private Long roleId;

    @NotNull
    private String roleName;

}
