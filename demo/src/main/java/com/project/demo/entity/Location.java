package com.project.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Table(name = "vo_location")
@Entity
public class Location {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;
    private String address;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(name = "place_id")
    private String placeId;
    @Column(name = "user_id")
    private int userId;

    public Location() {
    }

    public Location(Integer locationId, String address, double latitude, double longitude, String placeId) {
        this.locationId = locationId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeId = placeId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Integer setLocationId(Integer locationId) {
        this.locationId = locationId;
        return locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}