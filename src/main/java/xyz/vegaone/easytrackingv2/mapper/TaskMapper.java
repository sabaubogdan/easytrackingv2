package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytrackingv2.domain.TaskEntity;
import xyz.vegaone.easytrackingv2.dto.Task;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface TaskMapper {

    Task domainToDto(TaskEntity taskEntity);

    TaskEntity dtoToDomain(Task task);

    List<Task> domainToDtoList(List<TaskEntity> taskEntityList);

    List<TaskEntity> dtoToDomain(List<Task> taskList);

}
