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
    @JoinColumn(name = "departure_code_airport", nullable = false)
    private String departureId;
    @JoinColumn(name = "departure_name_airport", nullable = false)
    private String departure_Name;
    @JoinColumn(name = "arrival_code_airport", nullable = true)
    private String arrival_Id;
    @JoinColumn(name = "arrival_name_airport", nullable = true)
    private String arrival_Name;
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

    @Column(name = "outbound_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate outbound_date;
    @Column(name = "return_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private LocalDate return_date;

    @Column(name = "layover", nullable = false)
    private boolean isLayover;
    @Column(name = "total_durations", nullable = false)
    private int total_durations;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "type", nullable = false)
    private String type;

    //CONSTRUCTORS
    public Flight() {}

    //GETTERS & SETTERS

}
