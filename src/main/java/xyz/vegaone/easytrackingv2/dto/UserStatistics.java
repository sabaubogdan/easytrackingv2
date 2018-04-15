package xyz.vegaone.easytrackingv2.dto;

public class UserStatistics {

    private String name;

    private String email;

    private Organization organization;

    private Long numberOfUserStories;

    private Long numberOfTasks;

    private Long numberOfBugs;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getNumberOfUserStories() {
        return numberOfUserStories;
    }

    public void setNumberOfUserStories(Long numberOfUserStories) {
        this.numberOfUserStories = numberOfUserStories;
    }

    public Long getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(Long numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public Long getNumberOfBugs() {
        return numberOfBugs;
    }

    public void setNumberOfBugs(Long numberOfBugs) {
        this.numberOfBugs = numberOfBugs;
    }
}
