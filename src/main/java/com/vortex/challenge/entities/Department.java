package com.vortex.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEPARTMENTS")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String name;

    @Column(name = "MANAGER_ID")
    private Long managerId;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    public Department() {
    }

    public Department(String name, Long managerId, Location location) {
        this.name = name;
        this.managerId = managerId;
        this.location = location;
    }

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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
