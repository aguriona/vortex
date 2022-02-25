package com.vortex.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LOCATIONS")
public class Location implements Serializable {
    @Id
    @Column(name = "LOCATION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STREET_ADDRESS")
    private String street;

    @Column(name = "POSTAL_CODE")
    private Integer postalCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE_PROVINCE")
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    public Location() {
    }

    public Location(String street, Integer postalCode, String city, String stateProvince, Country country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
