package me.djamelkorei.projecttaskmanagement.service.mapper;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.Role;
import me.djamelkorei.projecttaskmanagement.domain.User;
import me.djamelkorei.projecttaskmanagement.repository.RoleRepository;
import me.djamelkorei.projecttaskmanagement.repository.UserRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Custom Mapper.
 *
 * @author Djamel Eddine Korei
 */
@Component
@Named("CustomMapper")
@RequiredArgsConstructor
public class CustomMapper {

  private final RoleRepository roleRepository;

  private final UserRepository userRepository;

  @Named("getRoleById")
  public Role getRoleById(Long id) {
    return roleRepository.findById(id).orElse(null);
  }

  @Named("getUserById")
  public User getAllUserIn(Long id) {
    return userRepository.findById(id).get();
  }

  @Named("getUserNameById")
  public String getUserNameById(Long id) {
    return userRepository.findName(id);
  }

}
