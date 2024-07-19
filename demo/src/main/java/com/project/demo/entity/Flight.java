package com.project.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;

@Table(name = "VO_Flight")
@Entity
public class Flight {

    //PERSISTANCE DATA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Integer flightId;
    @Column(name = "ticket_price", nullable = false)
    private double price;
    @Column(name = "airline_name", nullable = false)
    private String airline;
        //DATES AND TIME
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String endDate;
        //Localization
    @Column(name = "language_code", nullable = false)
    private String languageSearch; //Optional, 2 characters
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_currency_id", nullable = false)
    private Currency currency; //Optional


    //FILTERS
    //Advanced Flights Parameters
    private int travelClass; // 1- Economy, 2- Premium Economy, 3- Business, 4- First
    private boolean showHidden; //Optional
    //Advanced Filters
    // 0- Any number of stops(default)
    // 1- Non-stop,
    // 2- 1 stop or fewer,
    // 3- 2 stops or fewer
    private int stops;
    private int maxPrice; //Optional
    private int maxDuration;
    /*---------------------------------------------------------------------------------------------*/
//    //Input Data
//    public static class params{
//        //Search Needed Atts
//        //InputRequired
//        private final String engine = "google_flights"; //Required
//        private String hl; //Optional Language Code 2 characters
//        private String currency_Act; //Optional, 3 characters
//        private String departureId; //Required
//        private String arrivalId; //Required
//        private Date outboundDate; //Required
//        private int type; // 1- Round Trip, 2- One Way
//        //Si type es 1, entonces returnDate es requerido
//        private Date returnDate; //Optional
//    }
    /*---------------------------------------------------------------------------------------------*/

    //CONSTRUCTORS
    public Flight() {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLanguageSearch() {
        return languageSearch;
    }

    public void setLanguageSearch(String languageSearch) {
        this.languageSearch = languageSearch;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
