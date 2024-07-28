package com.project.demo.rest.request;

import com.project.demo.entity.request.GoogleFlightsRequestEntity;
import com.project.demo.logic.request.GoogleFlightsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping("/api/flights")
public class GoogleFlightsRequestController {
    @Autowired
    private GoogleFlightsRequestService googleFlightsRequestService;

    @GetMapping
    public GoogleFlightsRequestEntity searchFlights(@RequestParam("departure_id") String departure_id,
                                                    @RequestParam("arrival_id") String arrival_id,
                                                    @RequestParam("outbound_date") String outbound_date,
                                                    @RequestParam(value = "return_date", required = false, defaultValue = " ") String return_date,
                                                    @RequestParam(value = "travel_class", required = false, defaultValue = "0") int travel_class,
                                                    @RequestParam(value = "stops", required = false, defaultValue = "0") int stops,
                                                    @RequestParam(value = "max_price", required = false, defaultValue = "0") int max_price,
                                                    @RequestParam(value = "hl", required = false) String hl,
                                                    @RequestParam(value = "gl", required = false) String gl,
                                                    @RequestParam(value = "currency", required = false) String currency,
                                                    @RequestParam("type") int type
                                ) throws Exception {

            GoogleFlightsRequestEntity requestEntity = new GoogleFlightsRequestEntity( hl, gl, type, currency, departure_id, arrival_id,
                    LocalDate.parse(outbound_date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)),
                    return_date.equals(" ") ? null : LocalDate.parse(return_date, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)),
                    travel_class, stops, max_price);

            return googleFlightsRequestService.searchFlights(requestEntity);
    }
}
