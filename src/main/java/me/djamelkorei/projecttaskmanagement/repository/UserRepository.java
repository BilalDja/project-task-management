package me.djamelkorei.projecttaskmanagement.repository;

import me.djamelkorei.projecttaskmanagement.domain.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * Spring Data JPA repository for the {@link User} entity.
 *
 * @author Djamel Eddine Korei
 */
@Repository
public interface UserRepository extends DataTablesRepository<User, Long> {

    Optional<User> findOneByUsernameOrEmail(String username, String email);

    Set<User> findAllByUserIdIn(Set<Long> ids);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByResetKey(String resetKey);

    @Query("select concat( u.firstName , ' ' ,u.lastName) as name from user u where u.userId = :id")
    String findName(Long id);

}
