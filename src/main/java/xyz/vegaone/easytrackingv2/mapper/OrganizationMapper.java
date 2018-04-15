package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "Spring")
public abstract class OrganizationMapper {

    @Autowired
    private ProjectMapper projectMapper;

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
        if (!CollectionUtils.isEmpty(organizationEntity.getProjectList())) {
            organization.setProjectList(projectMapper.domainToDtoList(organizationEntity.getProjectList()));
        }

        // user list
        if (!CollectionUtils.isEmpty(organizationEntity.getUserList())) {
            organization.setUserList(organizationEntity.getUserList()
                    .stream()
                    .map(this::buildUserDto)
                    .collect(Collectors.toList()));
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
    void addIgnoredFieldsToDomain(Organization organization, @MappingTarget OrganizationEntity organizationEntity) {
        // project
        if (!CollectionUtils.isEmpty(organization.getProjectList())) {
            organizationEntity.setProjectList(projectMapper.dtoToDomainList(organization.getProjectList()));
        }

        // user list
        if (!CollectionUtils.isEmpty(organization.getUserList())) {
            organizationEntity.setUserList(
                    organization.getUserList()
                            .stream()
                            .map(this::buildUserDomain)
                            .collect(Collectors.toList()));
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
