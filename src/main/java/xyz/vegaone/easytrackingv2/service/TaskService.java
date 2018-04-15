package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.TaskEntity;
import xyz.vegaone.easytrackingv2.dto.Task;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.TaskMapper;
import xyz.vegaone.easytrackingv2.repo.TaskRepo;

import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    private TaskMapper taskMapper;

    private TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskMapper taskMapper, TaskRepo taskRepo) {
        this.taskMapper = taskMapper;
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        TaskEntity taskEntity = taskMapper.dtoToDomain(task);
        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return taskMapper.domainToDto(savedTaskEntity);
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepo.findById(id);

        TaskEntity taskEntity = taskEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Task with id + " + id + " not found"));
        Task task = taskMapper.domainToDto(taskEntity);

        return task;
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Task task) {
        TaskEntity taskEntity = taskMapper.dtoToDomain(task);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        Task savedTask = taskMapper.domainToDto(savedTaskEntity);

        return savedTask;
    }

    public Long getUserStatisticsTasks(Long id){
        return taskRepo.countByUserId(id);
    }
}
