package com.vortex.challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "JOBS")
public class Job implements Serializable {
    @Id
    @Column(name = "JOB_ID", nullable = false)
    private Long id;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "MIN_SALARY")
    private Double minSalary;

    @Column(name = "MAX_SALARY")
    private Double maxSalary;

    public Job() {
    }

    public Job(String jobTitle, Double minSalary, Double maxSalary) {
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }
}
