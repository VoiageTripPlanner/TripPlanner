package com.project.demo.rest.request;

import com.project.demo.entity.Lodge;
import com.project.demo.logic.request.GoogleHotelRequestService;
import com.project.demo.rest.IController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lodge")
public class GoogleHotelRequestController  {

    @Autowired
    private GoogleHotelRequestService googleHotelRequestService;

    @GetMapping
    public List<Lodge> retrieveAll() {
        return googleHotelRequestService.findAll();
    }



}
