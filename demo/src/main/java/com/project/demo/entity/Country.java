package com.project.demo.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "VO_COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "country_name", nullable = false)
    private String countryName;
    @Column(name = "country_code", nullable = false)
    private String countryCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_currency_id", nullable = false)
    private Currency currency;

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    @Column(name = "operational", nullable = false)
    private boolean operational;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}