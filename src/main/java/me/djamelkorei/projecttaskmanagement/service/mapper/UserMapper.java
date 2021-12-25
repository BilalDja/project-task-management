package me.djamelkorei.projecttaskmanagement.service.mapper;

import me.djamelkorei.projecttaskmanagement.domain.User;
import me.djamelkorei.projecttaskmanagement.service.dto.UserDTO;
import me.djamelkorei.projecttaskmanagement.service.dto.UserShortDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

/**
 * Mapper for the entity {@link User}.
 *
 * @author Djamel Eddine Korei
 */
@Mapper(componentModel = "spring", uses = {CustomMapper.class, RoleMapper.class})
public interface UserMapper {

  @Named("userDTO")
  @Mapping(target = "role", qualifiedByName = {"roleDTO"})
  UserDTO mapToUserDTO(User user);

  @Named("userShortDTO")
  UserShortDTO mapToUserShortDTO(User user);

  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "role", source = "roleId", qualifiedByName = {"CustomMapper", "getRoleById"})
  User mapToUser(UserDTO userDTO, @MappingTarget User user);

  @Mapping(target = "data", source = "data", qualifiedByName = {"userDTO"})
  DataTablesOutput<UserDTO> maptToDataTableUserDTO(DataTablesOutput<User> userDataTablesOutput);

}
