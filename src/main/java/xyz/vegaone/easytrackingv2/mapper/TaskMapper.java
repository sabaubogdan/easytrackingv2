package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import xyz.vegaone.easytrackingv2.domain.TaskEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Task;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStory;

import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class TaskMapper {

    @Mappings({
            @Mapping(target = "userStory", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract Task domainToDto(TaskEntity taskEntity);

    @Mappings({
            @Mapping(target = "userStory", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract TaskEntity dtoToDomain(Task task);

    public abstract List<Task> domainToDtoList(List<TaskEntity> taskEntityList);

    public abstract List<TaskEntity> dtoToDomain(List<Task> taskList);

    @AfterMapping
    void addIgnoredFieldsToDto(TaskEntity taskEntity, @MappingTarget Task task) {
        if (taskEntity.getUserStory() != null) {
            UserStory userStory = new UserStory();
            userStory.setId(taskEntity.getUserStory().getId());

            task.setUserStory(userStory);
        }


        if (taskEntity.getUser() != null) {
            User user = new User();
            user.setId(taskEntity.getUser().getId());

            task.setUser(user);
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Task task, @MappingTarget TaskEntity taskEntity) {
        if (task.getUserStory() != null) {
            UserStoryEntity userStoryEntity = new UserStoryEntity();
            userStoryEntity.setId(task.getUserStory().getId());

            taskEntity.setUserStory(userStoryEntity);
        }

        if (task.getUser() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(task.getUser().getId());

            taskEntity.setUser(userEntity);
        }
    }

}
