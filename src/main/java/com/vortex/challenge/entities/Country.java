package com.vortex.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    @Id
    @Column(name = "COUNTRY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COUNTRY_NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
