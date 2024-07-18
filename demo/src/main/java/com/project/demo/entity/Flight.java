package com.project.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "vo_flight")
@Entity
public class Flight {
    //PERSISTANCE DATA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    private double price;
    private String airline;
    /*---------------------------------------------------------------------------------------------*/
    //Search Needed Atts
    //InputRequired
    private String engine = "google_flights"; //Required
    private String departureId; //Required
    private String arrivalId; //Required
    private Date outboundDate; //Required
    private int type; // 1- Round Trip, 2- One Way
    //Si type es 1, entonces returnDate es requerido
    private Date returnDate; //Optional
    /*---------------------------------------------------------------------------------------------*/
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
    //Localization
    private String countryCode; //Optional
    private String languageSearch; //Optional
    private char currency; //Optional
}
