package me.djamelkorei.projecttaskmanagement.service;

import me.djamelkorei.projecttaskmanagement.domain.Task;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Interface for managing {@link Task}.
 */
public interface TaskService {
  /**
   * Save a task.
   *
   * @param task the entity to save.
   * @return the persisted entity.
   */
  Task save(Task task);

  /**
   * Get all the warranties.
   *
   * @param dataTablesInput params.
   * @return the list of entities.
   */
  DataTablesOutput<Task> findAll(DataTablesInput dataTablesInput);

  /**
   * Get all the warranties.
   *
   * @param dataTablesInput params.
   * @param marketSpecification filter.
   * @return the list of entities.
   */
  DataTablesOutput<Task> findAll(DataTablesInput dataTablesInput, Specification<Task> marketSpecification);

  /**
   * Get the "id" task.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<Task> findOne(Long id);

  /**
   * Delete a Task.
   *
   * @param task the entity to delete.
   */
  @Transactional
  void delete(Task task);

}
