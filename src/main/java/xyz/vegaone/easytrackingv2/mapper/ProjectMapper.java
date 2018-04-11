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
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class ProjectMapper {

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract Project domainToDto(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract ProjectEntity dtoToDomain(Project project);

    public abstract List<Project> domainToDtoList(List<ProjectEntity> projectEntityList);

    public abstract List<ProjectEntity> dtoToDomainList(List<Project> projectList);

    @AfterMapping
    void addUserStoryToDto(ProjectEntity projectEntity, @MappingTarget Project project) {
        if (!CollectionUtils.isEmpty(projectEntity.getUserStoryList())) {
            project.setUserStoryList(userStoryMapper.domainToDtoList(projectEntity.getUserStoryList()));
        }

        if (!CollectionUtils.isEmpty(projectEntity.getUserList())) {
            List<User> userList = userMapper.domainToDtoList(projectEntity.getUserList());
            userList.forEach(user -> {
                user.setUserStoryList(Collections.emptyList());
                user.setTaskList(Collections.emptyList());
                user.setUserStoryList(Collections.emptyList());
            });
            project.setUserList(userList);
        }
    }

    @AfterMapping
    void addUserStoryToDomain(Project project, @MappingTarget ProjectEntity projectEntity) {
        if (!CollectionUtils.isEmpty(project.getUserStoryList())) {
            projectEntity.setUserStoryList(userStoryMapper.dtoToDomainList(project.getUserStoryList()));
        }

        if (!CollectionUtils.isEmpty(project.getUserList())) {
            List<UserEntity> userList = userMapper.dtoToDomainList(project.getUserList());
            userList.forEach(user -> {
                user.setUserStoryList(Collections.emptyList());
                user.setTaskList(Collections.emptyList());
                user.setUserStoryList(Collections.emptyList());
            });
            projectEntity.setUserList(userList);
        }
    }

}
