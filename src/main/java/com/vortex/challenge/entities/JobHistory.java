package com.vortex.challenge.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "JOB_HISTORY")
@SQLDelete(sql = "UPDATE JOB_HISTORY SET END_DATE = current_timestamp() WHERE id = ?")
@Where(clause = "END_DATE is null")
public class JobHistory implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employeeId;

    @Column(name = "START_DATE")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job jobId;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public JobHistory() {
    }

    public JobHistory(Date startDate, Date endDate, Job jobId, Department department) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobId = jobId;
        this.department = department;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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

    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
