package me.djamelkorei.projecttaskmanagement.repository;

import me.djamelkorei.projecttaskmanagement.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Role} entity.
 *
 * @author Djamel Eddine Korei
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
