package xyz.vegaone.easytrackingv2.Task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.Task;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.TaskService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceTest {

    private static final String TASK_NAME = "Task name";
    private static final String TASK_NEW_NAME = "Task name";
    private static final String TASK_DESCRIPTION = "Task description";
    private static final String TASK_STATUS = "Task status";
    private static final Long TASK_PRIORITY = 1L;
    private static final Long TASK_ESTIMATION = 55L;

    @Autowired
    private TaskService taskService;


    @Test
    public void createTask() {
        //given

        //when
        Task savedTask = buildAndSaveTask();

        //then
        Assert.assertNotNull("There should have been one task saved in the database", savedTask);
        Assert.assertEquals("The task name should have matched", TASK_NAME, savedTask.getName());
        Assert.assertEquals("The task description should have matched", TASK_DESCRIPTION, savedTask.getDescription());
        Assert.assertEquals("The task status should have matched", TASK_STATUS, savedTask.getStatus());
        Assert.assertEquals("The task priority should have matched", TASK_PRIORITY, savedTask.getPriority());
        Assert.assertEquals("The task estimation should have matched", TASK_ESTIMATION, savedTask.getEstimation());
    }

    @Test
    public void getTask() {
        //given
        Task savedTask = buildAndSaveTask();

        //when
        Task findTask = taskService.getTask(savedTask.getId());

        //then
        Assert.assertNotNull("There should have been one task saved in the database", findTask);
        Assert.assertEquals("The task name should have matched", TASK_NAME, findTask.getName());
        Assert.assertEquals("The task description should have matched", TASK_DESCRIPTION, findTask.getDescription());
        Assert.assertEquals("The task status should have matched", TASK_STATUS, findTask.getStatus());
        Assert.assertEquals("The task priority should have matched", TASK_PRIORITY, findTask.getPriority());
        Assert.assertEquals("The task estimation should have matched", TASK_ESTIMATION, findTask.getEstimation());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteTask() {
        //given
        Task savedTask = buildAndSaveTask();

        //when
        taskService.deleteTask(savedTask.getId());

        //then
        Assert.assertNull("The task should have been deleted", taskService.getTask(savedTask.getId()));

    }

    @Test
    public void updateTask() {
        //given
        Task savedTask = buildAndSaveTask();

        //when
        savedTask.setName(TASK_NEW_NAME);
        Task updatedTask = taskService.updateTask(savedTask);

        //then
        Assert.assertNotNull("There should have been one task saved in the database", savedTask);
        Assert.assertEquals("The new email should match", TASK_NEW_NAME, updatedTask.getName());
    }

    private Task buildAndSaveTask() {
        Task task = new Task();
        task.setDescription(TASK_DESCRIPTION);
        task.setEstimation(TASK_ESTIMATION);
        task.setName(TASK_NAME);
        task.setPriority(TASK_PRIORITY);
        task.setStatus(TASK_STATUS);

        Task savedTask = taskService.createTask(task);
        return savedTask;
    }

    @Test(expected = EntityNotFoundException.class)
    public void taskNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        Task savedTask = buildAndSaveTask();
        taskService.deleteTask(savedTask.getId());
        //when
        long nonexistentTask = -1;
        taskService.getTask(nonexistentTask);
    }

}
