package com.project.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "VO_Calendar_Event")
@Entity
public class CalendarEvent  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_Id")
    private Integer eventId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(name = "event_Date",nullable = false)
    private Date eventDate;
    @Column(name = "event_Type",nullable = false)
    private String eventType;
    @Column(nullable = false)
    private Boolean operational;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date creation_datetime;
    @Column()
    private Integer creation_responsible;
    @UpdateTimestamp
    @Column(name = "updated_at",nullable = true)
    private Date last_update_datetime;
    @Column(nullable = true)
    private Integer update_responsible;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

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

    // Constructors
    public CalendarEvent() {}


}
