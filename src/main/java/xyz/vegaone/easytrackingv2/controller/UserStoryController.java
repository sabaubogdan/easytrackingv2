package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.UserStory;
import xyz.vegaone.easytrackingv2.service.UserStoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/userstory")
@Slf4j
public class UserStoryController {

    private UserStoryService userStoryService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping(value = "/{id}")
    public UserStory getUserStory(@PathVariable(value = "id") Long id) {
        UserStory userStory = userStoryService.getUserStory(id);

        return userStory;
    }

    @GetMapping(value = "/projectid/{id}")
    public List<UserStory> getUserStoryByProjectId(@PathVariable(value = "id") Long projectId) {
        return userStoryService.getUserStoryByProjectId(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserStory createUserStory(@RequestBody UserStory userStory) {
        UserStory createdUserStory = userStoryService.createUserStory(userStory);

        return createdUserStory;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserStory updateUserStory(@RequestBody UserStory userStory) {
        UserStory updatedUserStory = userStoryService.updateUserStory(userStory);

        return updatedUserStory;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserStory(@PathVariable(value = "id") Long id) {
        userStoryService.deleteUserStory(id);
    }
}
