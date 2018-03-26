package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.service.BugService;

@RestController
@RequestMapping(value = "/api/bug")
@Slf4j
public class BugController {

    private BugService bugService;

    @Autowired
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping(value = "/{id}")
    public Bug getBug(@PathVariable(value = "id") Long id) {
        Bug bug = bugService.getBug(id);

        return bug;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bug createBug(@RequestBody Bug bug) {
        Bug createdBug = bugService.createBug(bug);

        return createdBug;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Bug updateBug(@RequestBody Bug bug) {
        Bug updatedBug = bugService.updateBug(bug);

        return updatedBug;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBug(@PathVariable(value = "id") Long id) {
        bugService.deleteBug(id);
    }

}
