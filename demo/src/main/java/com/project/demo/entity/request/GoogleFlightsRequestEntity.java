package com.project.demo.entity.request;

import java.time.LocalDate;

public class GoogleFlightsRequestEntity {

    private String hl;
    private String gl;
    private String currency;
    private String departure_id;
    private String arrival_id;
    private LocalDate outbound_date;
    private LocalDate return_date;
    /*---------------------------------------------------------*/
    private int travel_class;
    private int stops;
    private int max_price;

    public GoogleFlightsRequestEntity() {
    }

    public String getHl() {
        return hl;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(String departure_id) {
        this.departure_id = departure_id;
    }

    public String getArrival_id() {
        return arrival_id;
    }

    public void setArrival_id(String arrival_id) {
        this.arrival_id = arrival_id;
    }

    public LocalDate getOutbound_date() {
        return outbound_date;
    }

    public void setOutbound_date(LocalDate outbound_date) {
        this.outbound_date = outbound_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public int getTravel_class() {
        return travel_class;
    }

    public void setTravel_class(int travel_class) {
        this.travel_class = travel_class;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }
}