package com.project.demo.entity.request;

public class CountryVisit {
    private Integer id;
    private String name;
    private Integer visits;

    public CountryVisit(Integer id, String name, Integer visits) {
        this.id = id;
        this.name = name;
        this.visits = visits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }
}
