package com.project.demo.logic.entity.user;
import com.project.demo.logic.entity.country.Country;
import com.project.demo.logic.entity.rol.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "VO_User")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String last_name;

    @Column(nullable = true)
    private String second_last_name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    private Country country;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean operational;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date creation_datetime;

    @Column(nullable = false)
    private Long creation_responsible;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date last_update_datetime;

    @Column(nullable = false)
    private Long update_responsible;

//    @OneToMany(mappedBy = "vo_country")

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRole_name().toString());
        return List.of(authority);
    }


    // Constructors
    public User() {}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSecond_last_name() {
        return second_last_name;
    }

    public void setSecond_last_name(String second_last_name) {
        this.second_last_name = second_last_name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getCreation_responsible() {
        return creation_responsible;
    }

    public void setCreation_responsible(Long creation_responsible) {
        this.creation_responsible = creation_responsible;
    }

    public Date getLast_update_datetime() {
        return last_update_datetime;
    }

    public void setLast_update_datetime(Date last_update_datetime) {
        this.last_update_datetime = last_update_datetime;
    }

    public Long getUpdate_responsible() {
        return update_responsible;
    }

    public void setUpdate_responsible(Long update_responsible) {
        this.update_responsible = update_responsible;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;

        return this;
    }
}
