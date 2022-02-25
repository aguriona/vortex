package com.vortex.challenge.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REGIONS")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID", nullable = false)
    private Long id;

    @Column(name = "REGION_NAME", nullable = false)
    private String name;


    public Region() {
    }

    public Region(String name) {
        this.name = name;
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
}
