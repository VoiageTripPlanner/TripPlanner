package com.project.demo.entity.request;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActivitiesYelpRequest {

    private Float latitude;

    private Float logitude;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLogitude() {
        return logitude;
    }

    public void setLogitude(Float logitude) {
        this.logitude = logitude;
    }
}
