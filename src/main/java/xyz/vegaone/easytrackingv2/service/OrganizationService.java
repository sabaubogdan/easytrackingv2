package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.OrganizationMapper;
import xyz.vegaone.easytrackingv2.mapper.ProjectMapper;
import xyz.vegaone.easytrackingv2.mapper.UserMapper;
import xyz.vegaone.easytrackingv2.repo.OrganizationRepo;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;
import xyz.vegaone.easytrackingv2.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepo organizationRepo;

    private OrganizationMapper organizationMapper;

    private UserRepo userRepo;

    private UserMapper userMapper;

    private ProjectService projectService;

    private ProjectRepo projectRepo;

    private ProjectMapper projectMapper;

    @Autowired
    public OrganizationService(OrganizationRepo organizationRepo, OrganizationMapper organizationMapper, UserRepo userRepo, UserMapper userMapper, ProjectService projectService, ProjectRepo projectRepo, ProjectMapper projectMapper) {
        this.organizationRepo = organizationRepo;
        this.organizationMapper = organizationMapper;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.projectRepo = projectRepo;
        this.projectMapper = projectMapper;
    }

    public Organization createOrganization(Organization organization) {
        OrganizationEntity organizationEntity = organizationMapper.dtoToDomain(organization);
        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        return organizationMapper.domainToDto(savedOrganizationEntity);
    }

    @Transactional
    public Organization getOrganization(Long id) {
        Optional<OrganizationEntity> organizationOptional = organizationRepo.findById(id);

        OrganizationEntity organizationEntity = organizationOptional.orElseThrow(() ->
                new EntityNotFoundException("Organization with id " + id + " not found"));

        Organization organization = organizationMapper.domainToDto(organizationEntity);

        Optional<UserEntity> userOptional = userRepo.findById(organizationEntity.getId());
        Optional<ProjectEntity> projectOptional = projectRepo.findById(organizationEntity.getId());

        User user = null;
        Project project = null;

        if (userOptional.isPresent()) {
            user = userMapper.domainToDto(userOptional.get());
        }
        if (projectOptional.isPresent()) {
            project = projectMapper.domainToDto(projectOptional.get());
        }

        return organization;
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }

    public Organization updateOrganization(Organization organization) {

        OrganizationEntity organizationEntity = organizationMapper.dtoToDomain(organization);

        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        Organization savedOrganization = organizationMapper.domainToDto(savedOrganizationEntity);

        return savedOrganization;

    }

    @Transactional
    public List<Organization> findAllOrganizationsBrief() {
        List<OrganizationEntity> organizationEntityList = organizationRepo.findAllOrganizationsBrief();

        return organizationMapper.domainToDtoList(organizationEntityList);
    }


}
