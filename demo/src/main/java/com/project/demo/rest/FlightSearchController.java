package com.project.demo.rest;

import com.project.demo.logic.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
@RequestMapping("/")
public class FlightSearchController {
    @Autowired
    private FlightSearchService flightSearchService;

    public String searchFlights(@PathVariable String engine, @PathVariable String departureId, @PathVariable String arrivalId,
                                @PathVariable Date outboundDate, @PathVariable int type, @PathVariable Date returnDate) {
        return flightSearchService.searchFlights(engine, departureId, arrivalId, outboundDate, type, returnDate);
    }

}
