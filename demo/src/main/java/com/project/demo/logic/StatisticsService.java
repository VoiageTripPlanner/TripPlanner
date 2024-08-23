package com.project.demo.logic;

import com.project.demo.entity.Trip;
import com.project.demo.entity.request.BudgetOverview;
import com.project.demo.entity.request.CountryVisit;
import com.project.demo.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    TripRepository tripRepository;

    public Optional<List<CountryVisit>> getCountryVisits(Integer userId) {
        List<Object[]> countryVisits = tripRepository.findTripCountByUserAndCountry(userId);
        List<CountryVisit> countryVisitList = countryVisits.stream().map(countryVisit -> {
            Integer countryId = (Integer) countryVisit[0];
            String countryName = (String) countryVisit[1];
            Long tripCount = (Long) countryVisit[2];
            return new CountryVisit(countryId, countryName, tripCount.intValue());
        }).toList();
        return Optional.of(countryVisitList);
    }

    public Optional<BudgetOverview> getBudgetOverview(Integer userId) {
        List<Trip> trips = tripRepository.findByUserId(userId);
        Double totalBudget = trips.stream().mapToDouble(Trip::getBudget).sum();
        Double totalActivitiesEstimatedCost = trips.stream().mapToDouble(trip -> {
            return trip.getActivitiesEstimatedCost() != null ? trip.getActivitiesEstimatedCost() : 0;
        }).sum();
        Double totalRestaurantsEstimatedCost = trips.stream().mapToDouble(trip -> {
            return trip.getRestaurantsEstimatedCost() != null ? trip.getRestaurantsEstimatedCost() : 0;
        }).sum();
        Double totalFlightCost = trips.stream().mapToDouble(trip -> {
            if (trip.getFlight() == null) {
                return 0;
            }
            return trip.getFlight().getPrice();
        }).sum();
        Double totalLodgeCost = trips.stream().mapToDouble(trip -> {
            Date departureDate = trip.getDepartureDate();
            Date returnDate = trip.getReturnDate();
            int nights = (int) ((returnDate.getTime() - departureDate.getTime()) / (1000 * 60 * 60 * 24));
            return trip.getLodge().getNightPrice() * nights;
        }).sum();
        Double otherCosts = totalBudget - (totalActivitiesEstimatedCost + totalRestaurantsEstimatedCost + totalFlightCost + totalLodgeCost);
        BudgetOverview budgetOverview = new BudgetOverview(totalFlightCost, totalLodgeCost, totalRestaurantsEstimatedCost, totalActivitiesEstimatedCost, otherCosts, totalBudget);
        return Optional.of(budgetOverview);
    }
}
