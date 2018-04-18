package xyz.vegaone.easytrackingv2.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public class User {

    private Long id;

    private String name;

    private String email;

    private List<Project> projectList;

    @JsonManagedReference(value = "user-userstory")
    private List<UserStory> userStoryList;

    @JsonManagedReference(value = "user-task")
    private List<Task> taskList;

    @JsonManagedReference(value = "user-bug")
    private List<Bug> bugList;

    @JsonBackReference(value = "organization-user")
    private Organization organization;

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

    public List<UserStory> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStory> userStoryList) {
        this.userStoryList = userStoryList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Bug> getBugList() {
        return bugList;
    }

    public void setBugList(List<Bug> bugList) {
        this.bugList = bugList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @JsonIgnoreProperties(value = "userList", allowSetters = true)
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", projectList=" + projectList +
                ", userStoryList=" + userStoryList +
                ", taskList=" + taskList +
                ", bugList=" + bugList +
                ", organization=" + organization +
                '}';
    }
}
