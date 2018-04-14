package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class OrganizationMapper {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "projectList", ignore = true)
    })
    public abstract Organization domainToDto(OrganizationEntity organizationEntity);

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "projectList", ignore = true)
    })
    public abstract OrganizationEntity dtoToDomain(Organization organization);

    public abstract List<Organization> domainToDtoList(List<OrganizationEntity> organizationEntityList);

    public abstract List<OrganizationEntity> dtoToDomainList(List<Organization> organizationList);

    @AfterMapping
    void addIgnoredFieldsToDto(OrganizationEntity organizationEntity, @MappingTarget Organization organization) {
        // project
        List<ProjectEntity> projectEntityList = organizationEntity.getProjectList();
        if (!CollectionUtils.isEmpty(projectEntityList)) {
            projectEntityList.forEach(project -> {
                project.setUserStoryList(Collections.emptyList());
                project.setSprintList(Collections.emptyList());
                project.getUserList().forEach(userEntity -> {
                    userEntity.setUserStoryList(Collections.emptyList());
                    userEntity.setTaskList(Collections.emptyList());
                    userEntity.setBugList(Collections.emptyList());
                    userEntity.setProjectList(Collections.emptyList());
                    userEntity.setOrganization(null);
                });
            });

            List<Project> projectList = projectMapper.domainToDtoList(projectEntityList);
            organization.setProjectList(projectList);
        }

        // user list
        if (!CollectionUtils.isEmpty(organizationEntity.getUserList())) {
            List<UserEntity> userEntityList = organizationEntity.getUserList();
            userEntityList.forEach(userEntity -> {
                userEntity.setBugList(Collections.emptyList());
                userEntity.setUserStoryList(Collections.emptyList());
                userEntity.setTaskList(Collections.emptyList());
                userEntity.setOrganization(null);
            });

            List<User> userList = userMapper.domainToDtoList(organizationEntity.getUserList());

            organization.setUserList(userList);
        }

    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Organization organization, @MappingTarget OrganizationEntity organizationEntity) {
        // project
        List<Project> projectList = organization.getProjectList();
        if (!CollectionUtils.isEmpty(projectList)) {
            projectList.forEach(project -> {
                project.setUserStoryList(Collections.emptyList());
                project.setSprintList(Collections.emptyList());
                project.getUserList().forEach(user -> {
                    user.setUserStoryList(Collections.emptyList());
                    user.setTaskList(Collections.emptyList());
                    user.setBugList(Collections.emptyList());
                    user.setProjectList(Collections.emptyList());
                    user.setOrganization(null);
                });
            });

            List<ProjectEntity> projectEntityList = projectMapper.dtoToDomainList(projectList);

            organizationEntity.setProjectList(projectEntityList);
        }

        // user list
        List<User> userList = organization.getUserList();
        if (!CollectionUtils.isEmpty(userList)) {
            userList.forEach(user -> {
                user.setBugList(Collections.emptyList());
                user.setUserStoryList(Collections.emptyList());
                user.setTaskList(Collections.emptyList());
                user.setOrganization(null);
            });

            List<UserEntity> userEntityList = userMapper.dtoToDomainList(userList);

            organizationEntity.setUserList(userEntityList);
        }
    }
}
