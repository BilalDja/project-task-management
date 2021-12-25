package me.djamelkorei.projecttaskmanagement.service.mapper;

import me.djamelkorei.projecttaskmanagement.domain.Role;
import me.djamelkorei.projecttaskmanagement.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Role}.
 *
 * @author Djamel Eddine Korei
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

  @Named("roleDTO")
  RoleDTO mapToRoleDTO(Role role);

}
