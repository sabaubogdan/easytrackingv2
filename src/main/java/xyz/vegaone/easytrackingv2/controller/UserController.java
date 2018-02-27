package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.exception.UserNotFoundException;
import xyz.vegaone.easytrackingv2.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler (UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String entityNotFoundError(UserNotFoundException userNotFoundException) {

        return userNotFoundException.getErrMsg();
    }
}
