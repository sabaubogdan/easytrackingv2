package xyz.vegaone.easytrackingv2.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "userList", fetch = FetchType.EAGER)
    private List<ProjectEntity> projectEntityList;

    @OneToMany(mappedBy = "user")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
        this.projectEntityList = projectEntityList;
    }

    public List<UserStoryEntity> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStoryEntity> userStoryList) {
        this.userStoryList = userStoryList;
    }
}
