package com.project.demo.rest.request;

import com.project.demo.entity.request.GoogleFlightsRequestEntity;
import com.project.demo.logic.request.GoogleFlightsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/flights")
public class GoogleFlightsRequestController {
    @Autowired
    private GoogleFlightsRequestService googleFlightsRequestService;

    @GetMapping
    public GoogleFlightsRequestEntity searchFlights(@RequestParam("departure_id") String departure_id,
                                                    @RequestParam("arrival_id") String arrival_id,
                                                    @RequestParam("outbound_date") String outbound_date,
                                                    @RequestParam("return_date") String return_date,
                                                    @RequestParam("travel_class") int travel_class,
                                                    @RequestParam("stops") int stops,
                                                    @RequestParam("max_price") int max_price,
                                                    @RequestParam("hl") String hl,
                                                    @RequestParam("gl") String gl,
                                                    @RequestParam("currency") String currency,
                                                    @RequestParam("type") int type
                                ) throws Exception {
        GoogleFlightsRequestEntity requestEntity = new GoogleFlightsRequestEntity();
        requestEntity.setDeparture_id(departure_id);
        requestEntity.setArrival_id(arrival_id);
        requestEntity.setOutbound_date(LocalDate.parse(outbound_date));
        requestEntity.setType(type);
        requestEntity.setReturn_date(LocalDate.parse(return_date));
        requestEntity.setTravel_class(travel_class);
        requestEntity.setStops(stops);
        requestEntity.setMax_price(max_price);
        requestEntity.setHl(hl);
        requestEntity.setGl(gl);
        requestEntity.setCurrency(currency);

        return googleFlightsRequestService.searchFlights(requestEntity);
    }
}
