package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping(value = "api/project")
@Slf4j
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{id}")
    public Project getProject(@PathVariable(value = "id") Long id) {
        Project project = projectService.getProject(id);

        return project;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);

        return createdProject;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Project updateProject(@RequestBody Project project) {
        Project updatedProject = projectService.updateProject(project);

        return updatedProject;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable(value = "id") Long id) {
        projectService.deleteProject(id);
    }

    @GetMapping(value = "/all")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findAllProjects(@RequestParam("brief") boolean brief) {
        List<Project> allProjects = projectService.findAllProjects(brief);
        return allProjects;
    }

    @GetMapping(value = "/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findAllProjectsByUserId(@PathVariable(value = "id") Long userId) {
        return projectService.findAllProjectsByUserId(userId);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String projectNotFoundError(EntityNotFoundException entityNotFoundException) {

        return entityNotFoundException.getErrMsg();
    }


}
