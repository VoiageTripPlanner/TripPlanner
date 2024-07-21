package com.project.demo.entity.Flights;

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
    @JoinColumn(name = "departure_airport", nullable = false)
    private Integer departureId;
    @JoinColumn(name = "arrival_airport", nullable = true)
    private Integer arrivalId;
    @Column(name= "duration", nullable = false)
    private int duration;
    @Column(name= "flight_number", nullable = false)
    private String flightNumber;

    //DATES AND TIME
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @Column(name = "flight_type", nullable = false)
    // 1- Round Trip, 2- One Way
    private int type;

    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String endDate;

    @Column(name = "g_flight_url", nullable = false)
    private String gFlightUrl;
    @Column(name = "total_price", nullable = false)
    private double totalPrice;

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

    public Integer getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Integer departureId) {
        this.departureId = departureId;
    }

    public Integer getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getgFlightUrl() {
        return gFlightUrl;
    }

    public void setgFlightUrl(String gFlightUrl) {
        this.gFlightUrl = gFlightUrl;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
