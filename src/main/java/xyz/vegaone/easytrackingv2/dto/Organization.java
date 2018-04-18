package xyz.vegaone.easytrackingv2.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public class Organization {

    private Long id;

    private String name;

    @JsonManagedReference(value = "organization-user")
    private List<User> userList;

    @JsonManagedReference(value = "organization-project")
    private List<Project> projectList;

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
