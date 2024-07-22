package com.project.demo.rest;

import com.project.demo.entity.Lodge;
import com.project.demo.logic.LodgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/lodge")
public class LodgeController implements IController<Lodge,Integer>{

    @Autowired
    private LodgeService lodgeService;
    @PostMapping
    @Override
    public Lodge create(Lodge entity) {
        return lodgeService.save(entity);
    }

    @GetMapping
    @Override
    public List<Lodge> retrieveAll() {
        return lodgeService.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Optional<Lodge> retrieveById(Integer integer) {
        return lodgeService.findById(integer);
    }

    @PutMapping("/{id}")
    @Override
    public Lodge update(Lodge entity) {
        return lodgeService.update(entity);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(Integer integer) {
        lodgeService.deleteById(integer);
    }


}
