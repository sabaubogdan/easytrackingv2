package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStory;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.ProjectMapper;
import xyz.vegaone.easytrackingv2.mapper.SprintMapper;
import xyz.vegaone.easytrackingv2.mapper.UserMapper;
import xyz.vegaone.easytrackingv2.mapper.UserStoryMapper;
import xyz.vegaone.easytrackingv2.repo.ProjectRepo;
import xyz.vegaone.easytrackingv2.repo.SprintRepo;
import xyz.vegaone.easytrackingv2.repo.UserRepo;
import xyz.vegaone.easytrackingv2.repo.UserStoryRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private ProjectMapper projectMapper;

    private UserService userService;

    private UserRepo userRepo;

    private UserMapper userMapper;

    private SprintService sprintService;

    private SprintRepo sprintRepo;

    private SprintMapper sprintMapper;

    private UserStoryService userStoryService;

    private UserStoryRepo userStoryRepo;

    private UserStoryMapper userStoryMapper;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, ProjectMapper projectMapper, UserService userService,
                          UserRepo userRepo, UserMapper userMapper, SprintService sprintService,
                          SprintRepo sprintRepo, SprintMapper sprintMapper, UserStoryService userStoryService,
                          UserStoryRepo userStoryRepo, UserStoryMapper userStoryMapper) {
        this.projectRepo = projectRepo;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.sprintService = sprintService;
        this.sprintRepo = sprintRepo;
        this.sprintMapper = sprintMapper;
        this.userStoryService = userStoryService;
        this.userStoryRepo = userStoryRepo;
        this.userStoryMapper = userStoryMapper;
    }

    public Project createProject(Project project) {
        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);
        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        return projectMapper.domainToDto(savedProjectEntity);

    }

    @Transactional
    public Project getProject(Long id) {

        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);

        if (projectOptional.isPresent()) {
            ProjectEntity projectEntity = projectOptional.get();
            Project project = projectMapper.domainToDto(projectEntity);

            Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(projectEntity.getId());
            Optional<UserEntity> userOptional = userRepo.findById(projectEntity.getId());
            Optional<SprintEntity> sprintOptional = sprintRepo.findById(projectEntity.getId());

            UserStory userStory = null;
            User user = null;
            Sprint sprint = null;

            if (userStoryOptional.isPresent()) {
                userStory = userStoryMapper.domainToDto(userStoryOptional.get());
            }
            if ((userOptional.isPresent())) {
                user = userMapper.domainToDto((userOptional.get()));
            }
            if (sprintOptional.isPresent()) {
                sprint = sprintMapper.domainToDto((sprintOptional.get()));
            }

            return project;
        } else {
            throw new EntityNotFoundException("Project with id " + id + " not found");
        }

    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);

    }

    public Project updateProject(Project project) {

        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);

        Project savedProject = projectMapper.domainToDto(savedProjectEntity);

        return savedProject;
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
