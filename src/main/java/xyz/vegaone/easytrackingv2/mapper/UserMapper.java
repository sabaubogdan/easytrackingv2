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
public abstract class UserMapper {

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private BugMapper bugMapper;

    @Mappings({
            @Mapping(target = "taskList", ignore = true),
            @Mapping(target = "bugList", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract User domainToDto(UserEntity entity);

    @Mappings({
            @Mapping(target = "taskList", ignore = true),
            @Mapping(target = "bugList", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract UserEntity dtoToDomain(User user);

    public abstract List<User> domainToDtoList (List<UserEntity> userEntityList);

    public abstract List<UserEntity> dtoToDomainList(List<User> userList);

    @AfterMapping
    void addIgnoredFieldsToDto(UserEntity userEntity, @MappingTarget User user) {
        if (!CollectionUtils.isEmpty(userEntity.getTaskList())) {
            user.setTaskList(taskMapper.domainToDtoList(userEntity.getTaskList()));
        } else {
            user.setTaskList(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(userEntity.getBugList())) {
            user.setBugList(bugMapper.domainToDtoList(userEntity.getBugList()));
        } else {
            user.setBugList(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(userEntity.getUserStoryList())) {
            user.setUserStoryList(userStoryMapper.domainToDtoList(userEntity.getUserStoryList()));
        } else {
            user.setBugList(Collections.emptyList());
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(User user, @MappingTarget UserEntity userEntity) {
        if (!CollectionUtils.isEmpty(user.getTaskList())) {
            userEntity.setTaskList(taskMapper.dtoToDomain(user.getTaskList()));
        } else {
            userEntity.setTaskList(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(user.getBugList())) {
            userEntity.setBugList(bugMapper.dtoToDomainList(user.getBugList()));
        } else {
            userEntity.setBugList(Collections.emptyList());
        }

        if (!CollectionUtils.isEmpty(user.getUserStoryList())) {
            userEntity.setUserStoryList(userStoryMapper.dtoToDomainList(user.getUserStoryList()));
        } else {
            userEntity.setUserStoryList(Collections.emptyList());
        }
    }
}
