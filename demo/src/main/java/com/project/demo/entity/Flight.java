package com.project.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Table(name = "VO_Flight")
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Integer flightId;
    @ManyToOne
    @JoinColumn(name = "departure_airport", referencedColumnName = "airport_id", nullable = false)
    private Airport departure_airport;
    @ManyToOne
    @JoinColumn (name = "arrival_airport", referencedColumnName = "airport_id", nullable = false)
    private Airport arrival_airport;
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
    private LocalDate outbound_date;
    @Column(name = "end_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private LocalDate return_date;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
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

    //CONSTRUCTORS
    public Flight() {}

    //GETTERS & SETTERS

}
