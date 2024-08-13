package com.project.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VO_TRIP")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "return_date")
    private Date returnDate;
    private Double budget;
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "lodge_id")
    private Lodge lodge;
    @Column(name = "ai_suggestion")
    private String aiSuggestion;

    @OneToOne(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Flight flight;
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Activity> activities;

    public Trip() {
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lodge getLodge() {
        return lodge;
    }

    public void setLodge(Lodge lodge) {
        this.lodge = lodge;
    }

    public String getAiSuggestion() {
        return aiSuggestion;
    }

    public void setAiSuggestion(String aiSuggestion) {
        this.aiSuggestion = aiSuggestion;
    }
}