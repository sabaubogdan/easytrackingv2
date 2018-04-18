package xyz.vegaone.easytrackingv2.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;
import xyz.vegaone.easytrackingv2.util.MapperUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private Mapper mapper;

    private MapperUtil mapperUtil;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, Mapper mapper, MapperUtil mapperUtil) {
        this.projectRepo = projectRepo;
        this.mapper = mapper;
        this.mapperUtil = mapperUtil;
    }

    public Project createProject(Project project) {
        ProjectEntity projectEntity = mapper.map(project, ProjectEntity.class);
        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return mapper.map(savedProjectEntity, Project.class);

    }

    public Project getProject(Long id) {

        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);

        ProjectEntity projectEntity = projectOptional.orElseThrow(() ->
                new EntityNotFoundException("Project with id " + id + " not found"));

        return mapper.map(projectEntity, Project.class);

    }


    public void deleteProject(Long id) {
        projectRepo.deleteById(id);

    }

    public Project updateProject(Project project) {

        ProjectEntity projectEntity = mapper.map(project, ProjectEntity.class);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return mapper.map(savedProjectEntity, Project.class);
    }

    public List<Project> findAllProjects(Boolean brief) {

        List<ProjectEntity> projectEntityList = new ArrayList<>();

        if (brief) {
            projectEntityList = projectRepo.findAllProjectsBrief();
        } else {
            projectEntityList = projectRepo.findAll();
        }

        return mapperUtil.mapList(projectEntityList, Project.class);
    }

    public List<Project> findAllProjectsByUserId(Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);

        List<ProjectEntity> projectEntityList = projectRepo.findAllByUserListIsContaining(user);

        if (CollectionUtils.isEmpty(projectEntityList)) {
            throw new EntityNotFoundException("No projects found for user id: " + userId);
        }

        return mapperUtil.mapList(projectEntityList, Project.class);
    }

    public List<Project> findAllProjectsByOrganizationId(Long organizationId) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setId(organizationId);

        List<ProjectEntity> projectEntityList = projectRepo.findAllByOrganization(organizationEntity);

        if (CollectionUtils.isEmpty(projectEntityList)) {
            throw new EntityNotFoundException("No projects found for user id: " + organizationId);
        }

        return mapperUtil.mapList(projectEntityList, Project.class);
    }
}
