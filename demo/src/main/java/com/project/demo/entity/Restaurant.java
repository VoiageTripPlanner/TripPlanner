package com.project.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "VO_RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    private String name;
    private String description;
    @Column(name = "creation_datetime")
    private Date creationDatetime;
    @Column(name= "last_update_datetime")
    private Date lastUpdateDatetime;
    // trip id, location mark, creation_Responsible, last_update_responsible
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private Trip trip;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "location_mark_id")
    private Location locationMark;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @JoinColumn(name = "creation_responsible_id")
//    private User creationResponsible;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @JoinColumn(name = "last_update_responsible_id")
//    private User lastUpdateResponsible;

    public Restaurant(Integer restaurantId, String name, String description, Date creationDatetime, Date lastUpdateDatetime, Trip trip) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.creationDatetime = creationDatetime;
        this.lastUpdateDatetime = lastUpdateDatetime;
        this.trip = trip;
    }

    public Restaurant() {

    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}