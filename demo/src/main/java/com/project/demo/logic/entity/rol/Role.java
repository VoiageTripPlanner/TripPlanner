package com.project.demo.logic.entity.rol;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Table(name = "VO_UserRole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer role_id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private Boolean operational;

    @CreationTimestamp
    @Column(updatable = false, name = "creation_datetime")
    private Date creation_datetime;

    @Column(nullable = false)
    private Integer creation_responsible;
    @UpdateTimestamp
    @Column(name = "last_update_datetime")
    private Date last_update_datetime;

    @Column(nullable = false)
    private Integer update_responsible;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public RoleEnum getRole_name() {
        return name;
    }

    public void setRole_name(RoleEnum role_name) {
        this.name = role_name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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
}
