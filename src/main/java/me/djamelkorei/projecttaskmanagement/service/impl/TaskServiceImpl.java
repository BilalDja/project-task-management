package me.djamelkorei.projecttaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.repository.TaskRepository;
import me.djamelkorei.projecttaskmanagement.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Task}.
 *
 * @author Djamel Eddine Korei
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

  private final TaskRepository taskRepository;

  /**
   * Get all the task.
   *
   * @param dataTablesInput request
   * @return the list of entities.
   */
  @Override
  @Transactional(readOnly = true)
  public DataTablesOutput<Task> findAll(DataTablesInput dataTablesInput) {
    log.debug("Request to get all Tasks");
    return taskRepository.findAll(dataTablesInput);
  }

  /**
   * Get all the warranties.
   *
   * @param dataTablesInput     params.
   * @param marketSpecification filter.
   * @return the list of entities.
   */
  @Override
  @Transactional(readOnly = true)
  public DataTablesOutput<Task> findAll(DataTablesInput dataTablesInput, Specification<Task> marketSpecification) {
    log.debug("Request to get all Tasks");
    return taskRepository.findAll(dataTablesInput, marketSpecification);
  }

  /**
   * Get one task by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<Task> findOne(Long id) {
    log.debug("Request to get Task : {}", id);
    return taskRepository.findById(id);
  }

  /**
   * Save a task.
   *
   * @param task the entity to save.
   * @return the persisted entity.
   */
  @Override
  @Transactional
  public Task save(Task task) {
    log.debug("Request to save Task : {}", task);
    return taskRepository.save(task);
  }

  /**
   * Delete a task.
   *
   * @param task the entity to delete.
   */
  @Override
  @Transactional
  public void delete(Task task) {
    log.debug("Request to delete Task : {}", task);
    taskRepository.deleteById(task.getTaskId());
  }

}
