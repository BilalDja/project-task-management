package me.djamelkorei.projecttaskmanagement.service.dto;

import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.Task;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Task} entity.
 */
@Getter
@Setter
public class TaskStatisticDTO implements Serializable {

  private Long taskRequestedTotal;
  private Long taskInProgressTotal;
  private Long taskCompletedTotal;
  private Long usersTotal;
  private List<TaskDTO> importantTasks;
  private List<TaskDTO> recentTasks;
  private List<UserShortDTO> users;

}
