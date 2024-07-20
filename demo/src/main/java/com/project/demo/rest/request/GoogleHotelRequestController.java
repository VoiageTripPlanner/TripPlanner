package com.project.demo.rest.request;

import com.project.demo.entity.Lodge;
import com.project.demo.entity.User;
import com.project.demo.logic.request.GoogleHotelRequestService;
import com.project.demo.rest.IController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
public class GoogleHotelRequestController  {

    @Autowired
    private GoogleHotelRequestService googleHotelRequestService;

    @GetMapping
    public String searchHotels(@RequestParam("q") String query,
                               @RequestParam("check_in_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkInDate,
                               @RequestParam("check_out_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOutDate) {
        return googleHotelRequestService.searchHotels(query, checkInDate, checkOutDate);
    }


}
