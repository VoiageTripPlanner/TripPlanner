package com.project.demo.entity;

import jakarta.persistence.*;

@Table(name = "VO_Airport")
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airport_id;
    private String name;
    private String id;
    private String time;

    public Airport() {}

    public Airport(Integer airport_id) {
        this.airport_id = airport_id;
    }

    public Integer getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Integer airport_id) {
        this.airport_id = airport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
