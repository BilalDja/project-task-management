package me.djamelkorei.projecttaskmanagement.web.rest;

import lombok.RequiredArgsConstructor;
import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Priority;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Status;
import me.djamelkorei.projecttaskmanagement.repository.TaskRepository;
import me.djamelkorei.projecttaskmanagement.repository.UserRepository;
import me.djamelkorei.projecttaskmanagement.security.AuthoritiesConstants;
import me.djamelkorei.projecttaskmanagement.service.TaskService;
import me.djamelkorei.projecttaskmanagement.service.UserService;
import me.djamelkorei.projecttaskmanagement.service.dto.TaskDTO;
import me.djamelkorei.projecttaskmanagement.service.dto.TaskStatisticDTO;
import me.djamelkorei.projecttaskmanagement.service.mapper.TaskMapper;
import me.djamelkorei.projecttaskmanagement.service.mapper.UserMapper;
import me.djamelkorei.projecttaskmanagement.util.SecurityUtils;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.CurrentUserLoginNotFoundException;
import me.djamelkorei.projecttaskmanagement.web.rest.errors.ResourceNotFoundException;
import me.djamelkorei.projecttaskmanagement.web.rest.vm.TaskVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link Task}.
 *
 * @author Djamel Eddine Korei
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskResource {

  private final Logger log = LoggerFactory.getLogger(TaskResource.class);
  private final TaskService taskService;
  private final TaskMapper taskMapper;


  // todo: should be called inside a service not a controller
  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserService userService;

  // todo: refactor
  @GetMapping("/taskStatistic")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TaskStatisticDTO> taskStatisticDTO() {
    TaskStatisticDTO taskStatisticDTO = new TaskStatisticDTO();
    taskStatisticDTO.setTaskRequestedTotal(taskRepository.countAllByStatusEquals(Status.REQUESTED));
    taskStatisticDTO.setTaskInProgressTotal(taskRepository.countAllByStatusEquals(Status.IN_PROGRESS));
    taskStatisticDTO.setTaskCompletedTotal(taskRepository.countAllByStatusEquals(Status.COMPLETED));
    taskStatisticDTO.setUsersTotal(userRepository.count());
    taskStatisticDTO.setImportantTasks(taskRepository.getAllByPriorityEqualsAndStatusEquals(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAt")), Priority.HIGH, Status.REQUESTED).stream().map(taskMapper::mapToTaskDTO).collect(Collectors.toList()));
    taskStatisticDTO.setRecentTasks(taskRepository.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAt"))).stream().map(taskMapper::mapToTaskDTO).collect(Collectors.toList()));
    taskStatisticDTO.setUsers(userRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "userId"))).stream().map(userMapper::mapToUserShortDTO).collect(Collectors.toList()));
    return ResponseEntity.ok(taskStatisticDTO);
  }

  @GetMapping("/tasksDataTable")
  public DataTablesOutput<TaskDTO> findAllTasksList(DataTablesInput input) {
    log.debug("REST request to get a datatable of Tasks");
    if (SecurityUtils.getCurrentUserId().isEmpty()) {
      throw new CurrentUserLoginNotFoundException();
    }
    if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
      Specification<Task> MarketSpecification = (Specification<Task>) (root, query, criteriaBuilder) ->
        root.get("user").get("userId").in(SecurityUtils.getCurrentUserId().get());
      return taskMapper.maptToDataTableTaskDTO(taskService.findAll(input, MarketSpecification));
    }
    return taskMapper.maptToDataTableTaskDTO(taskService.findAll(input));
  }

  /**
   * {@code POST  /tasks} : Create a new task.
   *
   * @param taskDTO the taskDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskDTO,
   * or with status {@code 400 (Bad Request)} if the taskDTO is not valid.
   */
  @PostMapping("/tasks")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
    log.debug("REST request to create Task : {}", taskDTO);
    return Optional.of(new Task())
      .map(t -> {
        t.setCreatedAt(Instant.now());
        return t;
      })
      .map(t -> taskMapper.mapToTask(taskDTO, t))
      .map(taskService::save)
      .map(taskMapper::mapToTaskDTO)
      .map(ResponseEntity::ok)
      .get();
  }

  /**
   * {@code PUT  /tasks:id} : Updates an existing task.
   *
   * @param taskDTO the taskDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDTO,
   * or with status {@code 400 (Bad Request)} if the taskDTO is not valid,
   * or with status {@code 404 (Not Found)}.
   * or with status {@code 500 (Internal Server Error)} if the taskDTO couldn't be updated.
   */
  @PutMapping("/tasks/{id}")
  public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable Long id) {
    log.debug("REST request to update Task : {}", taskDTO);

    if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER)) {
      Long userId = SecurityUtils.getCurrentUserId().orElse(0L);
      if (userId.equals(taskDTO.getUserId())) {
        return taskService.findOne(taskDTO.getTaskId())
          .map(t -> {
            t.setStatus(taskDTO.getStatus());
            return t;
          })
          .map(taskService::save)
          .map(taskMapper::mapToTaskDTO)
          .map(ResponseEntity::ok)
          .orElseThrow(ResourceNotFoundException::new);
      } else {
        throw new ResourceNotFoundException();
      }
    }

    return taskService.findOne(id)
      .map(c -> taskMapper.mapToTask(taskDTO, c))
      .map(t -> {
        t.setStatus(taskDTO.getStatus());
        if (taskDTO.getStatus().equals(Status.COMPLETED)) {
          t.setCompletedAt(Instant.now());
        } else {
          t.setCompletedAt(null);
        }
        return t;
      })
      .map(taskService::save)
      .map(taskMapper::mapToTaskDTO)
      .map(ResponseEntity::ok)
      .orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * {@code POST  /tasks:id/status} : Updates task status.
   *
   * @param taskVM the taskStatusDTO to update status.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskVM,
   * or with status {@code 400 (Bad Request)} if the taskVM is not valid,
   * or with status {@code 404 (Not Found)}.
   * or with status {@code 500 (Internal Server Error)} if the taskVM couldn't be updated.
   */
  @PostMapping("/tasks/{id}/status")
  public ResponseEntity<TaskDTO> updateTaskStatus(@Valid @RequestBody TaskVM taskVM, @PathVariable Long id) {
    log.debug("REST request to update Task status: {}", taskVM);
    return taskService.findOne(id)
      .map(t -> {
        t.setStatus(taskVM.getStatus());
        if (taskVM.getStatus().equals(Status.COMPLETED)) {
          t.setCompletedAt(Instant.now());
        } else {
          t.setCompletedAt(null);
        }
        return t;
      })
      .map(taskService::save)
      .map(taskMapper::mapToTaskDTO)
      .map(ResponseEntity::ok)
      .orElseThrow(ResourceNotFoundException::new);
  }

  /**
   * {@code DELETE  /tasks/:id} : delete the "id" task.
   *
   * @param id the id of the task to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)},
   * or with status {@code 404 (Not Found)}.
   */
  @DeleteMapping("/tasks/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
    Task task = taskService.findOne(id)
      .orElseThrow(ResourceNotFoundException::new);
    taskService.delete(task);
    return ResponseEntity.noContent().build();
  }

}
