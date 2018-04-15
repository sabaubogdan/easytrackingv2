package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "Spring")
public abstract class ProjectMapper {

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "organization", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract Project domainToDto(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "userList", ignore = true),
            @Mapping(target = "organization", ignore = true),
            @Mapping(target = "userStoryList", ignore = true)
    })
    public abstract ProjectEntity dtoToDomain(Project project);

    public abstract List<Project> domainToDtoList(List<ProjectEntity> projectEntityList);

    public abstract List<ProjectEntity> dtoToDomainList(List<Project> projectList);

    @AfterMapping
    void addIgnoredFieldsToDto(ProjectEntity projectEntity, @MappingTarget Project project) {
        // user story
        if (!CollectionUtils.isEmpty(projectEntity.getUserStoryList())) {
            project.setUserStoryList(userStoryMapper.domainToDtoList(projectEntity.getUserStoryList()));
        }

        // user list
        if (!CollectionUtils.isEmpty(projectEntity.getUserList())) {
            project.setUserList(
                    projectEntity.getUserList()
                            .stream()
                            .map(this::buildUserDto)
                            .collect(Collectors.toList()));
        }

        // organization
        if (projectEntity.getOrganization() != null) {
            Organization organization = new Organization();
            organization.setId(projectEntity.getOrganization().getId());
            organization.setName(projectEntity.getOrganization().getName());
            project.setOrganization(organization);
        }
    }

    private User buildUserDto(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        return user;
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Project project, @MappingTarget ProjectEntity projectEntity) {
        // user story
        if (!CollectionUtils.isEmpty(project.getUserStoryList())) {
            projectEntity.setUserStoryList(userStoryMapper.dtoToDomainList(project.getUserStoryList()));
        }

        // user list
        if (!CollectionUtils.isEmpty(project.getUserList())) {
            projectEntity.setUserList(
                    project.getUserList()
                            .stream()
                            .map(this::buildUserDomain)
                            .collect(Collectors.toList()));
        }

        // organization
        if (project.getOrganization() != null) {
            OrganizationEntity organizationEntity = new OrganizationEntity();
            organizationEntity.setId(project.getOrganization().getId());
            organizationEntity.setName(project.getOrganization().getName());
            projectEntity.setOrganization(organizationEntity);
        }
    }

    private UserEntity buildUserDomain(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

}
