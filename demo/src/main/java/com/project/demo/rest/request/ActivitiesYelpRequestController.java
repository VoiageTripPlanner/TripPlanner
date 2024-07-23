package com.project.demo.rest.request;

import com.project.demo.logic.request.ActivitiesYelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesYelpRequestController {

    @Autowired
    private ActivitiesYelpRequestService activitiesYelpRequestService;

    @GetMapping
    public String searchHotels(@RequestParam("latitude") Float latitude,
                               @RequestParam("longitude")  Float longitude){
        return activitiesYelpRequestService.searchActivities(latitude, longitude);
    }


}
