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

import java.util.Arrays;

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

    @Test
    public void createProjectTest() {
        //given

        //when
        Project savedProject = buildAndSaveProject();
        savedProject.setUserList(Arrays.asList(buildAndSaveUser()));

        Project createdProject = projectService.createProject(savedProject);

        //then
        Assert.assertNotNull("There should have been one project saved in the database", createdProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, createdProject.getName());

    }

    @Test
    public void getProjectTest() {
        //given
        Project savedProject = projectService.createProject(buildAndSaveProject());

        //when
        Project findProject = projectService.getProject(savedProject.getId());

        //then
        Assert.assertNotNull("There should have been one project saved in the database", savedProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, findProject.getName());

    }

    @Test
    public void deleteProjectTest() {
        //given
        Project savedProject = projectService.createProject(buildAndSaveProject());

        //when
        projectService.deleteProject(savedProject.getId());

        //then
        Assert.assertNull("Project should have been deleted from db", projectService.getProject(savedProject.getId()));

    }

    @Test
    public void updateProjectServiceTest() {
        //given
        Project project = buildAndSaveProject();
        Project savedProject = projectService.createProject(project);

        //when
        savedProject.setName(PROJECT_NEW_NAME);
        Project updatedProject = projectService.updateProject(savedProject);

        //then
        Assert.assertEquals("The project new name should have matched", PROJECT_NEW_NAME, updatedProject.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void projectNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        Project savedProject = buildAndSaveProject();
        //when
        long nonexistentProjectId = -1;
        projectService.getProject(nonexistentProjectId);

    }

    private Project buildAndSaveProject() {
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
}
