package com.project.demo.entity.request;

import java.util.Date;

public class GoogleHotelRequest {

//    public final String engine= "google_hotels";

    private String q;

    private Date check_in_date;

    private Date check_out_date;
    private  String api_key;

    public String getQuery() {
        return q;
    }

    public void setQuery(String query) {
        this.q = q;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

//    public String getEngine() {
//        return engine;
//    }



}
