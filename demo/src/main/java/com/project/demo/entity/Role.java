package com.project.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "VO_UserRole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private Boolean operational;
    @CreationTimestamp
    @Column(name = "creation_datetime", updatable = false)
    private Date creationDatetime;
    @Column(name = "creation_responsible", nullable = false)
    private Integer creationResponsible;
    @UpdateTimestamp
    @Column(name = "last_update_datetime")
    private Date lastUpdateDatetime;
    @Column(name = "update_responsible", nullable = false)
    private Integer updateResponsible;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
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
}
