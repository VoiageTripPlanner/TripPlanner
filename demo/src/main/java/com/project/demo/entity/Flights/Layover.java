package com.project.demo.entity.Flights;

import jakarta.persistence.*;

@Table(name = "VO_Layover")
@Entity
public class Layover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "layover_id", nullable = false)
    private Integer id;
    @Column(name = "layover_code_name", nullable = false)
    private String codeId;
    @Column(name = "layover_name", nullable = false)
    private String name;
    @Column(name = "layover_duration", nullable = false)
    private int duration;

    public Layover() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
