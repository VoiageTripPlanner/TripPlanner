package com.project.demo.rest;

import com.project.demo.entity.request.BudgetOverview;
import com.project.demo.entity.request.CountryVisit;
import com.project.demo.logic.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/countryVisits/{userId}")
    public ResponseEntity<List<CountryVisit>> getCountryVisits(@PathVariable Integer userId) {
        if (statisticsService.getCountryVisits(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsService.getCountryVisits(userId).get());
    }

    @GetMapping("/budgetOverview/{userId}")
    public ResponseEntity<BudgetOverview> getBudgetOverview(@PathVariable Integer userId) {
        if (statisticsService.getBudgetOverview(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsService.getBudgetOverview(userId).get());
    }
}
