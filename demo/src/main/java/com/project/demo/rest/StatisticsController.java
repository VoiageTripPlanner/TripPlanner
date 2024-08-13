package com.project.demo.rest;

import com.project.demo.entity.request.BudgetOverview;
import com.project.demo.entity.request.CountryVisit;
import com.project.demo.logic.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/topCountryVisits")
    public ResponseEntity<List<CountryVisit>> getTopCountryVisits() {
        if (statisticsService.getTopCountryVisits().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsService.getTopCountryVisits().get());
    }

    @GetMapping("/countryVisits")
    public ResponseEntity<List<CountryVisit>> getCountryVisits() {
        if (statisticsService.getCountryVisits().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsService.getCountryVisits().get());
    }

    @GetMapping("/budgetOverview")
    public ResponseEntity<BudgetOverview> getBudgetOverview() {
        if (statisticsService.getBudgetOverview().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsService.getBudgetOverview().get());
    }
}
