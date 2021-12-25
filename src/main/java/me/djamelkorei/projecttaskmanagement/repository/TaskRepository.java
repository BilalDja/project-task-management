package me.djamelkorei.projecttaskmanagement.repository;

import me.djamelkorei.projecttaskmanagement.domain.Task;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Task} entity.
 *
 * @author Djamel Eddine Korei
 */
@Repository
public interface TaskRepository extends DataTablesRepository<Task, Long> {

}
