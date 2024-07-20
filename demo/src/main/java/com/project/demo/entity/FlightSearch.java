package com.project.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FlightSearch {
    @Id
    private Integer flightSearchId;

    //InputRequired
    private final String engine = "google_flights"; //Required
    private String hl; //Optional Language Code 2 characters
    private String currency_Act; //Optional, 3 characters
    private String departureId; //Required
    private String arrivalId; //Required
    private Date outboundDate; //Required
    private int type; // 1- Round Trip, 2- One Way
    //Si type es 1, entonces returnDate es requerido
    private Date returnDate; //Optional

    //FILTERS
    //Advanced Flights Parameters
    //private int travelClass; // 1- Economy, 2- Premium Economy, 3- Business, 4- First
    //private boolean showHidden; //Optional
    //Advanced Filters
    // 0- Any number of stops(default)
    // 1- Non-stop,
    // 2- 1 stop or fewer,
    // 3- 2 stops or fewer
    //private int stops;
    //private int maxPrice; //Optional
    //private int maxDuration;

    public FlightSearch() {
    }

    public Integer getFlightSearchId() {
        return flightSearchId;
    }

    public void setFlightSearchId(Integer flightSearchId) {
        this.flightSearchId = flightSearchId;
    }

    public String getEngine() {
        return engine;
    }

    public String getHl() {
        return hl;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public String getCurrency_Act() {
        return currency_Act;
    }

    public void setCurrency_Act(String currency_Act) {
        this.currency_Act = currency_Act;
    }

    public String getDepartureId() {
        return departureId;
    }

    public void setDepartureId(String departureId) {
        this.departureId = departureId;
    }

    public String getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(String arrivalId) {
        this.arrivalId = arrivalId;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
