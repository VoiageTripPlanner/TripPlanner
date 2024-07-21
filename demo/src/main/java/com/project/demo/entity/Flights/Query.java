package com.project.demo.entity.Flights;

import java.util.List;

public class Query {

    //Query Data
    private List<Flight> flights; //Output
    private List<Layover> layovers; //Output
    private int total_duration;
    private int price;
    private String type;

    public Query() { }

    public Query(List<Flight> flights, List<Layover> layovers,
                 int total_duration, int price, String type) {
        this.flights = flights;
        this.layovers = layovers;
        this.total_duration = total_duration;
        this.price = price;
        this.type = type;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Layover> getLayovers() {
        return layovers;
    }

    public void setLayovers(List<Layover> layovers) {
        this.layovers = layovers;
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
