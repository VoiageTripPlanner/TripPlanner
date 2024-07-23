package com.project.demo.entity.request;

import org.springframework.stereotype.Service;

@Service
public class FoodYelpRequest {

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
