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
    void addUserProjectToDto(OrganizationEntity organizationEntity, @MappingTarget Organization organization) {
        if (!CollectionUtils.isEmpty(organizationEntity.getProjectList())) {
            List<Project> projectList = projectMapper.domainToDtoList(organizationEntity.getProjectList());
            projectList.forEach(project -> {
//                project.setOrganization(organization);
                project.setUserStoryList(null);
                project.setUserList(null);
                project.setSprintList(null);
            });

            organization.setProjectList(projectList);
        }

        if (!CollectionUtils.isEmpty(organizationEntity.getUserList())) {
            List<User> userList = userMapper.domainToDtoList(organizationEntity.getUserList());
            userList.forEach(user -> {
                user.setEmail(null);
                user.setBugList(null);
                user.setUserStoryList(null);
                user.setTaskList(null);
            });

            organization.setUserList(userList);
        }

    }

    @AfterMapping
    void addUserProjectToDomain(Organization organization, @MappingTarget OrganizationEntity organizationEntity) {
        if (!CollectionUtils.isEmpty(organization.getProjectList())) {
            List<ProjectEntity> projectList = projectMapper.dtoToDomainList(organization.getProjectList());
            projectList.forEach(project -> {
                project.setUserStoryList(null);
                project.setUserList(null);
                project.setSprintList(null);
            });

            organizationEntity.setProjectList(projectList);
        }

        if (!CollectionUtils.isEmpty(organization.getUserList())) {
            List<UserEntity> userList = userMapper.dtoToDomainList(organization.getUserList());
            userList.forEach(user -> {
                user.setEmail(null);
                user.setBugList(null);
                user.setUserStoryList(null);
                user.setTaskList(null);
            });

            organizationEntity.setUserList(userList);
        }
    }
}
