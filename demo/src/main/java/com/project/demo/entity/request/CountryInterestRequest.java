package com.project.demo.entity.request;

import java.util.List;

public class CountryInterestRequest {
    private Integer userId;
    private List<String> savedCountries;
    private List<String> deletedCountries;

    public CountryInterestRequest(Integer userId, List<String> savedCountries, List<String> deletedCountries) {
        this.userId = userId;
        this.savedCountries = savedCountries;
        this.deletedCountries = deletedCountries;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getSavedCountries() {
        return savedCountries;
    }

    public void setSavedCountries(List<String> savedCountries) {
        this.savedCountries = savedCountries;
    }

    public List<String> getDeletedCountries() {
        return deletedCountries;
    }

    public void setDeletedCountries(List<String> deletedCountries) {
        this.deletedCountries = deletedCountries;
    }
}
