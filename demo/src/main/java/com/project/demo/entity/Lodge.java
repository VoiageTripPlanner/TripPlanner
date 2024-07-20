package com.project.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Table(name = "VO_Lodge")
public class Lodge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lodge_id", nullable = false)
    private Integer lodge_id;
    @Column(name = "name", nullable = false)
    private String lodgeName;
    @Column(name = "description")
    private String description;
    @Column(name = "check_in", nullable = false)
    private Date checkIn;
    @Column(name = "check_out", nullable = false)
    private Date checkOut;
    @Column(name = "night_price", nullable = false)
    private Double nightPrice;
    @Column(name = "latitude", nullable = false)
    private Float latitude;
    @Column(name = "longitude", nullable = false)
    private Float longitude;

//    @Column(name = "external_link", nullable = false)
//    private String externalLink;
    @Column(name = "total_rate", nullable = false)
    private Double totalRate;
    @Column(name = "images")
    private String images;
    @Column(name = "type")
    private String type;
    @Column(name = "amenities")
    private String amenities;
//    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @JoinColumn(name = "trip_id", nullable = false)
//    private Trip tripId;

    @Column(nullable = false)
    private Boolean operational;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date creation_datetime;

    @Column(nullable = false)
    private Integer creation_responsible;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date last_update_datetime;

    @Column(nullable = true)
    private Integer update_responsible;

//    Setters and getters
    public Integer getLodge_id() {
        return lodge_id;
    }

    public void setLodge_id(Integer lodge_id) {
        this.lodge_id = lodge_id;
    }

    public String getLodgeName() {
        return lodgeName;
    }

    public void setLodgeName(String lodgeName) {
        this.lodgeName = lodgeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(Double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Double totalRate) {
        this.totalRate = totalRate;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

//    public Trip getTripId() {
//        return tripId;
//    }
//
//    public void setTripId(Trip tripId) {
//        this.tripId = tripId;
//    }

    public Boolean getOperational() {
        return operational;
    }

    public void setOperational(Boolean operational) {
        this.operational = operational;
    }

    public Date getCreation_datetime() {
        return creation_datetime;
    }

    public void setCreation_datetime(Date creation_datetime) {
        this.creation_datetime = creation_datetime;
    }

    public Integer getCreation_responsible() {
        return creation_responsible;
    }

    public void setCreation_responsible(Integer creation_responsible) {
        this.creation_responsible = creation_responsible;
    }

    public Date getLast_update_datetime() {
        return last_update_datetime;
    }

    public void setLast_update_datetime(Date last_update_datetime) {
        this.last_update_datetime = last_update_datetime;
    }

    public Integer getUpdate_responsible() {
        return update_responsible;
    }

    public void setUpdate_responsible(Integer update_responsible) {
        this.update_responsible = update_responsible;
    }
}
