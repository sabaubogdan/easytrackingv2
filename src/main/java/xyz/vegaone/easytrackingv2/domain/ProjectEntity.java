package xyz.vegaone.easytrackingv2.domain;

import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<UserEntity> userList;

    @OneToMany(mappedBy = "project")
    private List<SprintEntity> sprintList;

    @OneToMany(mappedBy = "project")
    private List<UserStoryEntity> userStoryList;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    public ProjectEntity() {
    }

    public ProjectEntity(Long id, String name) {
        this.id = id;
        this.title = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }
}
