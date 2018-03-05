package xyz.vegaone.easytrackingv2.domain;

import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private List<UserEntity> userList;

    @OneToMany(mappedBy = "project")
    private List<SprintEntity> sprintList;

    @OneToMany(mappedBy = "project")
    private List<UserStoryEntity> userStoryList;

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

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    public List<SprintEntity> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<SprintEntity> sprintList) {
        this.sprintList = sprintList;
    }

    public List<UserStoryEntity> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStoryEntity> userStoryList) {
        this.userStoryList = userStoryList;
    }
}
