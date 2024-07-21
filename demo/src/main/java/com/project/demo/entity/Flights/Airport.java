package com.project.demo.entity.Flights;

import jakarta.persistence.*;

@Table(name = "VO_Airport")
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id", nullable = false)
    private Integer id;
    @Column(name = "airport_name", nullable = false)
    private String name;
    @Column(name = "airport_code_name", nullable = false)
    private String codeId;
    @Column(name = "airport_time", nullable = false)
    private String time;

    public Airport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
