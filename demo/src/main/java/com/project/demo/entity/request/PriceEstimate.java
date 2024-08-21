package com.project.demo.entity.request;

import java.util.Date;
import java.util.List;

public class PriceEstimate {
    private Date startDate;
    private Date endDate;
    private String currency;
    private Double totalEstimate;
    private String destination;
    private List<ActivityEstimate> activityEstimates;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getTotalEstimate() {
        return totalEstimate;
    }

    public void setTotalEstimate(Double totalEstimate) {
        this.totalEstimate = totalEstimate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<ActivityEstimate> getActivityEstimates() {
        return activityEstimates;
    }

    public void setActivityEstimates(List<ActivityEstimate> activityEstimates) {
        this.activityEstimates = activityEstimates;
    }
}
