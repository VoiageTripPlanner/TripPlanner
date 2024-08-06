package com.project.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Table(name = "VO_Flight")
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Integer flightId;
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @JoinColumn(name = "departure_airport", referencedColumnName = "airport_id", nullable = false)
//    private Airport departure_airport;
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    @JoinColumn (name = "arrival_airport", referencedColumnName = "airport_id", nullable = false)
//    private Airport arrival_airport;
    @Column(name= "duration", nullable = false)
    private int duration;
    @Column(name = "airline_name", nullable = false)
    private String airline;
    @Column(name = "airline_logo", nullable = false)
    private String airline_logo;
    @Column(name = "travel_class", nullable = false)
    private String travel_class;
    @Column(name= "flight_number", nullable = false)
    private String flight_number;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date outbound_date;
    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date return_date;

    @Column(name = "created_at", nullable = false)
    private Date created_at;
    @Column(name = "booking_token", nullable = false)
    private String booking_token;
    private String google_flights_link;

    @Column(name = "is_layover", nullable = false)
    private boolean isLayover;
    @Column(name = "total_duration", nullable = false)
    private int total_duration;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "type", nullable = false)
    private String type;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "trip_id")
    @JsonIgnore
    private Trip trip;

    //CONSTRUCTORS
    public Flight() {}

    //GETTERS & SETTERS

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

//    public Airport getDeparture_airport() {
//        return departure_airport;
//    }
//
//    public void setDeparture_airport(Airport departure_airport) {
//        this.departure_airport = departure_airport;
//    }
//
//    public Airport getArrival_airport() {
//        return arrival_airport;
//    }
//
//    public void setArrival_airport(Airport arrival_airport) {
//        this.arrival_airport = arrival_airport;
//    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirline_logo() {
        return airline_logo;
    }

    public void setAirline_logo(String airline_logo) {
        this.airline_logo = airline_logo;
    }

    public String getTravel_class() {
        return travel_class;
    }

    public void setTravel_class(String travel_class) {
        this.travel_class = travel_class;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public Date getOutbound_date() {
        return outbound_date;
    }

    public void setOutbound_date(Date outbound_date) {
        this.outbound_date = outbound_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getBooking_token() {
        return booking_token;
    }

    public void setBooking_token(String booking_token) {
        this.booking_token = booking_token;
    }

    public String getGoogle_flights_link() {
        return google_flights_link;
    }

    public void setGoogle_flights_link(String google_flights_link) {
        this.google_flights_link = google_flights_link;
    }

    public boolean isLayover() {
        return isLayover;
    }

    public void setLayover(boolean layover) {
        isLayover = layover;
    }

    public int getTotal_duration() {
        return total_duration;
    }

    public void setTotal_duration(int total_duration) {
        this.total_duration = total_duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
