package me.djamelkorei.projecttaskmanagement.repository;

import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Priority;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link Task} entity.
 *
 * @author Djamel Eddine Korei
 */
@Repository
public interface TaskRepository extends DataTablesRepository<Task, Long> {

  Long countAllByStatusEquals(Status status);

  List<Task> getAllByPriorityEqualsAndStatusEquals(Pageable pageable, Priority priority, Status status);

}
