package com.project.demo.logic;

import com.project.demo.entity.request.BudgetOverview;
import com.project.demo.entity.request.CountryVisit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    public Optional<List<CountryVisit>> getTopCountryVisits() {
        // TODO: Get country visits from the database
        CountryVisit countryVisit1 = new CountryVisit(1, "United States", 100);
        CountryVisit countryVisit2 = new CountryVisit(2, "Germany", 50);
        CountryVisit countryVisit3 = new CountryVisit(3, "Costa Rica", 30);
        List<CountryVisit> countryVisitList = new ArrayList<CountryVisit>();
        countryVisitList.add(countryVisit1);
        countryVisitList.add(countryVisit2);
        countryVisitList.add(countryVisit3);
        return Optional.of(countryVisitList);
    }

    public Optional<List<CountryVisit>> getCountryVisits() {
        // TODO: Get country visits from the database
        CountryVisit countryVisit1 = new CountryVisit(1, "United States of America", 100);
        CountryVisit countryVisit2 = new CountryVisit(2, "Germany", 50);
        CountryVisit countryVisit3 = new CountryVisit(3, "Costa Rica", 30);
        CountryVisit countryVisit4 = new CountryVisit(3, "Mexico", 20);
        CountryVisit countryVisit5 = new CountryVisit(3, "Colombia", 10);
        CountryVisit countryVisit6 = new CountryVisit(3, "Spain", 9);
        CountryVisit countryVisit7 = new CountryVisit(3, "France", 8);
        CountryVisit countryVisit8 = new CountryVisit(3, "Sweden", 7);
        CountryVisit countryVisit9 = new CountryVisit(3, "Iceland", 6);
        CountryVisit countryVisit10 = new CountryVisit(3, "Poland", 3);
        List<CountryVisit> countryVisitList = new ArrayList<CountryVisit>();
        countryVisitList.add(countryVisit1);
        countryVisitList.add(countryVisit2);
        countryVisitList.add(countryVisit3);
        countryVisitList.add(countryVisit4);
        countryVisitList.add(countryVisit5);
        countryVisitList.add(countryVisit6);
        countryVisitList.add(countryVisit7);
        countryVisitList.add(countryVisit8);
        countryVisitList.add(countryVisit9);
        countryVisitList.add(countryVisit10);
        return Optional.of(countryVisitList);
    }

    public Optional<BudgetOverview> getBudgetOverview() {
        BudgetOverview budgetOverview = new BudgetOverview(10000.00, 5000.00, 30000.00, 1000.00, 5000.00, 12000500.00);
        return Optional.of(budgetOverview);
    }
}
