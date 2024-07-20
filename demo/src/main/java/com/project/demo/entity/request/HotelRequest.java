package com.project.demo.entity.request;

import java.util.Date;

public class HotelRequest {

    private String query;

    private Date check_in_date;

    private Date check_out_date;
    private final String api_key= "a25a903700190f245c88c3bbde9cade9d1afe4364e307b6d1433f71bc4185fc4";

//    private String api_key={GOOGLE_HOTEL_KEY};
//    a25a903700190f245c88c3bbde9cade9d1afe4364e307b6d1433f71bc4185fc4

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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


}
