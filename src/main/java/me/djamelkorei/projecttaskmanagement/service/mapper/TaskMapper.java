package me.djamelkorei.projecttaskmanagement.service.mapper;

import me.djamelkorei.projecttaskmanagement.domain.Task;
import me.djamelkorei.projecttaskmanagement.service.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

/**
 * Mapper for the entity {@link Task}.
 *
 * @author Djamel Eddine Korei
 */
@Mapper(componentModel = "spring", uses = {CustomMapper.class, UserMapper.class})
public interface TaskMapper {

  @Named("taskDTO")
  @Mapping(target = "user", qualifiedByName = {"userShortDTO"})
  TaskDTO mapToTaskDTO(Task task);

  @Mapping(target = "taskId", ignore = true)
  @Mapping(target = "user", source = "userId", qualifiedByName = {"CustomMapper", "getUserById"})
  Task mapToTask(TaskDTO taskDTO, @MappingTarget Task task);


  @Mapping(target = "data", source = "data", qualifiedByName = {"taskDTO"})
  DataTablesOutput<TaskDTO> maptToDataTableTaskDTO(DataTablesOutput<Task> all);

}
