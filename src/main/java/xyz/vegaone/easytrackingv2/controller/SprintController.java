package xyz.vegaone.easytrackingv2.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.service.SprintService;

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
