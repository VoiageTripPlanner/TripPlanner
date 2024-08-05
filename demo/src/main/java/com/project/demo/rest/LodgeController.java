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
public class LodgeController implements IController<Lodge, Integer> {

    @Autowired
    private LodgeService lodgeService;

    @Override
    @PostMapping
    public ResponseEntity<Lodge> create(@RequestBody Lodge entity) {
        Lodge createdLodge = lodgeService.save(entity);
        return ResponseEntity.ok(createdLodge);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Lodge>> retrieveAll() {
        List<Lodge> lodges = lodgeService.findAll();
        return ResponseEntity.ok(lodges);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Lodge> retrieveById(@PathVariable Integer id) {
        Lodge lodge = lodgeService.findById(id);
        return ResponseEntity.ok(lodge);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Lodge> update(@RequestBody Lodge entity) {
        Lodge updatedLodge = lodgeService.update(entity);
        return ResponseEntity.ok(updatedLodge);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        lodgeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
