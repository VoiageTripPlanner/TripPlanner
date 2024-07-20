package com.project.demo.logic.request;

import com.project.demo.entity.Lodge;
import com.project.demo.logic.IService;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.request.GoogleHotelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoogleHotelRequestService {

    @Autowired
    private GoogleHotelRequestRepository googleHotelRequestRepository;
    public List<Lodge> findAll() {
        return googleHotelRequestRepository.findAll();
    }


}
