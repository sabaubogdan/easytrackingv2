package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.ProjectMapper;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, ProjectMapper projectMapper) {
        this.projectRepo = projectRepo;
        this.projectMapper = projectMapper;
    }

    public Project createProject(Project project) {
        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);
        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return projectMapper.domainToDto(savedProjectEntity);

    }

    public Project getProject(Long id) {

        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);

        ProjectEntity projectEntity = projectOptional.orElseThrow(() ->
                new EntityNotFoundException("Project with id " + id + " not found"));

        return projectMapper.domainToDto(projectEntity);

    }


    public void deleteProject(Long id) {
        projectRepo.deleteById(id);

    }

    public Project updateProject(Project project) {

        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return projectMapper.domainToDto(savedProjectEntity);
    }

    public List<Project> findAllProjects(Boolean brief) {

        if (brief) {

            List<ProjectEntity> projectEntityList = projectRepo.findAllProjectsBrief();

            return projectMapper.domainToDtoList(projectEntityList);

        } else {
            List<ProjectEntity> projectEntityList = projectRepo.findAll();

            return projectMapper.domainToDtoList(projectEntityList);
        }
    }

    @Transactional
    public List<Project> findAllProjectsByUserId(Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);

        List<ProjectEntity> projectEntityList = projectRepo.findAllByUserList(Arrays.asList(user));

        if (CollectionUtils.isEmpty(projectEntityList)) {
            throw new EntityNotFoundException("No projects found for user id: " + userId);
        }

        return projectMapper.domainToDtoList(projectEntityList);
    }
}
