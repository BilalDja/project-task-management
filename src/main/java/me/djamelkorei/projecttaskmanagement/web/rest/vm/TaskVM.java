package me.djamelkorei.projecttaskmanagement.web.rest.vm;

import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Status;

import java.io.Serializable;

/**
 * A VM for the {@link Task} entity.
 */
@Getter
@Setter
public class TaskVM implements Serializable {

  private Status status;

}
