package com.project.demo.entity.request;

import java.time.LocalDate;

public class GoogleFlightsRequestEntity {
    private String api_key;
    private String hl;
    private String gl;
    private int type;
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

    public GoogleFlightsRequestEntity(int type, String departure_id, String arrival_id, LocalDate outbound_date) {
        this.type = type;
        this.departure_id = departure_id;
        this.arrival_id = arrival_id;
        this.outbound_date = outbound_date;
    }



    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
