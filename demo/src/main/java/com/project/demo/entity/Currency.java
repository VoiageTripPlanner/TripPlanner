package com.project.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VO_CURRENCY")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id", nullable = false)
    private Integer currencyId;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;
    @Column(name = "currency_code", nullable = false, length = 2)
    private String currencyCode;
    @Column(name = "currency_symbol", nullable = false)
    private String currencySymbol;
    @Transient
    @OneToMany(mappedBy = "currency")
    private List<Country> countries;

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
