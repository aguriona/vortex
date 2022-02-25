package com.vortex.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    @Id
    @Column(name = "COUNTRY_ID", nullable = false)
    private Long id;
    @Column(name = "COUNTRY_NAME", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "REGION_ID")
    private Region region;

    public Country() {
    }

    public Country(String name) {
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
