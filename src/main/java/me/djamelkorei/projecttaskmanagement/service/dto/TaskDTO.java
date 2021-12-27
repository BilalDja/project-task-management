package me.djamelkorei.projecttaskmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Priority;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Status;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * A DTO for the {@link Task} entity.
 */
@Getter
@Setter
public class TaskDTO implements Serializable {

  private Long taskId;

  private String title;

  private String description;

  private Status status;

  private Priority priority;

  private Instant createdAt;

  @NotNull
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long userId;

  private String username;

  private String userFirstName;

  private String userLastName;

  private String userPhotoName;

}
