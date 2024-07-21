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
    private Integer lodgeId;
    @Column(name = "name", nullable = false,length = 100)
    private String lodgeName;
    @Column(name = "description",length = 200)
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

    @Column(name = "external_link", nullable = false)
    private String externalLink;

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
    private Date creationDatetime;

    @Column(nullable = false)
    private Integer creationResponsible;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date lastUpdateDatetime;

    @Column(nullable = true)
    private Integer updateResponsible;

//    Setters and getters

    public Integer getLodgeId() {
        return lodgeId;
    }

    public void setLodgeId(Integer lodgeId) {
        this.lodgeId = lodgeId;
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

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Integer getCreationResponsible() {
        return creationResponsible;
    }

    public void setCreationResponsible(Integer creationResponsible) {
        this.creationResponsible = creationResponsible;
    }

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public Integer getUpdateResponsible() {
        return updateResponsible;
    }

    public void setUpdateResponsible(Integer updateResponsible) {
        this.updateResponsible = updateResponsible;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

}
