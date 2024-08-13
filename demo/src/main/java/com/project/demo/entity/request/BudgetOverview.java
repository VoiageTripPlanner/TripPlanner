package com.project.demo.entity.request;

public class BudgetOverview {
    private Double flightAmount;
    private Double lodgeAmount;
    private Double foodAmount;
    private Double activitiesAmount;
    private Double otherAmount;
    private Double total;

    public BudgetOverview(Double flightAmount, Double lodgeAmount, Double foodAmount, Double activitiesAmount, Double otherAmount, Double total) {
        this.flightAmount = flightAmount;
        this.lodgeAmount = lodgeAmount;
        this.foodAmount = foodAmount;
        this.activitiesAmount = activitiesAmount;
        this.otherAmount = otherAmount;
        this.total = total;
    }

    public Double getFlightAmount() {
        return flightAmount;
    }

    public void setFlightAmount(Double flightAmount) {
        this.flightAmount = flightAmount;
    }

    public Double getLodgeAmount() {
        return lodgeAmount;
    }

    public void setLodgeAmount(Double lodgeAmount) {
        this.lodgeAmount = lodgeAmount;
    }

    public Double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(Double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public Double getActivitiesAmount() {
        return activitiesAmount;
    }

    public void setActivitiesAmount(Double activitiesAmount) {
        this.activitiesAmount = activitiesAmount;
    }

    public Double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(Double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
