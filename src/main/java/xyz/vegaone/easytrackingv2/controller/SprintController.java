package xyz.vegaone.easytrackingv2.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.service.SprintService;

import java.util.List;

@RestController
@RequestMapping(value = "api/sprint")
@Slf4j
public class SprintController {

    private SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sprint createSprint(@RequestBody Sprint sprint) {
        Sprint createdSprint = sprintService.createSprint(sprint);

        return createdSprint;
    }

    @GetMapping(value = "/project/{id}")
    public List<Sprint> getSprintByProjectId(@PathVariable(value = "id") Long projectId) {
        return sprintService.getSprintByProjectid(projectId);
    }

    @GetMapping(value = "/{id}")
    public Sprint getSprint(@PathVariable(value = "id") Long id) {
        Sprint sprint = sprintService.getSprint(id);

        return sprint;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Sprint updatedSprint(@RequestBody Sprint sprint) {
        Sprint updatedSprint = sprintService.updateSprint(sprint);

        return updatedSprint;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSprint(@PathVariable(value = "id") Long id) {
        sprintService.deleteSprint(id);
    }
}
