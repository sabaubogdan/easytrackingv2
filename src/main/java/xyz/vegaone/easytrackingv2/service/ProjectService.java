package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.ProjectMapper;
import xyz.vegaone.easytrackingv2.mapper.UserMapper;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;
import xyz.vegaone.easytrackingv2.repo.UserRepo;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private ProjectMapper projectMapper;

    private UserService userService;

    private UserRepo userRepo;

    private UserMapper userMapper;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, ProjectMapper projectMapper, UserService userService, UserRepo userRepo, UserMapper userMapper) {
        this.projectRepo = projectRepo;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public Project createProject(Project project) {
        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);
        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return projectMapper.domainToDto(savedProjectEntity);

    }

    public Project getProject(Long id) {
        ProjectEntity projectEntity = projectRepo.findOne(id);

        if (projectEntity == null) {
            throw new EntityNotFoundException("Project with id " + id + " not found");
        }


        return projectMapper.domainToDto(projectEntity);

    }

    public void deleteProject(Long id) {
        projectRepo.delete(id);

    }

    public Project updateProject(Project project) {

        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        Project savedProject = projectMapper.domainToDto(savedProjectEntity);

        return savedProject;
    }

    public List<Project> findAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepo.findAll();

        return projectMapper.domainToDtoList(projectEntityList);
    }

    public List<Project> findProjectByUserId(Long userId) {
        List<ProjectEntity> projectEntityList = Collections.emptyList();
        projectEntityList = projectRepo.findAllByUserId(userId);

        return projectMapper.domainToDtoList(projectEntityList);
    }
}
