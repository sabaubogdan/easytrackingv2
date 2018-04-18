package xyz.vegaone.easytrackingv2.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

public class Sprint {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long sprintNumber;

    @JsonBackReference(value = "project-sprint")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Long sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
