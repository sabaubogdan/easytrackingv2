package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.TaskEntity;
import xyz.vegaone.easytrackingv2.dto.Task;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.TaskRepo;

import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    private Mapper mapper;

    private TaskRepo taskRepo;

    @Autowired
    public TaskService(Mapper mapper, TaskRepo taskRepo) {
        this.mapper = mapper;
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        TaskEntity taskEntity = mapper.map(task, TaskEntity.class);
        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return mapper.map(savedTaskEntity, Task.class);
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepo.findById(id);

        TaskEntity taskEntity = taskEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Task with id + " + id + " not found"));

        return mapper.map(taskEntity, Task.class);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Task task) {
        TaskEntity taskEntity = mapper.map(task, TaskEntity.class);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return mapper.map(savedTaskEntity, Task.class);
    }

    public Long getUserStatisticsTasks(Long id){
        return taskRepo.countByUserId(id);
    }
}
