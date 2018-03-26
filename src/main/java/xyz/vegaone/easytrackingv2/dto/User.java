package xyz.vegaone.easytrackingv2.dto;

import java.util.List;

public class User {

    private Long id;

    private String name;

    private String email;

    private List<UserStory> userList; // switch to userStoryList and fix infinite loop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserStory> getUserList() {
        return userList;
    }

    public void setUserList(List<UserStory> userList) {
        this.userList = userList;
    }
}
