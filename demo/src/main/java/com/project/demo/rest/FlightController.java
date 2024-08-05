package com.project.demo.rest;

import com.project.demo.entity.Flight;
import com.project.demo.logic.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights") // No estamos usando barra lateral al final aca. La barra lateral sí debería ir al inicio
public class FlightController implements IController<Flight, Integer> {
    @Autowired
    private FlightService flightService;

    @Override
    @PostMapping// No hace falta ni los paréntesis ni el espacio
    // Dado que solo tenemos esos dos users, no hace falta especificar el preauthorize. Ya se hizo en la parte de seguridad
    public ResponseEntity<Flight> create(@RequestBody Flight entity) {
        return ResponseEntity.ok(flightService.save(entity));
    }

    @Override
    @GetMapping("/{id}") //Agregué la barra que hacía falta acá
    public ResponseEntity<Flight> retrieveById(@PathVariable Integer id) {
        return ResponseEntity.ok(flightService.findById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        flightService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public ResponseEntity<List<Flight>> retrieveAll() { return null; }
    @Override
    public ResponseEntity<Flight> update(Flight entity) {
        return null;
    }
}
