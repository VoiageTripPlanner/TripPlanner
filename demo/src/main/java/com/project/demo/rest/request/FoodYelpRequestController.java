package com.project.demo.rest.request;

import com.project.demo.logic.request.FoodYelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodYelpRequestController {

    @Autowired
    private FoodYelpRequestService foodYelpRequestService;

    @GetMapping
    public String searchHotels(@RequestParam("latitude") Float latitude,
                               @RequestParam("longitude")  Float longitude){
        return foodYelpRequestService.searchFood(latitude, longitude);
    }


}
