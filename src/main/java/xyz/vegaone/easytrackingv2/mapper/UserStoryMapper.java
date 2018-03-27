package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStory;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class UserStoryMapper {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private BugMapper bugMapper;

    @Mappings({
            @Mapping(target = "project", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "bugs", ignore = true),
            @Mapping(target = "tasks", ignore = true)
    })
    public abstract UserStory domainToDto(UserStoryEntity userStoryEntity);

    @Mappings({
            @Mapping(target = "project", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "bugs", ignore = true),
            @Mapping(target = "tasks", ignore = true)
    })
    public abstract UserStoryEntity dtoToDomain(UserStory userStory);

    public abstract List<UserStory> domainToDtoList(List<UserStoryEntity> userStoryEntityList);

    public abstract List<UserStoryEntity> dtoToDomainList(List<UserStory> userStoryList);

    @AfterMapping
    void addIgnoredFieldsToDto(UserStoryEntity userStoryEntity, @MappingTarget UserStory userStory) {
        if (userStoryEntity.getProject() != null) {
            Project project = new Project();
            project.setId(userStoryEntity.getProject().getId());
            project.setTitle(userStoryEntity.getProject().getTitle());

            userStory.setProject(project);
        }

        if (!CollectionUtils.isEmpty(userStoryEntity.getTasks())) {
            userStory.setTasks(taskMapper.domainToDtoList(userStoryEntity.getTasks()));
        } else {
            userStory.setTasks(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(userStoryEntity.getBugs())) {
            userStory.setBugs(bugMapper.domainToDtoList(userStoryEntity.getBugs()));
        } else {
            userStory.setBugs(Collections.emptyList());
        }

        if (userStoryEntity.getUser() != null) {
            User user = new User();
            user.setId(userStoryEntity.getUser().getId());
            user.setName(userStoryEntity.getUser().getName());

            userStory.setUser(user);
        } else {
            userStory.setUser(null);
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(UserStory userStory, @MappingTarget UserStoryEntity userStoryEntity) {
        if (userStory.getProject() != null) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setId(userStory.getProject().getId());
            projectEntity.setTitle(userStory.getProject().getTitle());

            userStoryEntity.setProject(projectEntity);
        }

        if (!CollectionUtils.isEmpty(userStory.getTasks())) {
            userStoryEntity.setTasks(taskMapper.dtoToDomain(userStory.getTasks()));
        } else {
            userStoryEntity.setTasks(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(userStory.getBugs())) {
            userStoryEntity.setBugs(bugMapper.dtoToDomainList(userStory.getBugs()));
        } else {
            userStoryEntity.setTasks(Collections.emptyList());
        }

        if (userStory.getUser() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userStory.getUser().getId());
            userEntity.setName(userStory.getUser().getName());

            userStoryEntity.setUser(userEntity);
        } else {
            userStoryEntity.setUser(null);
        }
    }
}
