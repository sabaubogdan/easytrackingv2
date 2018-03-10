package xyz.vegaone.easytrackingv2.project;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.ProjectService;
import xyz.vegaone.easytrackingv2.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    private static final String PROJECT_NAME = "Project one";
    private static final String PROJECT_NEW_NAME = "Project two";

    private static final String USER_NAME = "Bogdan One";
    private static final String USER_EMAIL = "Bogdan@ddbsolutions.com";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createProjectTest() {
        //given

        //when
        Project savedProject = buildProject();
        savedProject.setUserList(Arrays.asList(buildAndSaveUser()));

        Project createdProject = projectService.createProject(savedProject);

        //then
        Assert.assertNotNull("There should have been one project saved in the database", createdProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, createdProject.getName());

    }

    @Test
    public void getProjectTest() {
        //given
        Project savedProject = projectService.createProject(buildProject());

        //when
        Project findProject = projectService.getProject(savedProject.getId());

        //then
        Assert.assertNotNull("There should have been one project saved in the database", savedProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, findProject.getName());

    }

    @Test
    public void deleteProjectTest() {
        //given
        Project savedProject = projectService.createProject(buildProject());

        //when
        projectService.deleteProject(savedProject.getId());

        //then
        Assert.assertNull("Project should have been deleted from db", projectService.getProject(savedProject.getId()));

    }

    @Test
    public void updateProjectServiceTest() {
        //given
        Project project = buildProject();
        Project savedProject = projectService.createProject(project);

        //when
        savedProject.setName(PROJECT_NEW_NAME);
        Project updatedProject = projectService.updateProject(savedProject);

        //then
        Assert.assertEquals("The project new name should have matched", PROJECT_NEW_NAME, updatedProject.getName());

    }

    @Test
    public void findAllProjectsTest() {
        //given
        Project project1 = buildProject();
        Project project2 = buildProject();

        //when
        Project savedProject1 = projectService.createProject(project1);
        Project savedProject2 = projectService.createProject(project2);

        //then
        Assert.assertEquals("There should have been found two project", 2, projectService.findAllProjects(true).size());

    }

    @Test
    public void findAllProjectsByUserIdTest() {
        //given
        User user = buildAndSaveUser();

        Project projectOne = buildProject("ProjectOne");
        projectOne.setUserList(Collections.singletonList(user));
        projectService.createProject(projectOne);

        Project projectTwo = buildProject("ProjectTwo");
        projectTwo.setUserList(Collections.singletonList(user));
        projectService.createProject(projectTwo);

        //when
        List<Project> foundProjects = projectService.findAllProjectsByUserId(user.getId());

        //then
        Assert.assertEquals("There should have been two projects associated with one user", 2, foundProjects.size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void projectNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        Project savedProject = buildProject();
        //when
        long nonexistentProjectId = -1;
        projectService.getProject(nonexistentProjectId);

    }

    private Project buildProject() {
        Project project = new Project();
        project.setName(PROJECT_NAME);

        return project;
    }

    private User buildAndSaveUser() {
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setName(USER_NAME);

        return userService.createUser(user);
    }

    private Project buildProject(String projectName){
        Project project = new Project();
        project.setName(projectName);

        return project;
    }
}
