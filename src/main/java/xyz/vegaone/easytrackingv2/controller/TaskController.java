package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.Task;
import xyz.vegaone.easytrackingv2.service.TaskService;

@RestController
@RequestMapping(value = "/api/task")
@Slf4j
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/{id}")
    public Task getTask(@PathVariable(value = "id") Long id) {
        Task task = taskService.getTask(id);

        return task;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        return createdTask;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);

        return updatedTask;

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable(value = "id") Long id) {
        taskService.deleteTask(id);
    }
}
